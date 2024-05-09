package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class correct extends AppCompatActivity {

    int quizCount;        // 問題数のカウント
    int correctCount = 0; // 正解数のカウント

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);

        Intent intent = getIntent();
        int number[] = intent.getIntArrayExtra("QUIZ_NUMBER");
        quizCount = intent.getIntExtra("QUIZ", 0);

        correctCount++;

        // 数を昇順にソート
        Arrays.sort(number);

        // 可変長のString配列を作って文を表示させる
        // quiz.javaから取得した値を文字列として結合し1文の解説にする？
        TextView ans1 = (TextView) findViewById(R.id.op1);
        ans1.setText(String.valueOf(number[2]));
        ans1.setTextSize(80);
        TextView ans2 = (TextView) findViewById(R.id.op2);
        ans2.setText(String.valueOf(number[1]));
        ans2.setTextSize(80);
        TextView ans3 = (TextView) findViewById(R.id.op3);
        ans3.setText(String.valueOf(number[0]));
        ans3.setTextSize(80);

    }

    public void onButton_result(View view) {
        Intent intent = new Intent(this, result.class);
        intent.putExtra("CORRECT",correctCount);
        intent.putExtra("QUIZ", quizCount);
        startActivity(intent);
    }

    public void onButton_quiz(View view) {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);
    }
}