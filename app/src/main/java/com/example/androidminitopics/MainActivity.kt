package com.example.androidminitopics

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoPlayerActivity = findViewById<Button>(R.id.videoPlayer)

        val audioPlayerActivity = findViewById<Button>(R.id.audioPlayer)

        val googleAdMobActivity = findViewById<Button>(R.id.googleAdMob)

        val webViewActivity = findViewById<Button>(R.id.showWebView)

        val notification = findViewById<Button>(R.id.button2)

        videoPlayerActivity.setOnClickListener {
            val intent = Intent(this,VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        audioPlayerActivity.setOnClickListener {
            val intent = Intent(this,AudioPlayerActivity::class.java)
            startActivity(intent)
        }

        googleAdMobActivity.setOnClickListener {
            val intent = Intent(this,GoogleAdMobActivity::class.java)
            startActivity(intent)
        }

        webViewActivity.setOnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            startActivity(intent)
        }


        notification.setOnClickListener {
            val intent = Intent(this,NotificationReciever::class.java)

            //pending intent is an intent that will be executed in future
            //get broadcast method of pending inent is used to create a pending intent that will start your broadcast when executed
            val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis().plus(5000),pendingIntent)
        }

    }
}