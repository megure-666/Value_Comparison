package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

       // findViewById(R.id.button_setting).setOnClickListener(v -> {
        // onButtonSettings();
        //});
    }

    // スタートボタン
    public void onButtonStart(View view) {
        Intent intent = new Intent(this, quiz.class);
        if(check) intent.putExtra("CLEAR", true);
        startActivity(intent);
    }

    // 設定ボタン
    public void onButtonSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }


    // 履歴ボタン
    // データベースのデータを表示するようになってる
    public void onButtonResult(View view) {
        Intent intent = new Intent(this, ShowDatabase.class);
        startActivity(intent);
    }
}