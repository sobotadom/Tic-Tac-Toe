package com.example.sobotad.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;



public class MainActivity extends AppCompatActivity {


    private int player = 1;
    private int btncount = 0;
    private int[][] board;
    private int gamecount = 0;
    private int onewins = 0;
    private int twowins = 0;
    private int draws = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       SetBoard();

    }

    private void SetBoard(){
        ImageButton oneone = (ImageButton) findViewById(R.id.oneone);
        ImageButton onetwo = (ImageButton) findViewById(R.id.onetwo);
        ImageButton onethree = (ImageButton) findViewById(R.id.onethree);
        ImageButton twoone = (ImageButton) findViewById(R.id.twoone);
        ImageButton twotwo = (ImageButton) findViewById(R.id.twotwo);
        ImageButton twothree = (ImageButton) findViewById(R.id.twothree);
        ImageButton threeone = (ImageButton) findViewById(R.id.threeone);
        ImageButton threetwo = (ImageButton) findViewById(R.id.threetwo);
        ImageButton threethree = (ImageButton) findViewById(R.id.threethree);


        //set buttons enabled
        oneone.setEnabled(true);
        onetwo.setEnabled(true);
        onethree.setEnabled(true);
        twoone.setEnabled(true);
        twotwo.setEnabled(true);
        twothree.setEnabled(true);
        threeone.setEnabled(true);
        threetwo.setEnabled(true);
        threethree.setEnabled(true);

        oneone.setBackgroundResource(android.R.drawable.btn_default);
        onetwo.setBackgroundResource(android.R.drawable.btn_default);
        onethree.setBackgroundResource(android.R.drawable.btn_default);
        twoone.setBackgroundResource(android.R.drawable.btn_default);
        twotwo.setBackgroundResource(android.R.drawable.btn_default);
        twothree.setBackgroundResource(android.R.drawable.btn_default);
        threeone.setBackgroundResource(android.R.drawable.btn_default);
        threetwo.setBackgroundResource(android.R.drawable.btn_default);
        threethree.setBackgroundResource(android.R.drawable.btn_default);


        //set onclick listener
        oneone.setOnClickListener(btnlistener);
        onetwo.setOnClickListener(btnlistener);
        onethree.setOnClickListener(btnlistener);
        twoone.setOnClickListener(btnlistener);
        twotwo.setOnClickListener(btnlistener);
        twothree.setOnClickListener(btnlistener);
        threeone.setOnClickListener(btnlistener);
        threetwo.setOnClickListener(btnlistener);
        threethree.setOnClickListener(btnlistener);

        board = new int[][]{{0,0,0} ,{0,0,0} ,{0,0,0}};
        player = 1;
        btncount = 0;






    }
    private void OutOfMoves(){

        draws++;
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Play Again?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SetBoard();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {System.exit(0);}
                })
                .setTitle("Draw")
                .create();
        exit.show();




    }
    private void Winner(){

        if (player == 1){onewins++;}
        else if (player == 2){twowins++;}


        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Play Again?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SetBoard();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {System.exit(0);}
                })
                .setTitle("Player " + player + " Wins!")
                .create();
        exit.show();
    }
    private void setPosition(View v){
        if(v.getId() == R.id.oneone){board[0][0] = player;}
        else if (v.getId() == R.id.onetwo){board[0][1] = player;}
        else if (v.getId() == R.id.onethree){board[0][2] = player;}
        else if (v.getId() == R.id.twoone){board[1][0] = player;}
        else if (v.getId() == R.id.twotwo){board[1][1] = player;}
        else if (v.getId() == R.id.twothree){board[1][2] = player;}
        else if (v.getId() == R.id.threeone){board[2][0] = player;}
        else if (v.getId() == R.id.threetwo){board[2][1] = player;}
        else if (v.getId() == R.id.threethree){board[2][2] = player;}
        else{
            //ERROR
        }
    }

    private boolean checkWin(){
        boolean wins = false;
        TextView txt = (TextView) findViewById(R.id.playerlbl);
        for(int i = 0; i < 3;i++){

            if((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != 0)){
                //check the row

                return true;

            }
            else if((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != 0)){
                //check the column

                return true;

            }

        }

        //check diagnols
        if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != 0)){

            return true;

        }
        else if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[0][2] != 0)){

            return true;

        }

        return false;
    }


    private View.OnClickListener btnlistener = new View.OnClickListener() {


        public void onClick(View v) {
            ImageButton btn = (ImageButton) findViewById(v.getId());
            TextView txt = (TextView) findViewById(R.id.playerlbl);
            btn.setEnabled(false);
            btncount++;


            //set player position on array
            setPosition(v);

            if(checkWin()){
                Winner();
                gamecount++;
                TextView one = (TextView) findViewById(R.id.onewins);
                one.setText(Integer.toString(onewins));

                TextView two = (TextView) findViewById(R.id.twowins);
                two.setText(Integer.toString(twowins));

            }
            else{
                //Last button pressed
                if (btncount == 9){
                    OutOfMoves();
                    TextView draw = (TextView) findViewById(R.id.Draws);
                    draw.setText(Integer.toString(draws));
                }

                switch (player) {
                    case 1:
                        txt.setText(R.string.player2);
                        v.setBackgroundResource(R.drawable.x);
                        player++;
                        break;

                    case 2:
                        txt.setText(R.string.player1);
                        v.setBackgroundResource(R.drawable.cir);
                        player--;
                        break;


                }
            }



        }

        ;


    };

}
