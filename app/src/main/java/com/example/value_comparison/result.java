package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    private VCDatabaseHelper helper = null;
    private int id = 0;

    boolean clear;

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
        String details_str = String.valueOf(correctCount) + " / " + String.valueOf(quizCount);
        details.setText(details_str);

        // 正答率の表示
        TextView percentage = findViewById(R.id.percentage);
        percentage.setTextSize(80);
        double val = ((double)correctCount / quizCount) * 100;
        String val_str = String.format("%.2f",val);
        percentage.setText(val_str + " %");

        // データを保存
        onSave(details_str, val_str);

    }

    // DB保存処理
    public void onSave(String details, String val){

        try(SQLiteDatabase db = helper.getWritableDatabase()){

            // 引数をContentValuesに設定
            // ContentValuesは、項目名と値をセットで保存できるオブジェクト
            ContentValues cv = new ContentValues();
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

    public void onButton_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        clear = true;
        intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }

    public void onButton_continue(View view) {
        Intent intent = new Intent(this, quiz.class);
        clear = false;
        intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }
}