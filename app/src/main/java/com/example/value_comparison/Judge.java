package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class Judge extends AppCompatActivity {

    static int quizCount;        // 問題数のカウント
    static int correctCount = 0; // 正解数のカウント
    //static int incorrectcount = 0; // 不正解数のカウント

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        // 前のActivityから送られたデータを変数に格納
        Intent intent = getIntent();
        boolean judge = intent.getBooleanExtra("RESULT", false);
        int number[] = intent.getIntArrayExtra("QUIZ_NUMBER");
        boolean check = intent.getBooleanExtra("CLEAR", true);
        //quizCount = intent.getIntExtra("QUIZ", 0);

        //if(check) quizCount = correctCount = incorrectcount = 0; // リセット処理
        if(check) quizCount = correctCount = 0;

        quizCount++;

        TextView title = findViewById(R.id.judge_title);
        if(judge){
            title.setText("正解");
            title.setTextColor(Color.RED);
            correctCount++;
        }else{
            title.setText("不正解");
            title.setTextColor(Color.BLUE);
            //incorrectcount++;
        }
        title.setTextSize(120);

        // 数を昇順にソート
        Arrays.sort(number);

        StringBuilder sb = new StringBuilder();
        for(int i = number.length - 1; i >= 0; i--){
            sb.append(number[i]);
            if(i!=0){
                sb.append(" > ");
            }
        }
        String explain = sb.toString();
        TextView ans = findViewById(R.id.op);
        ans.setText(explain);

    }

    public void onButton_result(View view) {
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("CORRECT",correctCount);
        //intent.putExtra("INCORRECT", incorrectcount);
        intent.putExtra("QUIZ", quizCount);
        startActivity(intent);
    }

    public void onButton_quiz(View view) {
        Intent intent = new Intent(this, Quiz3.class);
        intent.putExtra("CLEAR", false);
        startActivity(intent);
    }
}