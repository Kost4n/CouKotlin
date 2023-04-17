package com.kost4n.coukotlin.ui.settings

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import com.kost4n.coukotlin.RemindReceiver
import com.kost4n.coukotlin.databinding.FragmentSettingsBinding
import java.util.Calendar
import java.util.Date

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding?.timeButton?.setOnClickListener {
            val time = Date()
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTitleText("Выберете время")
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(time.hours)
                .setMinute(time.minutes)
                .build()

            materialTimePicker.addOnPositiveButtonClickListener{
                val pickedHour: Int = materialTimePicker.hour
                val pickedMinute: Int = materialTimePicker.minute
                // check for single digit hour hour and minute
                // and update TextView accordingly
                val textTime: String = getTextTime(pickedHour, pickedMinute)
                val remindTime = getTime(pickedHour, pickedMinute)

                if (pickedHour <= 12) {
                    binding?.viewMorningTimeAlarm?.text = textTime
                    } else {
                    binding?.viewEveningTimeAlarm?.text = textTime
                }

                setRemind(layoutInflater.context.applicationContext, remindTime)
            }

            materialTimePicker.show(childFragmentManager, "settings_fragment_tag")


        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun getTime(pickedHour: Int, pickedMinute: Int): Long = (((pickedHour * 60) + pickedMinute) * 60).toLong()

fun getTextTime(pickedHour: Int, pickedMinute: Int): String = when {
    pickedHour > 12 -> {
        if (pickedMinute < 10) {
            "${pickedHour - 12}:0${pickedMinute} pm"
        } else {
            "${pickedHour - 12}:${pickedMinute} pm"
        }
    }
    pickedHour == 12 -> {
        if (pickedMinute < 10) {
            "${pickedHour}:0${pickedMinute} pm"
        } else {
            "${pickedHour}:${pickedMinute} pm"
        }
    }
    pickedHour == 0 -> {
        if (pickedMinute < 10) {
            "${pickedHour + 12}:0${pickedMinute} am"
        } else {
            "${pickedHour + 12}:${pickedMinute} am"
        }
    }
    else -> {
        if (pickedMinute < 10) {
            "${pickedHour}:0${pickedMinute} am"
        } else {
            "${pickedHour}:${pickedMinute} am"
        }
    }
}

fun setRemind(context: Context, time: Long) {
    val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val pendingRemindIntent = createPendingRemindIntent(context)
    am.setExact(
        AlarmManager.RTC_WAKEUP,
        time,
        pendingRemindIntent
    )
}

private fun createPendingRemindIntent(context: Context) =
    PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, RemindReceiver::class.java).apply {

        },
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
