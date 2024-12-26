package com.nick.kidapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Locale;

public class AlphaActivity extends AppCompatActivity {

    TextView numberTextView;
    Button btnNext1obj;
    TextToSpeech textToSpeech;

    // Mapping of letters to fruit names
    private HashMap<String, String> fruitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha); // Ensure this matches your layout XML filename

        numberTextView = findViewById(R.id.numberTextView);
        btnNext1obj = findViewById(R.id.btnnext1);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle the case where the language is not supported
                }
            }
        });

        // Initialize fruit mapping
        fruitMap = new HashMap<>();
        fruitMap.put("A", "Apple");
        fruitMap.put("B", "Banana");
        fruitMap.put("C", "Cherry");
        fruitMap.put("D", "Dog");
        fruitMap.put("E", "Elephant");
        fruitMap.put("F", "Frog");
        fruitMap.put("G", "Got");
        fruitMap.put("H", "Home");
        fruitMap.put("I", "Indian");
        fruitMap.put("J", "Jackfruit");
        fruitMap.put("K", "Kiwi");
        fruitMap.put("L", "Lemon");
        fruitMap.put("M", "Mango");
        fruitMap.put("N", "Nectarine");
        fruitMap.put("O", "Orange");
        fruitMap.put("P", "Papaya");
        fruitMap.put("Q", "Quince");
        fruitMap.put("R", "Raspberry");
        fruitMap.put("S", "Strawberry");
        fruitMap.put("T", "Tomato");
        fruitMap.put("U", "Umbrella");
        fruitMap.put("V", "Village");
        fruitMap.put("W", "Watermelon");
        fruitMap.put("X", "Xigua");
        fruitMap.put("Y", "Yellow Passion Fruit");
        fruitMap.put("Z", "Zucchini");

        // Create an array of button IDs
        int[] buttonIds = {
                R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9,
                R.id.button10, R.id.button11, R.id.button12,
                R.id.button13, R.id.button14, R.id.button15,
                R.id.button16, R.id.button17, R.id.button18,
                R.id.button19, R.id.button20, R.id.button21,
                R.id.button22, R.id.button23, R.id.button24,
                R.id.button25, R.id.button26
        };

        // Set click listeners for each button
        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(v -> {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();
                numberTextView.setText(buttonText);

                // Check if there's a corresponding fruit name
                String fruitName = fruitMap.get(buttonText);
                if (fruitName != null) {
                    // Speak the corresponding fruit name
                    textToSpeech.speak(buttonText + " for " + fruitName, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });
        }

        // Set click listener for btnNext1obj
        btnNext1obj.setOnClickListener(v -> {
            // Handle the button click event, e.g., navigate to the next activity
            Intent intent = new Intent(AlphaActivity.this, AlphaActivity2.class);
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
