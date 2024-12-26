package com.nick.kidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView numberTextView;
    private TextToSpeech textToSpeech;

    // Array of number words
    String[] numberWords = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    Button btnnextovj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView = findViewById(R.id.numberTextView);
        btnnextovj = findViewById(R.id.btnnext);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle the case where the language is not supported
                }
            }
        });

        // Create an array of button IDs
        int[] buttonIds = {
                R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9
        };

        // Set click listeners for each button
        for (int i = 0; i < buttonIds.length; i++) {
            final int index = i; // Use the index to access numberWords
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberTextView.setText(numberWords[index]);
                    // Speak the number word
                    textToSpeech.speak(numberWords[index], TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });
        }

        // Set click listener for the next button
        btnnextovj.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), tryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
