package com.example.androidminitopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var chooseFile: Button
    private lateinit var playPause:Button
    private lateinit var seekBar: SeekBar
    private val PICK_VIDEO_REQUEST_CODE =1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoView = findViewById(R.id.videoView)
        chooseFile = findViewById(R.id.chooseFileButton)
        seekBar = findViewById(R.id.seekBar)
        playPause = findViewById(R.id.playPause)

        chooseFile.setOnClickListener {
            pickVideoFromGallery()
        }

        playPause.setOnClickListener {
            playPauseVideoPlayer()
        }

        videoView.setOnPreparedListener {
            videoPLayer ->
            seekBar.max= videoPLayer.duration
        }

        seekBar.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2) {
                    videoView.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                videoView.pause()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                videoView.start()
            }

        })
    }

    private fun playPauseVideoPlayer() {
        if(videoView?.isPlaying()== true) {
            videoView?.pause()
            playPause.setText("Play")
        } else {
            videoView?.start()
            playPause.setText("Pause")

        }
    }


    private fun startVideoPlayer() {
        videoView.start()

        //update the seekbar every second

        val duration = videoView?.duration ?: 0

        seekBar.max = duration

        Timer().scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                runOnUiThread {
                    val currentPosition = videoView?.currentPosition?:0
                    seekBar.progress= currentPosition
                }
            }
        }, 0,1000)


    }

    private fun pickVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="video/*"
        startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == RESULT_OK) {
            val videoUri = data?.data
            videoView.requestFocus()
            videoView.setVideoURI(videoUri)


            videoView?.setOnCompletionListener {
                videoView?.seekTo(0)
                seekBar.progress=0
            }

            startVideoPlayer()
        }
    }

    private fun stopVideoPlayer() {
        videoView.stopPlayback()
        seekBar.progress = 0

    }


    override fun onStop() {
        super.onStop()
        stopVideoPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}