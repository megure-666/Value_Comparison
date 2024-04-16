package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // スタートボタン
    public void onButton_start(View view) {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);
    }

    // 設定ボタン
    public void onButton_setting(View view) {
    }
}