package com.example.netflix.Activity

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import com.example.netflix.R

class PlayMovieActivity : AppCompatActivity() {

    lateinit var videoView: VideoView
    lateinit var progressBar : ProgressBar


    lateinit var videoUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_movie)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        videoView = findViewById(R.id.videoView)

        videoView.setKeepScreenOn(true)

         progressBar = findViewById(R.id.progress_bar)

        videoUrl = intent.getStringExtra("MovieUrlPlay").toString()

        val uri: Uri = Uri.parse(videoUrl)

        videoView.setVideoURI(uri)


        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
        }



        val mediaController = MediaController(this)

        mediaController.setAnchorView(videoView)

        mediaController.setMediaPlayer(videoView)

        videoView.setMediaController(mediaController)

                videoView.setOnInfoListener { _, what, _ ->
            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                progressBar.visibility = View.VISIBLE
            } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                progressBar.visibility = View.GONE
            }
            true
        }

        videoView.start()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Stop Watching")
            .setMessage("Do You Want To Stop Watching?")
            .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                super.onBackPressed()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

            }
            .show()
    }
}