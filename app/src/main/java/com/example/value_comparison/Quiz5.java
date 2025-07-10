package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Quiz5 extends AppCompatActivity {

    int quizNumber[] = new int[5];      // 乱数生成用の配列
    String strNumber[] = new String[5]; // 文字列に変換した数字用の配列
    int maxNumber = 0;                  // 最大値の配列の番号を保持する変数
    String maxNumberStr = "0";
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        Intent intent = getIntent();
        check = intent.getBooleanExtra("CLEAR", true);

        generateNumber();

        // before txt size = 60
        TextView option1 = (TextView) findViewById(R.id.option51);
        option1.setText(strNumber[0]);
        option1.setTextSize(120);
        TextView option2 = (TextView) findViewById(R.id.option52);
        option2.setText(strNumber[1]);
        option2.setTextSize(120);
        TextView option3 = (TextView) findViewById(R.id.option53);
        option3.setText(strNumber[2]);
        option3.setTextSize(120);
        TextView option4 = (TextView) findViewById(R.id.option54);
        option4.setText(strNumber[3]);
        option4.setTextSize(120);
        TextView option5 = (TextView) findViewById(R.id.option55);
        option5.setText(strNumber[4]);
        option5.setTextSize(120);

        // 選択されたボタンを感知する
        View.OnClickListener next = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button answer = findViewById(view.getId());
                String btnTxt = answer.getText().toString();
                boolean judge = false;
                Intent i;

                if(btnTxt.equals(maxNumberStr)) judge = true;

                i = new Intent(getApplication(), Judge.class);
                i.putExtra("RESULT", judge);
                i.putExtra("QUIZ_NUMBER", quizNumber); // 出題用の配列
                if(!check) i.putExtra("CLEAR", false);
                startActivity(i);
            }
        };

        findViewById(R.id.option51).setOnClickListener(next);
        findViewById(R.id.option52).setOnClickListener(next);
        findViewById(R.id.option53).setOnClickListener(next);
        findViewById(R.id.option54).setOnClickListener(next);
        findViewById(R.id.option55).setOnClickListener(next);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // 保存領域にセットした値を保存
        outState.putStringArray("STRNUM", strNumber);
        outState.putString("MAXNUMSTR", maxNumberStr);
        outState.putIntArray("NUMBER", quizNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstance){
        super.onRestoreInstanceState(savedInstance);

        strNumber = savedInstance.getStringArray("STRNUM");
        maxNumberStr = savedInstance.getString("MAXNUMSTR");
        quizNumber = savedInstance.getIntArray("NUMBER");

        TextView option1 = (TextView) findViewById(R.id.option51);
        option1.setText(strNumber[0]);
        TextView option2 = (TextView) findViewById(R.id.option52);
        option2.setText(strNumber[1]);
        TextView option3 = (TextView) findViewById(R.id.option53);
        option3.setText(strNumber[2]);
        TextView option4 = (TextView) findViewById(R.id.option54);
        option4.setText(strNumber[3]);
        TextView option5 = (TextView) findViewById(R.id.option55);
        option5.setText(strNumber[4]);
    }

    private void generateNumber () {

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
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
}