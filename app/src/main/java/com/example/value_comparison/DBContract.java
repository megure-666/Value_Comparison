package com.example.value_comparison;

import android.provider.BaseColumns;

// データベースのテーブル名・項目名を定義
public final class DBContract {

    // コンストラクタのプライベート宣言
    private DBContract(){}

    // テーブルの内容
    public static class DBEntry implements BaseColumns{
        // BassColumns インターフェースを実装して、内部クラスが_IDを継承可能に
        public static final String TABLE_NAME = "vc_tbl";
        public static final String COLUMN_NAME_DETAILS = "details";
        public static final String COLUMN_NAME_PERCENTAGE = "percentage";
        public static final String COLUMN_NAME_UPDATE = "up_date";
    }
}
