package com.nick.kidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {
Button btnnumobj,btnalpaobj,butcolorobj,btnshapeobj;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        btnnumobj=findViewById(R.id.btnnum);
        btnalpaobj=findViewById(R.id.butalpa);
        butcolorobj=findViewById(R.id.butcolor);
        btnshapeobj=findViewById(R.id.btnshape);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnnumobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btnalpaobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(FirstActivity.this,AlphaActivity.class);
                startActivity(j);
            }
        });
        butcolorobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m= new Intent(FirstActivity.this,ColorActivity.class);
                startActivity(m);
            }
        });
        btnshapeobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n= new Intent(FirstActivity.this, ShapeActivity.class);
                startActivity(n);
            }
        });


    }
}