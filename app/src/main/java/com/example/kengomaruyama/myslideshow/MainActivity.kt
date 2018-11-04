package com.example.kengomaruyama.myslideshow

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private val resource = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
    )

    private var position = 0
    private var isSlideshow = false
    private val handler = Handler()
    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSwitcher.setFactory {
            ImageView(this)
        }
        imageSwitcher.setImageResource(resource[0])

        prevButton.setOnClickListener {
            imageSwitcher.setInAnimation(this, android.R.anim.fade_in)
            imageSwitcher.setOutAnimation(this, android.R.anim.fade_out)
            movePosition(-1)
        }
        nextButton.setOnClickListener {
            imageSwitcher.setInAnimation(this, android.R.anim.slide_in_left)
            imageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right)
            movePosition(1)
        }

        timer(period = 5000) {
            handler.post {
                if (isSlideshow) movePosition(1)
            }
        }
        slideshowButton.setOnClickListener {
            isSlideshow = !isSlideshow
        }

        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true

        when (isSlideshow) {
            true -> player.start()
            false -> player.apply {
                pause()
                seekTo(0)
            }
        }
    }

    private fun movePosition(move: Int){
        position += move
        if (position >= resource.size) {
            position = 0
        } else if (position < 0){
            position = resource.size - 1
        }
        imageSwitcher.setImageResource(resource[position])
    }
}
