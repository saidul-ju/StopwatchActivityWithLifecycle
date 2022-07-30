package com.cse202202031.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this.getBaseContext(), "Hello, I am from the onCreate event.", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }

    /*@Override
    protected void onStart() {
        Toast.makeText(this.getBaseContext(), "Hello, I am from the onStart event.", Toast.LENGTH_LONG).show();
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }*/

    @Override
    protected void onResume() {
        Toast.makeText(this.getBaseContext(), "Hello, I am from the onResume event.", Toast.LENGTH_LONG).show();
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        Toast.makeText(this.getBaseContext(), "Hello, I am from the onPause event.", Toast.LENGTH_LONG).show();
        super.onPause();
        wasRunning = running;
        running = false;
    }

    /*@Override
    protected void onStop() {
        Toast.makeText(this.getBaseContext(), "Hello, I am from the onStop event.", Toast.LENGTH_LONG).show();
        super.onStop();
        wasRunning = running;
        running = false;
    }*/

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }
}