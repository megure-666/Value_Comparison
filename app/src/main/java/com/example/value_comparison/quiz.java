package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class quiz extends AppCompatActivity {

    int quizNumber[] = new int[3];      // 乱数生成用の配列
    String strNumber[] = new String[3]; // 文字列に変換した数字用の配列
    int maxNumber = 0;                  // 最大値の配列の番号を保持する変数
    String maxNumberStr = "0";
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
            // 101未満の数を生成
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

        generateNumber();

        TextView op1 = (TextView) findViewById(R.id.option1);
        op1.setText(strNumber[0]);
        op1.setTextSize(60);
        TextView op2 = (TextView) findViewById(R.id.option2);
        op2.setText(strNumber[1]);
        op2.setTextSize(60);
        TextView op3 = (TextView) findViewById(R.id.option3);
        op3.setText(strNumber[2]);
        op3.setTextSize(60);

        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);

        //answer1.setOnClickListener(this);
        //answer2.setOnClickListener(this);
        //answer3.setOnClickListener(this);
    }
    public void onClick(View view){
        Button answer = findViewById(view.getId());
        String btntxt = answer.getText().toString();

        String result;
        if (btntxt.equals(maxNumberStr)){
            Intent intent = new Intent(this, correct.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, incorrect.class);
            startActivity(intent);
        }

    }
    /*ボタン押して画面遷移できた*/
    /*
    public void onButton_op1(View view) {
        Intent intent = new Intent(this, correct.class);
        startActivity(intent);
    }

    public void onButton_op2(View view) {
        Intent intent = new Intent(this, incorrect.class);
        startActivity(intent);
    }

    public void onButton_op3(View view) {
        Intent intent = new Intent(this, incorrect.class);
        startActivity(intent);
    }
    */
}