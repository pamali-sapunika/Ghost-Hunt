package com.example.mygame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView(var c :Context, var gameTask: GameTask):View(c)
{
    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myCatPosition = 0
    private val ghostCats = ArrayList<HashMap<String,Any>>()

    var viewWidth = 0 //width and height of view
    var viewHeight = 0
    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth //Actual width and height of view
        viewHeight = this.measuredHeight

        //A new ghost cat adding rate
        if(time % 700 < 10 +speed){
            val map = HashMap<String,Any>()
            map["lane"] = (0..2).random()  //3 lanes
            map["startTime"] = time
            ghostCats.add(map)
        }
        time = time + 10 + speed  //progression of the time inside the game
        val witchCatWidth = viewWidth / 5
        var witchCatHeight = witchCatWidth + 10
        myPaint!!.style = Paint.Style.FILL  //sets the paint style
        val d = resources.getDrawable(R.drawable.witchcat,null)

        d.setBounds( //witchcat sizes boundaries
            myCatPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight-2 - witchCatHeight,
            myCatPosition * viewWidth / 3 + viewWidth / 15 + witchCatWidth - 25 ,
            viewHeight - 2
        )
        d.draw(canvas!!)
        myPaint!!.color = Color.GREEN
        var highScore = 0

        //Checks if ghist cat and witch cat meets
        for (i in ghostCats.indices){
            try {
                //horizontal and vertical position of ghost cat
                val ghostX = ghostCats[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var ghostY =  time - ghostCats[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.ghostcat,null)

                d2.setBounds(
                    ghostX + 25 , ghostY - witchCatHeight , ghostX + witchCatWidth - 25 , ghostY  //drawing on the canvas
                )
                d2.draw(canvas)

                //player lose thing
                if (ghostCats[i]["lane"] as Int == myCatPosition){  //if same lane
                    if (ghostY > viewHeight - 2 - witchCatHeight  //vertical position of the ghost cat with player
                        && ghostY < viewHeight - 2 ){

                        gameTask.closeGame(score)
                    }
                }
                if (ghostY > viewHeight + witchCatHeight) //ghost cats are removed from the list
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
        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 40f
        canvas.drawText("Score : $score",80f,80f,myPaint!!)  //current score on canvas
        canvas.drawText("Speed : $speed",380f,80f,myPaint!!)
        invalidate()



    }

    //Moving player cat right left
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                val x1 = event.x  //x coordinate of the touch
                if (x1 < viewWidth/2){  //touch in left half of screen
                    if (myCatPosition> 0){
                        myCatPosition--  //decrement to move left
                    }
                }
                if (x1 > viewWidth / 2){
                    if (myCatPosition<2){
                        myCatPosition++
                    }
                }
                invalidate()  //redraw the cat position
            }
            MotionEvent.ACTION_UP ->{}
        }
        return true
        }

}