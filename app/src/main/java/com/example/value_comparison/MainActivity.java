package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

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

        // 設定画面の値の読み込み(選択肢の数)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkChoices = sharedPreferences.getString("choices", "0");
        int checkVal = Integer.parseInt(checkChoices);

        switch (checkVal){
            case 3: // 3個
                Intent intent = new Intent(this, quiz.class);
                if(check) intent.putExtra("CLEAR", true);
                startActivity(intent);
                break;

            case 0:
                Toast toastError = Toast.makeText(getApplicationContext(),"値が読み込まれませんでした", Toast.LENGTH_SHORT);
                toastError.show();
                break;

            default:
                Toast toastNotAvailable = Toast.makeText(getApplicationContext(),"現在は利用できません", Toast.LENGTH_SHORT);
                toastNotAvailable.show();
                break;
        }
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