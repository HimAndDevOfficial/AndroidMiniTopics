package com.example.androidminitopics

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar

class AudioPlayerActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    private lateinit var selectAudioButton : Button
    private lateinit var playPauseButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)

        mediaPlayer = MediaPlayer()

        selectAudioButton = findViewById(R.id.select_audio_button)
        playPauseButton = findViewById(R.id.play_pause_button)

        selectAudioButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type="audio/*"
            startActivityForResult(intent,1)
        }

        playPauseButton.setOnClickListener {
            if(isPlaying) {
                mediaPlayer.pause()
                isPlaying= false
                playPauseButton.text = "Play"
            } else {
                mediaPlayer.start()
                isPlaying= true
                playPauseButton.text = "Pause"
            }
        }

        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
            playPauseButton.text= "Pause"
            isPlaying = true
        }

        mediaPlayer.setOnCompletionListener {
            isPlaying = false
            playPauseButton.text= "Play"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1 && resultCode== RESULT_OK) {
            val uri = data?.data?:return
            mediaPlayer.reset()
            mediaPlayer.setDataSource(applicationContext,uri)
            mediaPlayer.prepare()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}