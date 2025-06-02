package com.toonandtools.githubapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            Log.d("NotificationWorker", "doWork() started") // Add this

            val title = inputData.getString("title") ?: "Delayed Notification"
            val message = inputData.getString("message") ?: "You missed a notification."
            Log.d("NotificationWorker", "Notification shown")


            val channelId = "default_channel"
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId, "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(System.currentTimeMillis().toInt(), notification)

            return Result.success()

        } catch (e: Exception) {
            Log.e("NotificationWorker", "Error: ${e.localizedMessage}")
            return Result.failure()
        }

    }
}
