package com.example.mygame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity2 : AppCompatActivity(),GameTask {
    lateinit var rootLayout : LinearLayout
    lateinit var layout2 : LinearLayout
    lateinit var nightbtn : Button
    lateinit var morningbtn : Button
    lateinit var mGameView : GameView
    lateinit var mGameView2 : GameView2
    lateinit var score : TextView
    lateinit var image: ImageView
    lateinit var gametext: TextView
    lateinit var highscore: TextView
    lateinit var topheader: LinearLayout
    lateinit var backgame: TextView
    private var HIGH_SCORE_KEY = "high_score"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //btn back
        val btnGetStarted: TextView = findViewById(R.id.backgame)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this, TitleScreen::class.java)
            startActivity(intent)
        }

        nightbtn = findViewById(R.id.nightbtn)
        morningbtn = findViewById(R.id.morningbtn)
        rootLayout = findViewById(R.id.rootLayout)
        layout2 = findViewById(R.id.layout2)
        score = findViewById(R.id.score)
        image = findViewById(R.id.image)
        gametext = findViewById(R.id.gametext)
        highscore = findViewById(R.id.highscore)
        topheader = findViewById(R.id.topheader)
        backgame = findViewById(R.id.backgame)

        val sharedPreferences = getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        val savedHighScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
        score.text = "Score : 0"
        highscore.text = "High Score : $savedHighScore"

        nightbtn.setOnClickListener{
            mGameView = GameView(this, this) // Initialize the GameView here
            mGameView.setBackgroundResource(R.drawable.nightsky3)
            rootLayout.addView(mGameView)
            nightbtn.visibility = View.GONE
            layout2.visibility = View.GONE
            score.visibility = View.GONE
            image.visibility = View.GONE
            gametext.visibility = View.GONE
            morningbtn.visibility = View.GONE
            topheader.visibility = View.GONE
            backgame.visibility = View.GONE
        }

        morningbtn.setOnClickListener{
            mGameView = GameView(this, this) // Initialize the GameView here
            mGameView.setBackgroundResource(R.drawable.sky1)
            rootLayout.addView(mGameView)
            nightbtn.visibility = View.GONE
            score.visibility = View.GONE
            image.visibility = View.GONE
            gametext.visibility = View.GONE
            morningbtn.visibility = View.GONE
            layout2.visibility = View.GONE
            topheader.visibility = View.GONE
            backgame.visibility = View.GONE
        }
    }



    override fun closeGame(mScore: Int) {
        score.text = "Score : $mScore"
        rootLayout.removeView(mGameView)
        nightbtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        image.visibility = View.VISIBLE
        gametext.visibility = View.GONE
        morningbtn.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        topheader.visibility = View.VISIBLE
        backgame.visibility = View.VISIBLE

        val sharedPreferences = getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        val highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)

        // Update high score if the current score is higher
        if (mScore > highScore) {
            sharedPreferences.edit().putInt(HIGH_SCORE_KEY, mScore).apply()
        }

        // Display high score
        val savedHighScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
        score.text = "Score : $mScore"
        highscore.text = "High Score : $savedHighScore"

        rootLayout.removeView(mGameView)
        nightbtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        image.visibility = View.VISIBLE
        gametext.visibility = View.GONE
        morningbtn.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        topheader.visibility = View.VISIBLE
        backgame.visibility = View.VISIBLE
        }

    override fun closeGame2(mScore: Int) {
        score.text = "Score : $mScore"
        rootLayout.removeView(mGameView)
        nightbtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        image.visibility = View.VISIBLE
        gametext.visibility = View.GONE
        morningbtn.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        topheader.visibility = View.VISIBLE
        backgame.visibility = View.VISIBLE

        val sharedPreferences = getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        val highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)

        // Update high score if the current score is higher
        if (mScore > highScore) {
            sharedPreferences.edit().putInt(HIGH_SCORE_KEY, mScore).apply()
        }

        // Display high score
        val savedHighScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
        score.text = "Score : $mScore"
        highscore.text = "High Score : $savedHighScore"

        rootLayout.removeView(mGameView)
        nightbtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        image.visibility = View.VISIBLE
        gametext.visibility = View.GONE
        morningbtn.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        topheader.visibility = View.VISIBLE
        backgame.visibility = View.VISIBLE
    }


}