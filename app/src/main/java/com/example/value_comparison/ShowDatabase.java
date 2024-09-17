package com.example.value_comparison;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDatabase extends AppCompatActivity {

    private VCDatabaseHelper helper = null;
    DBListAdapter sc_adapter;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_showdb);

        onShow();
    }

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
    public void onButton_del(View view){

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
    public void onButton_alldel(View view){

        AllDeleteDialog dialog = new AllDeleteDialog();
        dialog.show(getSupportFragmentManager(), "all_del");

        // データ一覧を表示
        onShow();
    }

    // 戻るボタンの処理
    public void onButton_back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("CLEAR", clear);
        startActivity(intent);
    }
}
