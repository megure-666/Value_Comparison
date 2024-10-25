package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Result extends AppCompatActivity {

    private VCDatabaseHelper helper = null;
    private int id = 0;
    private String details_str;
    private String val_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int quizCount = intent.getIntExtra("QUIZ", 0);
        int correctCount = intent.getIntExtra("CORRECT", 0);
        //int incorrectCount = intent.getIntExtra("INCORRECT", 0);

        // ヘルパーを準備
        helper = new VCDatabaseHelper(this);

        // 正解数の表示
        TextView details = findViewById(R.id.details);
        details.setTextSize(80);
        details_str = correctCount + " / " + quizCount;
        details.setText(details_str);

        // 正答率の表示
        TextView percentage = findViewById(R.id.percentage);
        percentage.setTextSize(80);
        double val = ((double)correctCount / quizCount) * 100;
        val_str = String.format("%.2f",val);
        percentage.setText(val_str + " %");

    }

    // DB保存処理
    public void onSave(String details, String val){

        int id = 0;

        try(SQLiteDatabase db = helper.getWritableDatabase()){

            // 引数をContentValuesに設定
            // ContentValuesは、項目名と値をセットで保存できるオブジェクト
            ContentValues cv = new ContentValues();

            // 現在日時を取得
            LocalDateTime nowDate = LocalDateTime.now();
            // フォーマット形式を設定
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            String formatNowDate = dtf.format(nowDate);
            cv.put(DBContract.DBEntry.COLUMN_NAME_DATE, formatNowDate);
            // 設定画面の値の読み込み(選択肢の数)
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String checkChoices = sharedPreferences.getString("choices", "0");
            int checkVal = Integer.parseInt(checkChoices);
            cv.put(DBContract.DBEntry.COLUMN_NAME_QUANTITY, checkVal);
            cv.put(DBContract.DBEntry.COLUMN_NAME_DETAILS, details);
            cv.put(DBContract.DBEntry.COLUMN_NAME_PERCENTAGE, val);

            if(id == 0){
                // データ新規登録
                db.insert(DBContract.DBEntry.TABLE_NAME, null, cv);
            } else {
                // データ更新
                db.update(DBContract.DBEntry.TABLE_NAME, cv,
                        DBContract.DBEntry._ID + " = ?", new String[] {String.valueOf(id)});
            }
        }
    }

    public void onButtonMain(View view) {

        // データを保存
        onSave(details_str, val_str);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onButtonContinue(View view) {
        // 続ける場合はデータをDBに保存しない
        Intent intent;

        // 設定画面の値の読み込み(選択肢の数)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkChoices = sharedPreferences.getString("choices", "0");
        int checkVal = Integer.parseInt(checkChoices);

        switch (checkVal){
            case 3: // 3個
                intent = new Intent(this, Quiz3.class);
                intent.putExtra("CLEAR", false);
                startActivity(intent);
                break;

            case 4: // 4個
                intent = new Intent(this, Quiz4.class);
                intent.putExtra("CLEAR", false);
                startActivity(intent);
                break;

            case 5: // 5個
                intent = new Intent(this, Quiz5.class);
                intent.putExtra("CLEAR", false);
                startActivity(intent);
                break;

            default:
                Toast toastError = Toast.makeText(getApplicationContext(),"値が読み込まれませんでした", Toast.LENGTH_SHORT);
                toastError.show();
                break;
        }
    }
}