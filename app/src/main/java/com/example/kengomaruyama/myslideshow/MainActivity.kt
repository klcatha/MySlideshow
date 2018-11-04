package com.example.kengomaruyama.myslideshow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnClickListener {
            it.animate().apply {
                duration = 1000L
                interpolator = BounceInterpolator()
                y(it.y + 100.0f)
            }
        }
    }
}
