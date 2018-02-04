package com.arshaan.a4;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class GameInterfaceActivity extends AppCompatActivity implements
        Observer {
    Model model;
    TextView textScore;
    TextView textState;
    Button gameButton1;
    Button gameButton2;
    Button gameButton3;
    Button gameButton4;
    Button gameButton5;
    Button gameButton6;
    Button gamePlayAgainButton;

    // Undoes the temporary visibility change.
    void addAllButtons() {
        gameButton1.setVisibility(View.VISIBLE);
        gameButton2.setVisibility(View.VISIBLE);
        gameButton3.setVisibility(View.VISIBLE);
        gameButton4.setVisibility(View.VISIBLE);
        gameButton5.setVisibility(View.VISIBLE);
        gameButton6.setVisibility(View.VISIBLE);
    }
    // Just a temporary visibility change.
    void clearAllButtons() {
        gameButton1.setVisibility(View.GONE);
        gameButton2.setVisibility(View.GONE);
        gameButton3.setVisibility(View.GONE);
        gameButton4.setVisibility(View.GONE);
        gameButton5.setVisibility(View.GONE);
        gameButton6.setVisibility(View.GONE);
    }
    // While the Computer's turn, the Button's must be disabled to disallow
    // foul play.
    void enableAllButtons() {
        gameButton1.setEnabled(true);
        gameButton2.setEnabled(true);
        gameButton3.setEnabled(true);
        gameButton4.setEnabled(true);
        gameButton5.setEnabled(true);
        gameButton6.setEnabled(true);
    }
    // When the Computer's turn is over, the Button's must be enabled to
    // allow the player to play his/her turn.
    void disableAllButtons() {
        gameButton1.setEnabled(false);
        gameButton2.setEnabled(false);
        gameButton3.setEnabled(false);
        gameButton4.setEnabled(false);
        gameButton5.setEnabled(false);
        gameButton6.setEnabled(false);
    }
    // Upon GameState.WIN we will call the necessary functions and update the
    // View to continue to the next round, and upon a GameState.LOSE we will
    // call the necessary functions to end the game, and update the View to
    // allow the option to play another round.
    void checkWinLoss() {
        if (model.getGameState() == Model.GameState.WIN) {
            disableAllButtons();
            Handler handler = new Handler();
            // Calls these functions to update the view temporarily due to
            // winning state, lets the player know they won.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textState.setText("You WIN!");
                }
            }, 0);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textState.setText("Computer's Turn");
                    //enableAllButtons();
                    startTheGame();
                }
            }, 1000);
        }
        else if (model.getGameState() == Model.GameState.LOSE){
            clearAllButtons();
            gamePlayAgainButton.setVisibility(View.VISIBLE);
            gamePlayAgainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gamePlayAgainButton.setVisibility(View.GONE);
                    addAllButtons();
                    startTheGame();
                }
            });
        }
        else {
            return;
        }
    }
    // The verifyButton() below will set the state correctly at the end. If
    // incorrect it will set state to LOSE, if correct but not done
    // accepting all input we will still listen to clicks, if correct and
    // we have verified all, we set state to WIN.
    // Setting up click listeners for each button for verification if the
    // right button was clicked.
    void setButtonClickable() {
        enableAllButtons();
        gameButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(1);
                checkWinLoss();
            }
        });
        gameButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(2);
                checkWinLoss();
            }
        });
        gameButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(3);
                checkWinLoss();
            }
        });
        gameButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(4);
                checkWinLoss();
            }
        });
        gameButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(5);
                checkWinLoss();
            }
        });
        gameButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyButton(6);
                checkWinLoss();
            }
        });
    }
    // Sets up all the positions of the buttons on the screen based on how
    // many buttons have been clicked.
    void setupButtons() {
        ViewGroup layout = (ViewGroup) gameButton1.getParent();
        gamePlayAgainButton.setVisibility(View.GONE);
        if (model.getButtons() == 1) {
            gameButton1.setX(475);
            gameButton1.setY(750);
            gameButton1.setScaleX(5);
            gameButton1.setScaleY(5);
            layout.removeView(gameButton2);
            layout.removeView(gameButton3);
            layout.removeView(gameButton4);
            layout.removeView(gameButton5);
            layout.removeView(gameButton6);
        }
        else if (model.getButtons() == 2) {
            gameButton1.setX(475);
            gameButton1.setY(450);
            gameButton1.setScaleX(4);
            gameButton1.setScaleY(4);
            gameButton2.setX(475);
            gameButton2.setY(1100);
            gameButton2.setScaleX(4);
            gameButton2.setScaleY(4);
            layout.removeView(gameButton3);
            layout.removeView(gameButton4);
            layout.removeView(gameButton5);
            layout.removeView(gameButton6);
        }
        else if (model.getButtons() == 3) {
            gameButton1.setX(475);
            gameButton1.setY(350);
            gameButton1.setScaleX(3);
            gameButton1.setScaleY(3);
            gameButton2.setX(475);
            gameButton2.setY(750);
            gameButton2.setScaleX(3);
            gameButton2.setScaleY(3);
            gameButton3.setX(475);
            gameButton3.setY(1150);
            gameButton3.setScaleX(3);
            gameButton3.setScaleY(3);
            layout.removeView(gameButton4);
            layout.removeView(gameButton5);
            layout.removeView(gameButton6);
        }
        else if (model.getButtons() == 4) {
            gameButton1.setX(225);
            gameButton1.setY(450);
            gameButton1.setScaleX(3);
            gameButton1.setScaleY(3);
            gameButton2.setX(725);
            gameButton2.setY(450);
            gameButton2.setScaleX(3);
            gameButton2.setScaleY(3);
            gameButton3.setX(225);
            gameButton3.setY(950);
            gameButton3.setScaleX(3);
            gameButton3.setScaleY(3);
            gameButton4.setX(725);
            gameButton4.setY(950);
            gameButton4.setScaleX(3);
            gameButton4.setScaleY(3);
            layout.removeView(gameButton5);
            layout.removeView(gameButton6);
        }
        else if (model.getButtons() == 5) {
            gameButton1.setX(225);
            gameButton1.setY(400);
            gameButton1.setScaleX(3);
            gameButton1.setScaleY(3);
            gameButton2.setX(725);
            gameButton2.setY(400);
            gameButton2.setScaleX(3);
            gameButton2.setScaleY(3);
            gameButton3.setX(225);
            gameButton3.setY(800);
            gameButton3.setScaleX(3);
            gameButton3.setScaleY(3);
            gameButton4.setX(725);
            gameButton4.setY(800);
            gameButton4.setScaleX(3);
            gameButton4.setScaleY(3);
            gameButton5.setX(475);
            gameButton5.setY(1200);
            gameButton5.setScaleX(3);
            gameButton5.setScaleY(3);
            layout.removeView(gameButton6);
        }
        else {
            gameButton1.setX(225);
            gameButton1.setY(400);
            gameButton1.setScaleX(3);
            gameButton1.setScaleY(3);
            gameButton2.setX(725);
            gameButton2.setY(400);
            gameButton2.setScaleX(3);
            gameButton2.setScaleY(3);
            gameButton3.setX(225);
            gameButton3.setY(800);
            gameButton3.setScaleX(3);
            gameButton3.setScaleY(3);
            gameButton4.setX(725);
            gameButton4.setY(800);
            gameButton4.setScaleX(3);
            gameButton4.setScaleY(3);
            gameButton5.setX(225);
            gameButton5.setY(1200);
            gameButton5.setScaleX(3);
            gameButton5.setScaleY(3);
            gameButton6.setX(725);
            gameButton6.setY(1200);
            gameButton6.setScaleX(3);
            gameButton6.setScaleY(3);
        }
    }
    // This must be called at the beginning of every round, if will go
    // through the actions of the computer and then allow the user to act.
    void startTheGame() {
        // This function below will reset the game for you if in GameState.LOSE
        // but suppose player won last round, this will set up sequence again,
        // for another round.
        model.newRound();
        // This reference below is needed for .getColorStateList().
        final GameInterfaceActivity self = this;
        Handler handler = new Handler();
        // Allows me to stagger the handler.postDelayed() over time.
        int iteration = 1;
        int totalLoopTime = (((3000 / model.getDifficulty() + 1000) *
                                model.getLength()) + 1000);
        // Disables buttons so that Computer can run without interruption
        // from user.
        disableAllButtons();
        while (model.getGameState() == Model.GameState.COMPUTER) {
            int buttonClicked = model.nextButton();
            textState.setText("Computer's Turn");
            final Button buttonToAnimate;
            switch(buttonClicked) {
                case 1:
                    buttonToAnimate = gameButton1;
                    break;
                case 2:
                    buttonToAnimate = gameButton2;
                    break;
                case 3:
                    buttonToAnimate = gameButton3;
                    break;
                case 4:
                    buttonToAnimate = gameButton4;
                    break;
                case 5:
                    buttonToAnimate = gameButton5;
                    break;
                default:
                    buttonToAnimate = gameButton6;
                    break;
            }
            // Have the buttons flash 1 by 1.
            handler.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    buttonToAnimate.setBackgroundTintList(ContextCompat
                            .getColorStateList(self, R.color.colorPrimary));
                }
            }, 1000 + ((3000 / model.getDifficulty() + 1000) * (iteration - 1)));
            handler.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    buttonToAnimate.setBackgroundTintList(ContextCompat
                            .getColorStateList(self, R.color.colorButton));
                }
            }, (3000 / model.getDifficulty() + 1000)*iteration);
            iteration++;
        }
        //Allows user to act once all buttons have flashed.
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                setButtonClickable();
                textState.setText("Your Turn");
            }
        }, totalLoopTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_interface);

        model = Model.getInstance();
        model.addObserver(this);

        // Accessing references to the various widgets used.
        textScore = (TextView) findViewById(R.id.textScore);
        textState = (TextView) findViewById(R.id.textState);
        gameButton1 = (Button) findViewById(R.id.gameButton1);
        gameButton2 = (Button) findViewById(R.id.gameButton2);
        gameButton3 = (Button) findViewById(R.id.gameButton3);
        gameButton4 = (Button) findViewById(R.id.gameButton4);
        gameButton5 = (Button) findViewById(R.id.gameButton5);
        gameButton6 = (Button) findViewById(R.id.gameButton6);
        gamePlayAgainButton = (Button) findViewById(R.id.gamePlayAgainButton);
        // Used to remove unnecessary buttons altogether from interface and
        // give the rest a beautiful layout.
        setupButtons();
        // You will only call the below function once, at the beginning of
        // the game.
        model.initGame();
        startTheGame();
    }

    @Override
    public void update(Observable observable, Object o) {
        // Update Score.
        textScore.setText("SCORE: ".concat(String.valueOf(model.getScore())));
        // Update State.
        Model.GameState currGameState = model.getGameState();
        String stringGameState;
        if (currGameState == Model.GameState.START) {
            stringGameState = "Begin!";
        }
        else if (currGameState == Model.GameState.COMPUTER) {
            stringGameState = "Computer's Turn";
        }
        else if (currGameState == Model.GameState.HUMAN) {
            stringGameState = "Your Turn";
        }
        else if (currGameState == Model.GameState.LOSE) {
            stringGameState = "You LOSE!";
        }
        else if (currGameState == Model.GameState.WIN) {
            stringGameState = "You WIN!";
        }
        else {
            stringGameState = "UNKNOWN";
        }
        textState.setText(stringGameState);
    }
}
