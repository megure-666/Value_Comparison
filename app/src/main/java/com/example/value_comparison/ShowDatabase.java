package com.example.value_comparison;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowDatabase extends AppCompatActivity
        implements AllDeleteDialog.NoticeDialogListener, CSVExportDialog.DialogListener{

    private VCDatabaseHelper helper = null;
    DBListAdapter sc_adapter;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_showdb);

        onShow();
    }

    // データベースの情報を表示するメソッド
    protected void onShow(){

        // データベースヘルパーを準備
        helper = new VCDatabaseHelper(this);

        // データベースから取得する項目を設定
        String[] cols = {DBContract.DBEntry._ID, DBContract.DBEntry.COLUMN_NAME_DETAILS,
                DBContract.DBEntry.COLUMN_NAME_PERCENTAGE};

        // 読み込みモードでデータベースをオープン
        try (SQLiteDatabase db = helper.getReadableDatabase()){

            // データを取得するSQLを実行
            // 取得したデータがCursorオブジェクトに格納される
            Cursor cursor = db.query(DBContract.DBEntry.TABLE_NAME, cols, null,
                    null, null, null, null, null);

            // 検索結果から取得する項目を定義
            String[] from = {DBContract.DBEntry.COLUMN_NAME_DETAILS, DBContract.DBEntry.COLUMN_NAME_PERCENTAGE};

            // データを設定するレイアウトのフィールドを定義
            int[] to = {R.id.text_details, R.id.text_percentage};

            // ListViewの1行分のレイアウト(row_main.xml)と検索結果を関連付け
            sc_adapter = new DBListAdapter(this, R.layout.row_main, cursor, from, to, 0);

            // activity_showdbに定義したListViewオブジェクトの取得
            ListView list = findViewById(R.id.show_db);

            // ListViewにアダプターを設定
            list.setAdapter(sc_adapter);

        }
    }

    // 削除ボタン タップ時に呼び出されるメソッド
    public void onButtonDel(View view){

        // DBListAdapterで設定されたリスト内の位置を取得
        int pos = (Integer)view.getTag();

        // アダプターから、_idの値を取得
        int id = ((Cursor) sc_adapter.getItem(pos)).getInt(0);

        // データを削除
        try (SQLiteDatabase db = helper.getWritableDatabase()){
            db.delete(DBContract.DBEntry.TABLE_NAME,
                    DBContract.DBEntry._ID+" = ?", new String[] {String.valueOf(id)});
        }

        // データ一覧を表示
        onShow();
    }

    // 全削除ボタン
    public void onButtonAllDel(View view){

        AllDeleteDialog dialog = new AllDeleteDialog();
        dialog.show(getSupportFragmentManager(), "all_del");

    }

    // ダイアログでボタンが押されたときに動作するコールバック関数
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        try(SQLiteDatabase db = helper.getWritableDatabase()){
            db.delete(DBContract.DBEntry.TABLE_NAME, null, null);
        }

        // データ一覧を表示
        onShow();
    }

    // CSV出力ボタン
    public void onButtonExport(View view){

        CSVExportDialog dialog = new CSVExportDialog();
        dialog.show(getSupportFragmentManager(), "CSV_export");

    }

    // ダイアログでCSVファイル出力を行う際のコールバック関数
    @Override
    public boolean onDialogPositiveClickExport(DialogFragment dialog) {
        // 外部ストレージがマウントされているかチェック
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return false;
        }else{
            File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if(!exportDir.exists()){
                exportDir.mkdirs();
            }

            File file;
            PrintWriter printWriter = null;

            try {
                // 現在日時を取得
                LocalDateTime nowDate = LocalDateTime.now();

                // フォーマット形式を設定
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                String formatNowDate = dtf.format(nowDate);

                // ファイル作成
                file = new File(exportDir, "result_" + formatNowDate + ".csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));

                helper = new VCDatabaseHelper(this);
                SQLiteDatabase db = helper.getReadableDatabase();

                // データベースから取得する項目を設定
                String[] cols = {DBContract.DBEntry._ID, DBContract.DBEntry.COLUMN_NAME_DETAILS,
                        DBContract.DBEntry.COLUMN_NAME_PERCENTAGE};

                Cursor cursor = db.query(DBContract.DBEntry.TABLE_NAME, cols, null,
                        null, null, null, null, null);
                //String[] colsName = {"details", "percentage"};

                // CSVファイルのヘッダーを書き出し
                printWriter.println("DETAILS,PERCENTAGE");

                // データの行数分CSV形式でデータ書き出し
                if (cursor.moveToFirst()) {
                    do {

                        int[] getCursorColumn = new int[2];

                        try{
                            getCursorColumn[0] = cursor.getColumnIndex("details");
                            getCursorColumn[1] = cursor.getColumnIndex("percentage");

                        } catch (IndexOutOfBoundsException e){
                            Toast ts = Toast.makeText(this, "不正な値が配列に入力されました", Toast.LENGTH_SHORT);
                            ts.show();
                            return false;

                        }

                        String details = cursor.getString(getCursorColumn[0]);
                        details = " " + details; // CSVで勝手に日付に変換されないための処理
                        String percentage = cursor.getString(getCursorColumn[1]);

                        String record = details + "," + percentage;
                        printWriter.println(record);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();

            } catch (FileNotFoundException e) {
                // フォルダへの権限がない場合の表示
                Toast ts = Toast.makeText(this, "アクセス権限がありません", Toast.LENGTH_SHORT);
                ts.show();
                return false;

            } catch (Exception e) {
                Toast ts = Toast.makeText(this, "CSV出力が失敗しました", Toast.LENGTH_SHORT);
                ts.show();
                return false;

            } finally {
                if(printWriter != null) printWriter.close();

            }

            Toast ts = Toast.makeText(this, "CSVに出力しました", Toast.LENGTH_SHORT);
            ts.show();
            return true;
        }

    }

    // 戻るボタンの処理
    public void onButtonBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }
}
