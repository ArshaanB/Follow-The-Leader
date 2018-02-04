package com.arshaan.a4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    Model model;
    // All buttons in View are stored here.
    Button buttonEasy;
    Button buttonNormal;
    Button buttonHard;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        model = Model.getInstance();

        buttonEasy = (Button) findViewById(R.id.buttonEasy);
        buttonNormal = (Button) findViewById(R.id.buttonNormal);
        buttonHard = (Button) findViewById(R.id.buttonHard);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonSix = (Button) findViewById(R.id.buttonSix);

        // Controller to act upon button clicks.
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setDifficulty(3);
            }
        });
        buttonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setDifficulty(6);
            }
        });
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setDifficulty(10);
            }
        });
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(1);
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(2);
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(3);
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(4);
            }
        });
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(5);
            }
        });
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(6);
            }
        });
    }
}
