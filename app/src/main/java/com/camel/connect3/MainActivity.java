package com.camel.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean redTurn = true; // true = reds turn - false = yellows turn
    private boolean gameInProgress = true;

    /*2 = nothing currently there
    0 = red in slot
    1 = yellow in slot */
    private int[] gameState = {2, 2, 2,
                       2, 2, 2,
                       2, 2, 2};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        if (!gameInProgress) { //is the game over?
            return;
        }
        if (gameState[tag] != 2) { //is there already something there?
            return;
        } else { //if game is going and nothing is placed - place item
            counter.setTranslationY(-2500f); //move off screen
            counter.setImageResource(redTurn ? R.drawable.red : R.drawable.yellow); //set color depending on whos turn it is
            counter.animate().translationYBy(2500f).setDuration(300); //move back on screen
            gameState[tag] = redTurn ? 0 : 1;
            redTurn = !redTurn; //set to other players turn
            checkWin();
        }

    }

    //will restart the game after there is a winner
    public void restartGame(View view) {
        TextView winnerTxt = (TextView) findViewById(R.id.txtWinner);
        winnerTxt.setText(""); //clear winner text

        for (int i = 0; i < gameState.length; i++) { //reset the gamestate to start
            gameState[i] = 2;
        }
        redTurn = true; //red goes first

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout); //get our gridlayout

        for (int j = 0; j < gridLayout.getChildCount(); j++) { //clear all images inside the grid layout

            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);

        }
        gameInProgress = true; //the game is back in progress
    }

    private void setWinner(String winner) {
        gameInProgress = false;
        TextView winnerTxt = (TextView) findViewById(R.id.txtWinner);
        if (winner == "Red")
            winnerTxt.setText("Red player wins!");
        else
            winnerTxt.setText("Yellow player wins!");
    }

    private void checkWin() {

        if (gameState[0] == 1 && gameState[1] == 1 && gameState[2] == 1) {
            setWinner("Yellow");
        } else if (gameState[0] == 0 && gameState[1] == 0 && gameState[2] == 0) {
            setWinner("Red");
        } else if (gameState[3] == 1 && gameState[4] == 1 && gameState[5] == 1) {
            setWinner("Yellow");
        } else if (gameState[3] == 0 && gameState[4] == 0 && gameState[5] == 0) {
            setWinner("Red");
        } else if (gameState[6] == 1 && gameState[7] == 1 && gameState[8] == 1) {
            setWinner("Yellow");
        } else if (gameState[6] == 0 && gameState[7] == 0 && gameState[8] == 0) {
            setWinner("Red");
        }
        //side to side complete ^
        else if (gameState[0] == 1 && gameState[3] == 1 && gameState[6] == 1) {
            setWinner("Yellow");
        } else if (gameState[0] == 0 && gameState[3] == 0 && gameState[6] == 0) {
            setWinner("Red");
        } else if (gameState[1] == 1 && gameState[4] == 1 && gameState[7] == 1) {
            setWinner("Yellow");
        } else if (gameState[1] == 0 && gameState[4] == 0 && gameState[7] == 0) {
            setWinner("Red");
        } else if (gameState[2] == 1 && gameState[5] == 1 && gameState[8] == 1) {
            setWinner("Yellow");
        } else if (gameState[2] == 0 && gameState[5] == 0 && gameState[8] == 0) {
            setWinner("Red");
        }
        //up and down complete ^
        else if (gameState[0] == 1 && gameState[4] == 1 && gameState[8] == 1) {
            setWinner("Yellow");
        } else if (gameState[0] == 0 && gameState[4] == 0 && gameState[8] == 0) {
            setWinner("Red");
        } else if (gameState[2] == 1 && gameState[4] == 1 && gameState[6] == 1) {
            setWinner("Yellow");
        } else if (gameState[2] == 0 && gameState[4] == 0 && gameState[6] == 0) {
            setWinner("Red");
        }
        //diagnol complete ^
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
