package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Quiz4 extends AppCompatActivity {

    int quizNumber[] = new int[4];      // 乱数生成用の配列
    String strNumber[] = new String[4]; // 文字列に変換した数字用の配列
    int maxNumber = 0;                  // 最大値の配列の番号を保持する変数
    String maxNumberStr = "0";
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);

        Intent intent = getIntent();
        check = intent.getBooleanExtra("CLEAR", true);

        generateNumber();

        TextView option1 = (TextView) findViewById(R.id.option41);
        option1.setText(strNumber[0]);
        option1.setTextSize(60);
        TextView option2 = (TextView) findViewById(R.id.option42);
        option2.setText(strNumber[1]);
        option2.setTextSize(60);
        TextView option3 = (TextView) findViewById(R.id.option43);
        option3.setText(strNumber[2]);
        option3.setTextSize(60);
        TextView option4 = (TextView) findViewById(R.id.option44);
        option4.setText(strNumber[3]);
        option4.setTextSize(60);
    }

    private void generateNumber () {

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
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

    public void onClick(View view){
        Button answer = findViewById(view.getId());
        String btnTxt = answer.getText().toString();
        boolean judge = false;
        Intent i;

        if(btnTxt.equals(maxNumberStr)) judge = true;

        i = new Intent(this, Judge.class);
        i.putExtra("RESULT", judge);
        i.putExtra("QUIZ_NUMBER", quizNumber); // 出題用の配列
        if(!check) i.putExtra("CLEAR", false);
        startActivity(i);

    }
}
