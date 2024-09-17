package com.example.value_comparison;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class AllDeleteDialog extends DialogFragment {

    private VCDatabaseHelper helper = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        // ダイアログを作るためのビルダーを作成
        // ビルダーでタイトルやメッセージを設定
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ダイアログのタイトル
        builder.setTitle(R.string.dialog_title);

        // ダイアログのメッセージ文
        builder.setTitle(R.string.dialog_message);

        // Positiveボタンの内容表示と押したときの処理
        builder.setPositiveButton(R.string.daialog_positive, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ボタンを押した時の処理

            }
        });

        // Nagativeボタンの内容表示と押したときの処理(無いのでnull)
        builder.setNegativeButton(R.string.dialog_negative, null);

        // Neutralボタンの内容表示と押したときの処理(無いのでnull)
        builder.setNeutralButton(R.string.dialog_neutral, null);

        // 設定したダイアログを作成
        AlertDialog dialog = builder.create();

        // ダイアログをリターン
        return dialog;
    }
}
