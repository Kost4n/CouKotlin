package com.kost4n.coukotlin.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.kost4n.coukotlin.R
import com.kost4n.coukotlin.database.RecordDatabase
import com.kost4n.coukotlin.database.adapter.RecordRVAdapter
import com.kost4n.coukotlin.database.entity.RecordEntity
import com.kost4n.coukotlin.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



//    private lateinit var listView: ListView
    private val recordDatabase by lazy {  RecordDatabase.getDatabase(requireContext().applicationContext).getRecordDao() }
    private lateinit var calendar: Calendar
//    private lateinit var adapter: RecordRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.calendarView.setOnDateChangeListener {
                _, year, month, dayOfMonth ->

            calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)


            Toast.makeText(context, "${calendar.time}", Toast.LENGTH_SHORT).show()
        }

        binding.fab.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            val addDialog = inflater.inflate(R.layout.add_dialog, null)
            builder.setView(addDialog)

            val upPres: EditText = addDialog.findViewById<View>(R.id.input_up_pres) as EditText
            upPres.requestFocus()
            upPres.isFocusableInTouchMode = true
            val dwPres: EditText = addDialog.findViewById<View>(R.id.input_dw_pres) as EditText
            val pulse: EditText = addDialog.findViewById<View>(R.id.input_puls) as EditText

            builder.setCancelable(false)
            builder.setPositiveButton("Добавить") { _, _ ->
                calendar = Calendar.getInstance()

                lifecycleScope.launch {
                    val newRecord = RecordEntity(calendar.time, upPres.text.toString().toInt(),
                        dwPres.text.toString().toInt(), pulse.text.toString().toInt())
                    recordDatabase.addRecord(newRecord)
                }
            }

            builder.setNegativeButton("Отменить") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }



//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun observeRecords() {
//        lifecycleScope.launch {
//            recordDatabase.getRecords().collect {
//                if (it.isNotEmpty()) {
//                    adapter.submitList(it)
//                }
//            }
//        }
//    }
}
