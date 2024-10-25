package com.example.value_comparison;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VCDatabaseHelper extends SQLiteOpenHelper {

    // データベースのバージョン
    // テーブルの内容などを変更したら、この数字を変更する
    static final private int VERSION = 3;

    // データベース名
    static final private String DBNAME = "vc.db";

    // コンストラクタは必ず必要
    public VCDatabaseHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    // データベース作成時にテーブル作成
    public void onCreate(SQLiteDatabase db){

        // テーブル作成
        db.execSQL(
                "CREATE TABLE "+ DBContract.DBEntry.TABLE_NAME + " (" +
                        DBContract.DBEntry._ID + " INTEGER PRIMARY KEY, " +
                        DBContract.DBEntry.COLUMN_NAME_DATE + " TEXT default 'NO DATA'," +
                        DBContract.DBEntry.COLUMN_NAME_QUANTITY + " TEXT default 'NO DATA'," +
                        DBContract.DBEntry.COLUMN_NAME_DETAILS + " TEXT default 'NO DATA'," +
                        DBContract.DBEntry.COLUMN_NAME_PERCENTAGE + " TEXT default 'NO DATA'," +
                        DBContract.DBEntry.COLUMN_NAME_UPDATE + " INTEGER DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')))");

        // トリガーを作成
        db.execSQL(
                "CREATE TRIGGER trigger_vc_tbl_update AFTER UPDATE ON " + DBContract.DBEntry.TABLE_NAME +
                        " BEGIN "+
                        " UPDATE " + DBContract.DBEntry.TABLE_NAME + " SET up_date = DATETIME('now', 'localtime') WHERE rowid == NEW.rowid; " +
                        " END;");

    }

    // データベースをバージョンアップした時、テーブルを削除してから再作成
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME);
        onCreate(db);
    }
}
