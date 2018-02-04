package com.arshaan.a4;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Arshaan on 2017-11-28.
 */

public class Model extends Observable {
    // Activity's get an instance of this model only.
    private static final Model ourInstance = new Model();
    static Model getInstance()
    {
        return ourInstance;
    }
    // Setting up the random number generator.
    Random rand = new Random();
    // These are the possible game states:
    // PAUSE - nothing happening
    // COMPUTER - computer is playing a sequence of buttons
    // HUMAN - human is guessing the sequence of buttons
    // LOSE and WIN - game is over in one of these states
    public enum GameState {
        START, COMPUTER, HUMAN, LOSE, WIN
    }
    // The current GameState, score, length of sequence, number of
    // possible buttons, the actual sequence, the current button, difficulty
    // of game.
    private GameState gameState;
    private int score;
    private int length;
    private int buttons = 4;
    ArrayList<Integer> sequence = new ArrayList<Integer>();
    int index;
    int difficulty = 6; // Where 3, 6 and 10 are Easy, Normal and Hard.
    // Getters and Setters as needed.
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState myGameState) {
        gameState = myGameState;
        setChanged();
        notifyObservers();
    }
    public int getScore() {
        return score;
    }
    public void setScore(int myScore) {
        score = myScore;
        setChanged();
        notifyObservers();
    }
    public void incrementScore() {
        score++;
        setChanged();
        notifyObservers();
    }
    public void setButtons(int numOfButtons) {
        this.buttons = numOfButtons;
    }
    public int getButtons() {
        return buttons;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public int getLength() {
        return length;
    }
    // Must be called at the beginning of any game.
    public void initGame() {
        length = 1;
        setGameState(GameState.START);
        setScore(0);
    }
    // Sets up the beginning of any round, registers the Computer's sequence
    // for the View's to access.
    void newRound() {
        // Reset if lost last time.
        if (gameState == GameState.LOSE) {
            length = 1;
            setScore(0);
        }
        sequence.clear();
        for (int i=0; i<length; i++) {
            int b = rand.nextInt(buttons) + 1;
            sequence.add(b);
        }
        index = 0;
        setGameState(GameState.COMPUTER);
    }
    // Call this to get the next button to "flash" when the computer is playing.
    int nextButton() {
        if (gameState != GameState.COMPUTER) return -1;
        int button = sequence.get(index);
        // Advance to next button.
        index++;
        // If all the buttons are shown, give the human a chance to guess the
        // sequence.
        if (index >= sequence.size()) {
            index = 0;
            setGameState(GameState.HUMAN);
        }
        return button;
    }
    // Verifies if the button clicked was the next required one in the sequence.
    boolean verifyButton(int button) {
        // Safety, just in case button is clicked during another GameState.
        if (gameState != GameState.HUMAN) {
            return false;
        }
        // Did they press the right button?
        boolean correct = (button == sequence.get(index));
        //Advance to the next button.
        index++;
        // Pushed the wrong buttons.
        if (!correct) {
            setGameState(GameState.LOSE);
        }
        // The got it right.
        else {
            // If last button, you win the round.
            if (index == sequence.size()) {
                setGameState(GameState.WIN);
                // Update the score and increase the difficulty.
                incrementScore();
                length++;
            }
        }
        return correct;
    }
}
