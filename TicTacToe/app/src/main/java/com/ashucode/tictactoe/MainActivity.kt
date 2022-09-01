package com.ashucode.tictactoe

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var b0 : Button
    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var tv : TextView

    var player1 = 0
    var player2 = 1
    var activeplayer = player1
    lateinit var filledpos : IntArray
    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filledpos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        b0 = findViewById(R.id.a1)
        b1 = findViewById(R.id.a2)
        b2 = findViewById(R.id.a3)
        b3 = findViewById(R.id.b1)
        b4 = findViewById(R.id.b2)
        b5 = findViewById(R.id.b3)
        b6 = findViewById(R.id.c1)
        b7 = findViewById(R.id.c2)
        b8 = findViewById(R.id.c3)

        tv = findViewById(R.id.turnTV)

        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(!gameActive)
            return

        var btn_clicked = findViewById<Button>(v!!.id)
        var clickedTag = Integer.parseInt(btn_clicked.tag.toString())

        if(filledpos[clickedTag] != -1)
            return

        filledpos[clickedTag] = activeplayer
        if(activeplayer == player1)
        {
            btn_clicked.setText("O")
            activeplayer = player2
            tv.setText("X's Turn - Tap to play")
        }
        else
        {
            btn_clicked.setText("X")
            activeplayer = player1
            tv.setText("O's Turn - Tap to play")
        }
        checkForWin()
    }

    private fun checkForWin() {
        var winPos = arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),
                            intArrayOf(0,3,6),intArrayOf(1,4,7),intArrayOf(2,5,8),
                            intArrayOf(0,4,8),intArrayOf(2,4,6))

        for(i in 0 until winPos.size)
        {
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if(filledpos[val0] == filledpos[val1] && filledpos[val1] == filledpos[val2])
            {
                if(filledpos[val0]!=-1) {
                    gameActive = false
                    if (filledpos[val0] == player1) {
                        showMsg("Player O is winner")
                    } else {
                        showMsg("Player X is winner")
                    }
                    return
                }
            }
        }
        var count =0
        for(i in 0 until filledpos.size)
        {
            if(filledpos[i]== -1)
            {
                count++
            }
        }
        if(count == 0)
        {
            showMsg("It's Draw")
            return
        }
    }

    private fun showMsg(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart Game", DialogInterface.OnClickListener{dialog, which ->
                restartGame()
            })
            .show()

    }

    private fun restartGame() {
        filledpos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activeplayer = player1
        gameActive = true
        tv.setText("Turn O")
        b0.setText("")
        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
    }
}