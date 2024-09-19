package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class quiz extends AppCompatActivity {

    int quizNumber[] = new int[3];      // 乱数生成用の配列
    String strNumber[] = new String[3]; // 文字列に変換した数字用の配列
    int maxNumber = 0;                  // 最大値の配列の番号を保持する変数
    //static int quizCount = 0;                  // 問題数のカウント
    String maxNumberStr = "0";
    boolean check = false;
    private Button answer1, answer2, answer3;

    public void generateNumber () {

        // 配列の初期化
        /*
        for(int r = 0; r < quiz_number.length; r++){
            quiz_number[r] = -1;
        }
         */
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            // 乱数の生成
            // 101未満の自然数を生成
            quizNumber[i] = random.nextInt(101);
            // 同じ数が出ないようにする再抽選
            for (int j = 0; j < i; j++){
                if(quizNumber[i] == quizNumber[j]){
                    quizNumber[i] = -100; // 一時的なエラーコードとして使用
                    break;
                }
            }

            // 同じ数のエラーになっていないかの確認
            if(quizNumber[i] != -100){
                // 数字→文字列変換
                strNumber[i] = Integer.valueOf(quizNumber[i]).toString();
                // 大小比較
                if (quizNumber[i] > maxNumber) {
                    maxNumber = quizNumber[i];
                    maxNumberStr = strNumber[i];
                }
            }else{
                // エラーになっていたらiをデクリメントしてやり直し
                i--;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        check = intent.getBooleanExtra("CLEAR", true);

        generateNumber();

        TextView option1 = (TextView) findViewById(R.id.option1);
        option1.setText(strNumber[0]);
        option1.setTextSize(60);
        TextView option2 = (TextView) findViewById(R.id.option2);
        option2.setText(strNumber[1]);
        option2.setTextSize(60);
        TextView option3 = (TextView) findViewById(R.id.option3);
        option3.setText(strNumber[2]);
        option3.setTextSize(60);

        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);

        //answer1.setOnClickListener(this);
        //answer2.setOnClickListener(this);
        //answer3.setOnClickListener(this);

        //if(check) quizCount = 0;
        //quizCount++;
    }
    public void onClick(View view){
        Button answer = findViewById(view.getId());
        String btntxt = answer.getText().toString();
        boolean judge = false;
        Intent i;

        if(btntxt.equals(maxNumberStr)){
            judge = true;
        }else{
            judge = false;
        }
        i = new Intent(this, judge.class);
        i.putExtra("RESULT", judge);
        i.putExtra("QUIZ_NUMBER", quizNumber); // 出題用の配列
        //i.putExtra("QUIZ", quizCount);
        if(check == false) i.putExtra("CLEAR", false);
        startActivity(i);

    }
}