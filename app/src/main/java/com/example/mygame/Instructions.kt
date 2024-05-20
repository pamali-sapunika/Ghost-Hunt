package com.example.mygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Instructions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)

        //btn back
        val btnGetStarted: Button = findViewById(R.id.back)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this, TitleScreen::class.java)
            startActivity(intent)
        }
    }
}