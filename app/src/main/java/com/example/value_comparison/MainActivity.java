package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        check = intent.getBooleanExtra("CLEAR", false);
    }

    // スタートボタン
    public void onButton_start(View view) {
        Intent intent = new Intent(this, quiz.class);
        if(check == true) intent.putExtra("CLEAR", true);
        startActivity(intent);
    }

    // 設定ボタン
    // 一時的にデータベースのデータを表示するようになってる
    public void onButton_setting(View view) {
        Intent intent = new Intent(this, ShowDatabase.class);
        //if(check == true) intent.putExtra("CLEAR", true);
        startActivity(intent);
    }
}