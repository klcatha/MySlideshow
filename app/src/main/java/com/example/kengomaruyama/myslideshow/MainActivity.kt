package com.example.kengomaruyama.myslideshow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val resource = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
    )

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSwitcher.setFactory {
            ImageView(this)
        }
        imageSwitcher.setImageResource(resource[0])

        prevButton.setOnClickListener { movePosition(-1) }
        nextButton.setOnClickListener { movePosition(1) }
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
