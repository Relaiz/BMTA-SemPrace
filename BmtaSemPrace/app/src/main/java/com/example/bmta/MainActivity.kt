package com.example.bmta
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class MainActivity : Activity(), View.OnClickListener {
    private val TAG = "DEBUG: "
    private val buttons = Array(3) { arrayOfNulls<Button>(3) }

    private var player1Turn = true

    private var roundCount = 0

    private var player1Points = 0
    private var player2Points = 0
    private var gameOver = false


    private val gamesList = mutableListOf<Game>()

    private lateinit var textViewPlayer1: TextView
    private lateinit var textViewPlayer2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
    }
    override fun onClick(v: View) {
        if (gameOver) {

            return
        }

        if (!(v as Button).text.toString().isEmpty()) {
            return
        }

        if (player1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }

        roundCount++

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        }  else {
            player1Turn = !player1Turn
        }
    }


    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }

        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]?.text.toString()
            }
        }

        for (i in 0..2) {
            if (field[i][0] == field[i][1] &&
                field[i][0] == field[i][2] &&
                !field[i][0].isNullOrEmpty()
            ) {
                Log.v(TAG, "WinOnRow")
                return true
            }
        }

        for (i in 0..2) {
            if (field[0][i] == field[1][i] &&
                field[0][i] == field[2][i] &&
                !field[0][i].isNullOrEmpty()
            ) {
                Log.v(TAG, "WinOnColumn")
                return true
            }
        }

        if (field[0][0] == field[1][1] &&
            field[0][0] == field[2][2] &&
            !field[0][0].isNullOrEmpty()
        ) {
            Log.v(TAG, "WinOnDiagonal")
            return true
        }

        if (field[0][2] == field[1][1] &&
            field[0][2] == field[2][0] &&
            !field[0][2].isNullOrEmpty()
        ) {
            Log.v(TAG, "WinOnInvertedDiagonal")
            return true
        }

        return false
    }
    private fun recordGame(winner: String) {
        val player1Name = textViewPlayer1.text.toString().substringAfter(": ").trim()
        val player2Name = textViewPlayer2.text.toString().substringAfter(": ").trim()
        val game = Game(player1Name, player2Name, winner)
        gamesList.add(game)
    }
    private fun player1Wins() {
        player1Points++
        val player1Name = textViewPlayer1.text.toString().substringBefore(": ").trim()
        Toast.makeText(this, "$player1Name wins!", Toast.LENGTH_LONG).show()
        gameOver = true
        updatePointsText()
        recordGame("$player1Name")
        //resetBoard();
    }

    private fun player2Wins() {
        player2Points++
        val player2Name = textViewPlayer2.text.toString().substringBefore(": ").trim()
        Toast.makeText(this, "$player2Name wins!", Toast.LENGTH_LONG).show()
        gameOver = true
        updatePointsText()
        recordGame("$player2Name")
        //resetBoard();
    }



    private fun updatePointsText() {
        val player1Name = textViewPlayer1.text.toString().substringBefore(": ").trim()
        val player2Name = textViewPlayer2.text.toString().substringBefore(": ").trim()
        textViewPlayer1.text = "$player1Name: $player1Points"
        textViewPlayer2.text = "$player2Name: $player2Points"
    }






}