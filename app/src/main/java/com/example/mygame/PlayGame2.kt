package com.example.mygame

//GameView.kt
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView2(var c :Context, var gameTask: GameTask):View(c)
{
    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myCatPosition = 0
    private val ghostCats = ArrayList<HashMap<String,Any>>()

    var viewWidth = 0
    var viewHeight = 0
    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight

        // Calculate the width and height of the witchcat
        val witchcatWidth = viewWidth / 3
        var witchcatHeight = witchcatWidth + 10

        // Calculate the smaller size for the ghostcat
        val ghostcatWidth = witchcatWidth / 2
        val ghostcatHeight = witchcatHeight / 2

        if(time % 700 < 10 +speed){
            val map = HashMap<String,Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            ghostCats.add(map)
        }
        time = time + 10 + speed

        myPaint!!.style = Paint.Style.FILL
        val d = resources.getDrawable(R.drawable.witchcat,null)

        d.setBounds(
            myCatPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight-2 - witchcatHeight,
            myCatPosition * viewWidth / 3 + viewWidth / 15 + witchcatWidth - 25 ,
            viewHeight - 2
        )
        d.draw(canvas!!)
        myPaint!!.color = Color.GREEN
        var highScore = 0

        for (i in ghostCats.indices){
            try {
                val ghostcatX = ghostCats[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var ghostcatY =  time - ghostCats[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.ghostcat,null)

                // Draw the ghost cat with smaller dimensions
                d2.setBounds(
                    ghostcatX + 25 , ghostcatY - ghostcatHeight , ghostcatX + ghostcatWidth - 25 , ghostcatY
                )
                d2.draw(canvas)
                if (ghostCats[i]["lane"] as Int == myCatPosition){
                    if (ghostcatY > viewHeight - 2 - witchcatHeight
                        && ghostcatY < viewHeight - 2 ){

                        gameTask.closeGame2(score)
                    }
                }
                if (ghostcatY > viewHeight + witchcatHeight)
                {
                    ghostCats.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score / 8)
                    if (score > highScore){
                        highScore = score
                    }
                }
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
        myPaint!!.color = Color.BLACK
        myPaint!!.textSize = 40f
        canvas.drawText("Score : $score",80f,80f,myPaint!!)
        canvas.drawText("Speed : $speed",380f,80f,myPaint!!)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                val x1 = event.x
                if (x1 < viewWidth/2){
                    if (myCatPosition> 0){
                        myCatPosition--
                    }
                }
                if (x1 > viewWidth / 2){
                    if (myCatPosition<2){
                        myCatPosition++
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP ->{}
        }
        return true
    }

}