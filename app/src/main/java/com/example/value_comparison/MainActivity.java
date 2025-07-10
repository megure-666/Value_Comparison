package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // スタートボタン
        Button startButton = (Button) findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                // 設定画面の値の読み込み(選択肢の数)
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String checkChoices = sharedPreferences.getString("choices", "0");
                int checkVal = Integer.parseInt(checkChoices);

                switch (checkVal) {
                    case 3: // 3個
                        intent = new Intent(getApplication(), Quiz3.class);
                        intent.putExtra("CLEAR", true);
                        startActivity(intent);
                        break;

                    case 4: // 4個
                        intent = new Intent(getApplication(), Quiz4.class);
                        intent.putExtra("CLEAR", true);
                        startActivity(intent);
                        break;

                    case 5: // 5個
                        intent = new Intent(getApplication(), Quiz5.class);
                        intent.putExtra("CLEAR", true);
                        startActivity(intent);
                        break;

                    default: // 読み込みエラー時
                        Toast toastError = Toast.makeText(getApplicationContext(), "値が読み込まれませんでした", Toast.LENGTH_SHORT);
                        toastError.show();
                        break;
                }
            }
        });

        // 設定ボタン
        Button settingsButton = (Button) findViewById(R.id.button_setting);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Settings.class);
                startActivity(intent);
            }
        });

        // 履歴ボタン
        // データベースのデータを表示するようになってる
        Button resultButton = (Button) findViewById(R.id.button_result);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ShowDatabase.class);
                startActivity(intent);
            }
        });
    }
}