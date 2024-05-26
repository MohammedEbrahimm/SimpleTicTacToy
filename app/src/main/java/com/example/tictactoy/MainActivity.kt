package com.example.tictactoy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoy.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // step 2
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }// step 2

    // This function is called when a button is clicked
    fun buSelect(view: View){
        // Get the button that was clicked
        val buChoice = view as Button
        var cellId = 0
        // Determine which button was clicked by its ID
        when(buChoice.id){
            binding.b1.id-> cellId=1
            binding.b2.id-> cellId=2
            binding.b3.id-> cellId=3
            binding.b4.id-> cellId=4
            binding.b5.id-> cellId=5
            binding.b6.id-> cellId=6
            binding.b7.id-> cellId=7
            binding.b8.id-> cellId=8
            binding.b9.id-> cellId=9
        }
        // Log the ID of the button that was clicked
        Log.d("cellId",cellId.toString())
        // Call the playgame function with the cell ID and button that was clicked
        playgame(cellId,buChoice)
    }

    // Create two lists to store the cells selected by each player and initialize the active player to player 1
    var  player1 =ArrayList<Int>()
    var  player2 =ArrayList<Int>()
    var  activePlayer  = 1

    // This function is called when a player selects a cell
    fun playgame(cellId:Int ,buChoice:Button ){
        // If it's player 1's turn, set the text of the button to X and the background color to blue, add the selected cell to player1's list, and switch to player 2's turn
        if (activePlayer == 1){
            buChoice.text = "X"
            buChoice.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer=2
            AutoPlay()
        }else{
            // If it's player 2's turn, set the text of the button to O and the background color to dark green, add the selected cell to player2's list, and switch to player 1's turn
            buChoice.text="O"
            buChoice.setBackgroundResource(R.color.darkgreen)
            player2.add(cellId)
            activePlayer=1

        }
        // Disable the button that was clicked
        buChoice.isEnabled=false
        // Check if there is a winner
        checkWinner()
    }

    // This function checks if there is a winner
    fun checkWinner(){
        var winner=-1

        // Check if player 1 has won by checking if they have selected cells 1, 2, and 3
        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner =1
        // Check if player 2 has won by checking if they have selected cells 1, 2, and 3
        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner=2

        // Continue checking for a winner in the same way for all possible winning combinations
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner =1
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner=2

        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner =1
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner=2

        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner =1
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner=2

        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner =1
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner=2

        if(player1.contains(3) && player1.contains(6)&& player1.contains(9))
            winner = 1
        if(player2.contains(3) && player2.contains(6) && player2.contains(9))
            winner = 2

        if(player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1
        if(player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner = 2

        if(player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner = 1
        if(player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner = 2

        // If there is a winner, show a toast message with the winner's name and disable all buttons to prevent further play
        if (winner!=-1){
            if (winner==1){
                Toast.makeText(this,"Player 1 wins the game",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Player 2 wins the game",Toast.LENGTH_LONG).show()
            }
            disableButtons()
            // ask if the user wants to play again
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to play again?")
                .setTitle("Game Over")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    resetGame()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
            val dialog = builder.create()
            dialog.show()
        }
    }

    // Disable all buttons
    fun disableButtons(){
        binding.b1.isEnabled=false
        binding.b2.isEnabled=false
        binding.b3.isEnabled=false
        binding.b4.isEnabled=false
        binding.b5.isEnabled=false
        binding.b6.isEnabled=false
        binding.b7.isEnabled=false
        binding.b8.isEnabled=false
        binding.b9.isEnabled=false
    }

    // This function resets the game
    fun resetGame(){
        binding.b1.isEnabled=true
        binding.b2.isEnabled=true
        binding.b3.isEnabled=true
        binding.b4.isEnabled=true
        binding.b5.isEnabled=true
        binding.b6.isEnabled=true
        binding.b7.isEnabled=true
        binding.b8.isEnabled=true
        binding.b9.isEnabled=true

        binding.b1.setBackgroundResource(android.R.drawable.btn_default)
        binding.b2.setBackgroundResource(android.R.drawable.btn_default)
        binding.b3.setBackgroundResource(android.R.drawable.btn_default)
        binding.b4.setBackgroundResource(android.R.drawable.btn_default)
        binding.b5.setBackgroundResource(android.R.drawable.btn_default)
        binding.b6.setBackgroundResource(android.R.drawable.btn_default)
        binding.b7.setBackgroundResource(android.R.drawable.btn_default)
        binding.b8.setBackgroundResource(android.R.drawable.btn_default)
        binding.b9.setBackgroundResource(android.R.drawable.btn_default)

        binding.b1.text=""
        binding.b2.text=""
        binding.b3.text=""
        binding.b4.text=""
        binding.b5.text=""
        binding.b6.text=""
        binding.b7.text=""
        binding.b8.text=""
        binding.b9.text=""

        player1.clear()
        player2.clear()
        activePlayer=1
    }

    // This function is called when it's the computer's turn
    fun AutoPlay(){
        val emptyCells = ArrayList<Int>()
        // Find all unselected cells and add them to the emptyCells list
        for (cellId in 1..9){
            if (!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        // If there are no empty cells, return
        if (emptyCells.isEmpty()){
            return
        }
        // Select a random empty cell and call the playgame function with the selected cell ID and a random button
        val randomIndex = Random.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]
        val buSelected:Button
        when(cellId){
            1->buSelected = binding.b1
            2->buSelected = binding.b2
            3->buSelected = binding.b3
            4->buSelected = binding.b4
            5->buSelected = binding.b5
            6->buSelected = binding.b6
            7->buSelected = binding.b7
            8->buSelected = binding.b8
            9->buSelected = binding.b9
            else->buSelected=binding.b1
        }
        playgame(cellId,buSelected)
    }
}