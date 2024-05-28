package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class judge extends AppCompatActivity {

    int quizCount;        // 問題数のカウント
    static int correctCount = 0; // 正解数のカウント
    static int incorrectcount = 0; // 不正解数のカウント

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        boolean judge = false;

        // 前のActivityから送られたデータを変数に格納
        Intent intent = getIntent();
        judge = intent.getBooleanExtra("RESULT", false);
        int number[] = intent.getIntArrayExtra("QUIZ_NUMBER");
        quizCount = intent.getIntExtra("QUIZ", 0);;

        TextView title = findViewById(R.id.judge_title);
        if(judge == true){
            title.setText("正解");
            title.setTextColor(Color.RED);
        }else{
            title.setText("不正解");
            title.setTextColor(Color.BLUE);
        }
        title.setTextSize(80);


        // 数を昇順にソート
        Arrays.sort(number);

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= 0; i--){
            sb.append(number[i]);
            sb.append(" ");
        }
        String explain = sb.toString();

        TextView ans = findViewById(R.id.op);
        ans.setText(explain);
        ans.setTextSize(80);
    }

    public void onButton_result(View view) {
        Intent intent = new Intent(this, result.class);
        intent.putExtra("CORRECT",correctCount);
        intent.putExtra("INCORRECT", incorrectcount);
        intent.putExtra("QUIZ", quizCount);
        startActivity(intent);
    }

    public void onButton_quiz(View view) {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);
    }
}