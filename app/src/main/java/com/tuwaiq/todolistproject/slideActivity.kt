package com.tuwaiq.todolistproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class slideActivity : AppCompatActivity() {
    private lateinit var backgroundImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

         backgroundImg =findViewById(R.id.iv_logo)
         backgroundImg.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide))
            Handler().postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
            }, 2000)
    }
}