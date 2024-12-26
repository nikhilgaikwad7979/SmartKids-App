package com.nick.kidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class shapActivity2 extends AppCompatActivity {

    TextView questionText;
    EditText answerInput;
    Button submitButton;
    TextView resultText;
    TextView submittedAnswerText; // TextView to display the submitted answer
    private TextToSpeech textToSpeech; // TextToSpeech object

    private Map<String, String> questions;
    private String currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shap2);

        questionText = findViewById(R.id.questionText);
        answerInput = findViewById(R.id.answerInput);
        submitButton = findViewById(R.id.submitButton);
        resultText = findViewById(R.id.resultText);
        submittedAnswerText = findViewById(R.id.submittedAnswerText); // Initialize the new TextView

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Define questions and answers
        questions = new HashMap<>();
        questions.put("What shape has three sides?", "Triangle");
        questions.put("What shape has four equal sides?", "Square");
        questions.put("What shape has four sides and opposite sides are equal?", "Rectangle");
        questions.put("What shape is round?", "Circle");
        questions.put("What shape has four equal angles and is not square?", "Rectangle");

        // Get a random question
        getRandomQuestion();

        submitButton.setOnClickListener(v -> checkAnswer());
    }

    private void getRandomQuestion() {
        Object[] keys = questions.keySet().toArray();
        currentQuestion = (String) keys[new Random().nextInt(keys.length)];
        questionText.setText(currentQuestion);
        answerInput.setText("");
        resultText.setText("");
        submittedAnswerText.setVisibility(View.GONE); // Hide the submitted answer TextView
    }

    private void checkAnswer() {
        String userAnswer = answerInput.getText().toString().trim();
        String correctAnswer = questions.get(currentQuestion);

        // Check the user's answer without showing the correct answer
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            resultText.setText("Correct!");
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show(); // Show Toast for correct answer
            speakOut("Correct!");
        } else {
            resultText.setText("Wrong!");
            Toast.makeText(this, "Wrong! Try again.", Toast.LENGTH_SHORT).show(); // Show Toast for wrong answer
            speakOut("Wrong! Try again.");
        }

        // Show the submitted answer
        submittedAnswerText.setText("Your answer: " + userAnswer);
        submittedAnswerText.setVisibility(View.VISIBLE); // Make it visible

        // Load the next question after a short delay (optional)
        new android.os.Handler().postDelayed(this::getRandomQuestion, 2000); // 2 seconds delay
    }

    private void speakOut(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
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
