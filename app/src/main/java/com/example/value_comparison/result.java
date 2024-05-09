package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int quizCount = intent.getIntExtra("QUIZ", 0);
        int correctCount = intent.getIntExtra("CORRECT", 0);
        int incorrectCount = intent.getIntExtra("INCORRECT", 0);
    }

    public void onButton_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onButton_restart(View view) {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);
    }
}