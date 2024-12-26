package com.nick.kidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class AlphaActivity2 extends AppCompatActivity {

    private TextView questionText;
    private EditText answerInput;
    private Button submitButton;
    private TextView resultText;
    private TextView submittedAnswerText; // TextView to display the submitted answer
    private TextToSpeech textToSpeech; // TextToSpeech object

    private Map<String, String> questions;
    private String currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha2);

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
                    // Handle the case where the language is not supported
                }
            }
        });

        // Define questions and answers
        questions = new HashMap<>();
        questions.put("A is for?", "Apple");
        questions.put("B is for?", "Ball");
        questions.put("C is for?", "Cat");
        questions.put("D is for?", "Dog");

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
            speak("Correct!");
        } else {
            resultText.setText("Wrong!");
            Toast.makeText(this, "Wrong! Try again.", Toast.LENGTH_SHORT).show(); // Show Toast for wrong answer
            speak("Wrong! Try again.");
        }

        // Show the submitted answer
        submittedAnswerText.setText("Your answer: " + userAnswer);
        submittedAnswerText.setVisibility(View.VISIBLE); // Make it visible

        // Load the next question after a short delay (optional)
        new android.os.Handler().postDelayed(this::getRandomQuestion, 2000); // 2 seconds delay
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null); // Speak the text
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






//
//package com.nick.kidapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.speech.tts.TextToSpeech;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Random;
//
//public class AlphaActivity2 extends AppCompatActivity {
//
//    private TextView questionText;
//    private EditText answerInput;
//    private Button submitButton;
//    private TextView resultText;
//    private TextView submittedAnswerText; // TextView to display the submitted answer
//    private TextToSpeech textToSpeech; // TextToSpeech object
//
//    private Map<String, String> questions;
//    private String currentQuestion;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alpha2);
//
//        questionText = findViewById(R.id.questionText);
//        answerInput = findViewById(R.id.answerInput);
//        submitButton = findViewById(R.id.submitButton);
//        resultText = findViewById(R.id.resultText);
//        submittedAnswerText = findViewById(R.id.submittedAnswerText); // Initialize the new TextView
//
//        // Initialize TextToSpeech
//        textToSpeech = new TextToSpeech(this, status -> {
//            if (status == TextToSpeech.SUCCESS) {
//                int result = textToSpeech.setLanguage(Locale.US);
//                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                    // Handle the case where the language is not supported
//                }
//            }
//        });
//
//        // Define questions and answers
//        questions = new HashMap<>();
//        questions.put("A is for?", "Apple");
//        questions.put("B is for?", "Ball");
//        questions.put("C is for?", "Cat");
//        questions.put("D is for?", "Dog");
//
//        // Get a random question
//        getRandomQuestion();
//
//        submitButton.setOnClickListener(v -> checkAnswer());
//    }
//
//    private void getRandomQuestion() {
//        Object[] keys = questions.keySet().toArray();
//        currentQuestion = (String) keys[new Random().nextInt(keys.length)];
//        questionText.setText(currentQuestion);
//        answerInput.setText("");
//        resultText.setText("");
//        submittedAnswerText.setVisibility(View.GONE); // Hide the submitted answer TextView
//    }
//
//    private void checkAnswer() {
//        String userAnswer = answerInput.getText().toString().trim();
//        String correctAnswer = questions.get(currentQuestion);
//
//        // Check the user's answer without showing the correct answer
//        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
//            resultText.setText("Correct!");
//            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show(); // Show Toast for correct answer
//            speak("Correct!");
//        } else if (startsWithCorrectLetter(userAnswer, correctAnswer)) {
//            resultText.setText("Almost! But try again.");
//            Toast.makeText(this, "Almost! But try again.", Toast.LENGTH_SHORT).show(); // Show Toast for starting with correct letter
//            speak("Almost! But try again.");
//        } else {
//            resultText.setText("Wrong!");
//            Toast.makeText(this, "Wrong! Try again.", Toast.LENGTH_SHORT).show(); // Show Toast for wrong answer
//            speak("Wrong! Try again.");
//        }
//
//        // Show the submitted answer
//        submittedAnswerText.setText("Your answer: " + userAnswer);
//        submittedAnswerText.setVisibility(View.VISIBLE); // Make it visible
//
//        // Load the next question after a short delay (optional)
//        new android.os.Handler().postDelayed(this::getRandomQuestion, 2000); // 2 seconds delay
//    }
//
//    private boolean startsWithCorrectLetter(String userAnswer, String correctAnswer) {
//        // Check if user answer starts with the same letter as the correct answer
//        return !userAnswer.isEmpty() && userAnswer.charAt(0) == correctAnswer.charAt(0);
//    }
//
//    private void speak(String text) {
//        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null); // Speak the text
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (textToSpeech != null) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
//        super.onDestroy();
//    }
//}
