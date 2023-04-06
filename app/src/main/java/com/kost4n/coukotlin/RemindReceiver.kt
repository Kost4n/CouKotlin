package com.kost4n.coukotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class RemindReceiver : BroadcastReceiver() {

    private var notificationChannel: NotificationChannel? = null
    private val notificationID: Int = 445
    private val notificationName: String = "Remind"

    override fun onReceive(context: Context?, intent: Intent?) {
        val nm = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val remindBuilder = createRemindBuilder(nm, context)
        val pendingRemindIntent = createPendingRemindIntent(context, intent)
        val notification = createNotification(remindBuilder, pendingRemindIntent)
        nm.notify(notificationID, notification)
    }

    private fun createRemindBuilder(nm: NotificationManager, context: Context) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(createChannel())
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        } else {
            NotificationCompat.Builder(context)
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(): NotificationChannel {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            notificationName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "remind notification"
        }
        notificationChannel = channel
        return channel
    }

    private fun createPendingRemindIntent(context: Context?, intent: Intent?) =
        PendingIntent.getActivity(
            context,
            NOTIFICATION_REQUEST_CODE,
            Intent(context, MainActivity::class.java).apply {
                // Какие-то экстра данные, которые нужны для отработки интента, например:
                // putExtra("имя экстры", intent?.getIntExtra("имя экстры", "дефолтное значение"))
            },
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

    private fun createNotification(
        remindBuilder: NotificationCompat.Builder,
        pendingRemindIntent: PendingIntent
    ): Notification {

        remindBuilder
            .setSmallIcon(R.drawable.notification)
//            .setTicker("")
            .setContentTitle("Надо померить давление")
//            .setContentText("Описание следующие строки")
            .setContentIntent(pendingRemindIntent)
            .setAutoCancel(true)

        return remindBuilder.build()
    }

    companion object {
        private const val NOTIFICATION_REQUEST_CODE = 25 // просто рандомный номер, но можно заморочиться с его выбором
        private const val NOTIFICATION_CHANNEL_ID = "имя канала для нотификаций"
    }

}