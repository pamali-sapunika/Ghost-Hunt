package com.example.mygame

//MainActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    private val DELAY_DURATION: Long = 4000 // 9 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AUTO NAVIGATION PROCESS
        Handler().postDelayed({
            // Start the GetStartedScreen activity
            val intent = Intent(this@MainActivity, TitleScreen::class.java)
            startActivity(intent)

            // Finish the LaunchActivity to prevent it from being accessed through the back button
            finish()
        }, DELAY_DURATION)

        //GIF TO LIFE
//        Thread{
//            val source : ImageDecoder.Source= ImageDecoder.createSource(resources, R.drawable.logocat)
//
//            val drawable : Drawable = ImageDecoder.decodeDrawable(source)
//
//            val imageView: ImageView = findViewById<ImageView>(R.id.logocatid)
//            imageView.post{
//                imageView.setImageDrawable(drawable)
//                (drawable as? AnimationDrawable)?.start()
//            }
//        }.start()


    }
}