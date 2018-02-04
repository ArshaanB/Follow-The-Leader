package com.arshaan.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * CS349: A4
 * Created by Arshaan on 2017-11-28.
 */

public class MainActivity extends AppCompatActivity {
    Button gameInterfaceButton;
    Button settingsButton;
    MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Button references to add listeners.
        gameInterfaceButton = (Button) findViewById(R.id.buttonPlay);
        settingsButton = (Button) findViewById(R.id.buttonSettings);
        gameInterfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Transfer me to GameInterfaceActivity.
                // Create Intent to launch the activity.
                Intent intent = new Intent(self, GameInterfaceActivity.class);
                // Start activity
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Transfer me to SettingsActivity.
                // Create Intent to launch the activity.
                Intent intent = new Intent(self, SettingsActivity.class);
                // Start activity
                startActivity(intent);
            }
        });
    }
}
