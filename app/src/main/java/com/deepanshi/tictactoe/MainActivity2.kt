package com.deepanshi.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.txtDisplay
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity(), View.OnClickListener{
    var PLAYER=true
    var TURN_COUNT=0

    var boardStatus=Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        board=arrayOf(
            arrayOf(btn00,btn01,btn02),
            arrayOf(btn10,btn11,btn12),
            arrayOf(btn20,btn21,btn22)
        )

        for (i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()
        btnReset.setOnClickListener {
            updateDisplay("Player X Turn")
            TURN_COUNT=0
            PLAYER=true
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        updateDisplay("Player X Turn")
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn00->{
                updateValue(row =0,col =0,player =PLAYER)
            }
            R.id.btn01->{
                updateValue(row =0,col =1,player =PLAYER)
            }
            R.id.btn02->{
                updateValue(row =0,col =2,player =PLAYER)
            }
            R.id.btn10->{
                updateValue(row =1,col =0,player =PLAYER)
            }
            R.id.btn11->{
                updateValue(row =1,col =1,player =PLAYER)
            }
            R.id.btn12->{
                updateValue(row =1,col =2,player =PLAYER)
            }
            R.id.btn20->{
                updateValue(row =2,col =0,player =PLAYER)
            }
            R.id.btn21->{
                updateValue(row =2,col =1,player =PLAYER)
            }
            R.id.btn22->{
                updateValue(row =2,col =2,player =PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER=!PLAYER

        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }

        if(TURN_COUNT>=3) checkWinner()
    }

    private fun checkWinner() {

        var lastChk=false
        for(i in 0..2){
            //Rows
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("YAY! PLAYER X WINS")
                    lastChk=true
                    disableButton()
                    break
                }
                else if(boardStatus[i][0]==0){
                    updateDisplay("YAY! PLAYER 0 WINS")
                    lastChk=true
                    disableButton()
                    break
                }
            }

            //Cols
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("YAY! PLAYER X WINS")
                    lastChk=true
                    disableButton()
                    break
                }
                else if(boardStatus[0][i]==0){
                    updateDisplay("YAY! PLAYER 0 WINS")
                    lastChk=true
                    disableButton()
                    break
                }
            }
        }

        //Primary Diagonal
        if(!lastChk) {
            if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[1][1]) {
                if (boardStatus[1][1] == 1) {
                    updateDisplay("YAY! PLAYER X WINS")
                    lastChk = true
                    disableButton()
                } else if (boardStatus[1][1] == 0) {
                    updateDisplay("YAY! PLAYER 0 WINS")
                    lastChk = true
                    disableButton()
                }
            }
        }

        //Secondary Diagonal
        if(!lastChk) {
            if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
                if (boardStatus[1][1] == 1) {
                    updateDisplay("YAY! PLAYER X WINS")
                    lastChk = true
                    disableButton()
                } else if (boardStatus[1][1] == 0) {
                    updateDisplay("YAY! PLAYER 0 WINS")
                    lastChk = true
                    disableButton()
                }
            }
        }

        if(!lastChk){
            if(TURN_COUNT==9){
                updateDisplay("Game Draw")
            }
        }
    }

    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(text: String) {
        txtDisplay.text=text
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply{
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}