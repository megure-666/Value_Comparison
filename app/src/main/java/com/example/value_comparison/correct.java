package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class correct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);

        Intent intent = getIntent();
        int number[] = intent.getIntArrayExtra("QUIZ_NUMBER");

        TextView op1 = (TextView) findViewById(R.id.op1);
        op1.setText(number[0]);
        op1.setTextSize(60);
        TextView op2 = (TextView) findViewById(R.id.op2);
        op2.setText(number[1]);
        op2.setTextSize(60);
        TextView op3 = (TextView) findViewById(R.id.op3);
        op3.setText(number[2]);
        op3.setTextSize(60);
    }

    public void onButton_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onButton_quiz(View view) {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);
    }
}