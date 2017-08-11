package com.tttx.tictactoextreme;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeXActivity extends Activity implements View.OnClickListener {


    final int PLAYER_1 = 1;
    final int PLAYER_2 = 2;
    boolean gameEnded = false;

    int player = PLAYER_1;
    TextView WinnerVIew;
    TextView Winner2View;
    TextView OView;
    TextView XView;
    TextView playerView;
    TextView player2View;
    TextView[][] field = new TextView[5][5];
    int[][] status = new int[5][5];
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tic_tac_toe_x);
        WinnerVIew=(TextView) findViewById(R.id.Winner);
        WinnerVIew.setVisibility(TextView.GONE);
        Winner2View=(TextView) findViewById(R.id.Winner2);
        Winner2View.setVisibility(TextView.GONE);
        XView=(TextView) findViewById(R.id.X);
        OView=(TextView) findViewById(R.id.O);
        player2View=(TextView) findViewById(R.id.player2);
        player2View.setVisibility(TextView.GONE);
        playerView = (TextView) findViewById(R.id.player);
        playerView.setVisibility(TextView.VISIBLE);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button clicked
                restart();
            }
        });
        field[0][0] = (TextView) findViewById(R.id.field00);
        field[0][1] = (TextView) findViewById(R.id.field01);
        field[0][2] = (TextView) findViewById(R.id.field02);
        field[0][3] = (TextView) findViewById(R.id.field03);
        field[0][4] = (TextView) findViewById(R.id.field04);

        field[1][0] = (TextView) findViewById(R.id.field10);
        field[1][1] = (TextView) findViewById(R.id.field11);
        field[1][2] = (TextView) findViewById(R.id.field12);
        field[1][3] = (TextView) findViewById(R.id.field13);
        field[1][4] = (TextView) findViewById(R.id.field14);

        field[2][0] = (TextView) findViewById(R.id.field20);
        field[2][1] = (TextView) findViewById(R.id.field21);
        field[2][2] = (TextView) findViewById(R.id.field22);
        field[2][3] = (TextView) findViewById(R.id.field23);
        field[2][4] = (TextView) findViewById(R.id.field24);

        field[3][0] = (TextView) findViewById(R.id.field30);
        field[3][1] = (TextView) findViewById(R.id.field31);
        field[3][2] = (TextView) findViewById(R.id.field32);
        field[3][3] = (TextView) findViewById(R.id.field33);
        field[3][4] = (TextView) findViewById(R.id.field34);

        field[4][0] = (TextView) findViewById(R.id.field40);
        field[4][1] = (TextView) findViewById(R.id.field41);
        field[4][2] = (TextView) findViewById(R.id.field42);
        field[4][3] = (TextView) findViewById(R.id.field43);
        field[4][4] = (TextView) findViewById(R.id.field44);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j].setOnClickListener(this);
                field[i][j].setTag(i * 5 + j);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (gameEnded == true) {
            return;
        }
        int num = (Integer) v.getTag();
        int i = num / 5;
        int j = num % 5;

        if (isFree(i, j)) {
            setStatus(i, j, player);
            if (didWIn(player) == true) {
                gameEnded = true;
                WinnerVIew.setVisibility(TextView.VISIBLE);
                WinnerVIew.setText(xo);
                Winner2View.setVisibility(TextView.VISIBLE);
                Winner2View.setText("WINNER");
                XView.setVisibility(TextView.INVISIBLE);
                OView.setVisibility(TextView.INVISIBLE);
                player2View.setVisibility(TextView.INVISIBLE);
                playerView.setVisibility(TextView.INVISIBLE);
            } else {
                changePlayer();
            }
        }
    }

    String xo="";

    void changePlayer() {
        if (player == PLAYER_1) {
            player = PLAYER_2;
            playerView.setVisibility(TextView.GONE);
            player2View.setVisibility(TextView.VISIBLE);
            xo="O";
        } else {
            player = PLAYER_1;
            player2View.setVisibility(TextView.GONE);
            playerView.setVisibility(TextView.VISIBLE);

            xo="X";
        }

    }

    boolean isFree(int i, int j) {
        if (status[i][j] == 0) {
            return true;
        } else {
            return false;
        }

    }

    void setStatus(int i, int j, int p) {
        status[i][j] = p;
        String text = "";
        if (p == PLAYER_1) {
            text = "X";

        } else {
            text = "O";
        }

        field[i][j].setText(text);
    }

    boolean didWIn(int p) {
        for (int i = 0; i < 5; i++) {
            int counter = 0;
            for (int j = 0; j < 5; j++) {
                if (status[i][j] == p) {
                    counter++;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            int counter = 0;
            for (int j = 0; j < 5; j++) {
                if (status[j][i] == p) {
                    counter++;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            if (status[i][i] == p) {
                counter++;
            }
            if (counter == 4) {
                return true;
            }
        }
        counter = 0;
        int j = 4;
        for (int i = 0; i < 5; i++) {
            if (status[i][j] == p) {

                counter++;
            }
            j--;
            if (counter == 4) {

                return true;
            }
        }
        counter = 0;
        j = 0;
        for (int i = 1; i <= 4; i++) {
            if (status[i][j] == p) {
                counter++;
            }
            j++;
            if (counter == 4) {
                return true;
            }
        }
        counter = 0;
        j = 1;
        for (int i = 0; i < 4; i++) {
            if (status[i][j] == p) {
                counter++;
            }
            j++;
            if (counter == 4) {
                return true;
            }
        }
        counter = 0;
        j = 3;
        for (int i = 0; i < 4; i++) {
            if (status[i][j] == p) {
                counter++;
            }
            j--;
            if (counter == 4) {
                return true;
            }
        }
        counter = 0;
        j = 4;
        for (int i = 1; i < 5; i++) {
            if (status[i][j] == p) {
                counter++;
            }
            j--;
            if (counter == 4) {
                return true;
            }
        }
        return false;
    }

    void restart() {
        gameEnded = false;
        player = PLAYER_1;
        playerView.setText("X");
        player2View.setText("O");
        WinnerVIew.setVisibility(TextView.GONE);
        Winner2View.setVisibility(TextView.GONE);
        playerView.setVisibility(TextView.VISIBLE);
        player2View.setVisibility(TextView.GONE);
        XView.setVisibility(TextView.VISIBLE);
        OView.setVisibility(TextView.VISIBLE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                status[i][j] = 0;
                field[i][j].setText("");
            }
        }
    }
}
