package com.nick.kidapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ColorActivity extends AppCompatActivity {

    EditText edtText;
    TextToSpeech textToSpeech;
    Button btnnext_3; // Declare the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color);

        edtText = findViewById(R.id.edtText);
        btnnext_3 = findViewById(R.id.btnnext_3); // Initialize the button

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle the case where the language is not supported
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setButtonListeners();

        // Set the click listener for the next button
        btnnext_3.setOnClickListener(v -> {
            Intent intent = new Intent(ColorActivity.this, colorActivity2.class);
            startActivity(intent);
        });
    }

    private void setButtonListeners() {
        findViewById(R.id.button1).setOnClickListener(v -> showColorName("White", Color.WHITE));
        findViewById(R.id.button2).setOnClickListener(v -> showColorName("Red", Color.parseColor("#F40F0F")));
        findViewById(R.id.button3).setOnClickListener(v -> showColorName("Cyan", Color.parseColor("#3CFFF1")));
        findViewById(R.id.button4).setOnClickListener(v -> showColorName("Magenta", Color.parseColor("#EF17EB")));
        findViewById(R.id.button5).setOnClickListener(v -> showColorName("Green", Color.parseColor("#40F832")));
        findViewById(R.id.button6).setOnClickListener(v -> showColorName("Yellow", Color.parseColor("#FFE91B")));
        findViewById(R.id.button7).setOnClickListener(v -> showColorName("Blue", Color.parseColor("#0D42E2")));
        findViewById(R.id.button26).setOnClickListener(v -> showColorName("Orange", Color.parseColor("#FD470D")));
        findViewById(R.id.button8).setOnClickListener(v -> showColorName("Sky Blue", Color.parseColor("#03A9F4")));
    }

    private void showColorName(String colorName, int color) {
        edtText.setText(colorName);
        edtText.setBackgroundColor(color); // Optional: Change the background color of EditText to the color selected

        // Speak the color name
        textToSpeech.speak(colorName, TextToSpeech.QUEUE_FLUSH, null, null);
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
