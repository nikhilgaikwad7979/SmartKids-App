package com.nick.kidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class colorActivity2 extends AppCompatActivity {

    private String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange"};
    private Button option1, option2, option3;
    private TextView colorText, scoreText;
    private int score = 0;
    private Random random;
    private TextToSpeech textToSpeech;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color2);

        colorText = findViewById(R.id.colorText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        scoreText = findViewById(R.id.scoreText);
        random = new Random();

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadNewQuestion();

        option1.setOnClickListener(v -> checkAnswer(option1.getText().toString()));
        option2.setOnClickListener(v -> checkAnswer(option2.getText().toString()));
        option3.setOnClickListener(v -> checkAnswer(option3.getText().toString()));
    }

    private void loadNewQuestion() {
        int correctAnswerIndex = random.nextInt(colors.length);
        colorText.setText(colors[correctAnswerIndex]);

        // Shuffle the options
        List<String> options = new ArrayList<>(Arrays.asList(colors));
        Collections.shuffle(options);
        options = options.subList(0, 2); // Take two random options
        options.add(colors[correctAnswerIndex]); // Add correct answer
        Collections.shuffle(options);

        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
    }

    private void checkAnswer(String selectedColor) {
        if (selectedColor.equals(colorText.getText().toString())) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            textToSpeech.speak("Correct!", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            String correctAnswer = colorText.getText().toString();
            Toast.makeText(this, "Wrong! The correct answer was " + correctAnswer, Toast.LENGTH_SHORT).show();
            textToSpeech.speak("Wrong! The correct answer was " + correctAnswer, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        scoreText.setText("Score: " + score);
        loadNewQuestion();
    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech on destroy
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
