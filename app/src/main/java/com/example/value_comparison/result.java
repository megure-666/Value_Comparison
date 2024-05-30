package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    boolean clear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int quizCount = intent.getIntExtra("QUIZ", 0);
        int correctCount = intent.getIntExtra("CORRECT", 0);
        //int incorrectCount = intent.getIntExtra("INCORRECT", 0);

        // 正解数の表示
        TextView details = findViewById(R.id.details);
        details.setTextSize(80);
        details.setText(String.valueOf(correctCount) + " / " + String.valueOf(quizCount));

        // 正答率の表示
        TextView percentage = findViewById(R.id.percentage);
        percentage.setTextSize(80);
        double val = ((double)correctCount / quizCount) * 100;
        String val_str = String.format("%.2f",val);
        percentage.setText(val_str + " %");
    }

    public void onButton_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }

    public void onButton_restart(View view) {
        Intent intent = new Intent(this, quiz.class);
        intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }
}