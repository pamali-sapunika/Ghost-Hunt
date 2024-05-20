package com.example.mygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        //btn start hunting
        val starthunting: Button = findViewById(R.id.starthunting)

        starthunting.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        //btn instructions
        val instructions: ImageButton = findViewById(R.id.instructions)

        instructions.setOnClickListener {
            val intent = Intent(this, Instructions::class.java)
            startActivity(intent)
        }

        //btn instructions
        val textinstructions: TextView = findViewById(R.id.textinstruc)

        textinstructions.setOnClickListener {
            val intent = Intent(this, Instructions::class.java)
            startActivity(intent)
        }
    }
}