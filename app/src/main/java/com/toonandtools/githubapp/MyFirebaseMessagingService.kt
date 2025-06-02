package com.toonandtools.githubapp

import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.app.NotificationChannel
import android.util.Log

import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.TimeUnit

//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//
//        // Only show notification when in background or killed state
//        remoteMessage.notification?.let {
//            showNotification(it.title, it.body)
//        }
//    }
//
//    private fun showNotification(title: String?, message: String?) {
//        val channelId = "default_channel"
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId, "Default Channel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.logo)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setAutoCancel(true)
//            .build()
//
//        notificationManager.notify(0, notification)
//    }
//}
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Message received: ${remoteMessage.data}")


        // Extract notification payload
//        val title = remoteMessage.notification?.title ?: "Title"
//        val body = remoteMessage.notification?.body ?: "Message"

        val title = remoteMessage.data["title"] ?: "No Title"
        val body = remoteMessage.data["message"] ?: "No Message"

        if (isAppInForeground()) {
            // If app is in foreground, delay notification by 1 hour
            scheduleNotification(title, body, delayMinutes = 1)
        } else {
            // App is in background or killed, show immediately
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "default_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun isAppInForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false
        val packageName = packageName

        for (process in appProcesses) {
            if (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                process.processName == packageName) {
                return true
            }
        }
        return false
    }

    private fun scheduleNotification(title: String, message: String, delayMinutes: Long) {
        val data = Data.Builder()
            .putString("title", title)
            .putString("message", message)
            .build()
        Log.d("MyFirebaseService", "Worker scheduled with delay")

        Log.d("MyFirebaseService", "Scheduling worker with title: $title, message: $message")


        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(delayMinutes, TimeUnit.SECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}
