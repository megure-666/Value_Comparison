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
        //int number = intent.getIntExtra("QUIZ_NUMBER",-100);

        String strNum[] = new String[3];
        for(int i = 0;i < 3;i++){
            strNum[i] = Integer.valueOf(number[i]).toString();
        }

        TextView ans1 = (TextView) findViewById(R.id.op1);
        ans1.setText(String.valueOf(number[0]));
        ans1.setTextSize(60);
        TextView ans2 = (TextView) findViewById(R.id.op2);
        ans2.setText(String.valueOf(number[1]));
        ans2.setTextSize(60);
        TextView ans3 = (TextView) findViewById(R.id.op3);
        ans3.setText(String.valueOf(number[2]));
        ans3.setTextSize(60);

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