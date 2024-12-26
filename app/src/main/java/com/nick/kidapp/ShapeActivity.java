package com.nick.kidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ShapeActivity extends AppCompatActivity {
    ImageView img;
    Button btnimg, btnnext9; // Declare the next button
    int count;
    TextToSpeech textToSpeech;

    // Array to hold drawable resource IDs for images
    int[] imageResources = {
            R.drawable.img_16,
            R.drawable.img_10, // Circle
            R.drawable.img_11, // Rectangle
            R.drawable.img_12, // Triangle
            R.drawable.img_14, // Square
            R.drawable.img_13  // Pentagon
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shape);

        img = findViewById(R.id.img);
        btnimg = findViewById(R.id.btnimg);
        btnnext9 = findViewById(R.id.btnnext_9);
        count = 0;

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(ShapeActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set the initial image and speak the corresponding text
        img.setImageResource(imageResources[count]); // Set the first image
        Toast.makeText(ShapeActivity.this, "Circle", Toast.LENGTH_SHORT).show();
        textToSpeech.speak("Circle", TextToSpeech.QUEUE_FLUSH, null, null);

        btnimg.setOnClickListener(v -> {
            // Increment count and loop back to 0 if it exceeds the number of images
            count = (count + 1) % imageResources.length;

            String text;
            switch (count) {
                case 0:
                    text = "Circle";
                    break;
                case 1:
                    text = "Rectangle";
                    break;
                case 2:
                    text = "Triangle";
                    break;
                case 3:
                    text = "Square";
                    break;
                case 4:
                    text = "Pentagon";
                    break;
                default:
                    text = ""; // Fallback (should never happen)
            }

            img.setImageResource(imageResources[count]); // Set the image based on count
            Toast.makeText(ShapeActivity.this, text, Toast.LENGTH_SHORT).show();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null); // Speak the text
        });

        // Add OnClickListener for btnnext9
        btnnext9.setOnClickListener(v -> {
            try {
                // Start the next activity
                Intent intent = new Intent(ShapeActivity.this, shapActivity2.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(ShapeActivity.this, "Error starting activity: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
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
