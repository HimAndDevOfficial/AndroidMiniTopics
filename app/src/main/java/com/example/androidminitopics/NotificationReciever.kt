package com.example.androidminitopics

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver

@SuppressLint("RestrictedApi")
class NotificationReciever : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    override fun onReceive(context: Context, intent: Intent?) {

        //creating your own notification channel

        val channelId="10001"

        val channelName="default"
        val channel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

    //building the notification
        val builder = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My Notification")
            .setContentText("This is example of local notification")

        //show the notification
        notificationManager.notify(0,builder.build())

    }
}