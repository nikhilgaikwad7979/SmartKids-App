package com.nick.kidapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random; // Import Random class

public class tryActivity extends AppCompatActivity implements OnInitListener {

     TextView numberTextView;
     Random random;
     int currentRandomNumber;
     TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        numberTextView = findViewById(R.id.numberTextView);
        random = new Random(); // Initialize Random instance
        textToSpeech = new TextToSpeech(this, this); // Initialize TextToSpeech


        updateRandomNumber();

        int[] buttonIds = {
                R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9
        };


        for (int i = 0; i < buttonIds.length; i++) {
            final int number = i + 1;
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = button.getText().toString();
                    String correctText = getNumberWord(currentRandomNumber);

                    if (buttonText.equals(correctText)) {
                        showToast("Correct!");
                        speak("Correct!");
                    } else {
                        showToast("Wrong!");
                        speak("Wrong!");
                    }


                    updateRandomNumber();
                }
            });
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            }
        }
    }


    private void updateRandomNumber() {
        currentRandomNumber = random.nextInt(9) + 1;
        numberTextView.setText(String.valueOf(currentRandomNumber));
    }


    private String getNumberWord(int number) {
        switch (number) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

