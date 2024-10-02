package com.example.value_comparison;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class CSVExportDialog extends DialogFragment {

    public interface DialogListener {
        public boolean onDialogPositiveClickExport(DialogFragment dialog);
    }

    DialogListener listener;

    // フラグメントができたときにlistenerをインスタンス化
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (DialogListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        // ダイアログを作るためのビルダーを作成
        // ビルダーでタイトルやメッセージを設定
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ダイアログのタイトル
        builder.setTitle(R.string.export_title);

        // ダイアログのメッセージ文
        builder.setMessage(R.string.export_message);

        DialogClickListener listener = new DialogClickListener();

        // Positiveボタンの内容表示と押したときの処理
        builder.setPositiveButton(R.string.dialog_positive, listener);

        // Nagativeボタンの内容表示と押したときの処理(無いのでnull)
        builder.setNegativeButton(R.string.dialog_negative, null);

        // 設定したダイアログを作成
        AlertDialog dialog = builder.create();

        // ダイアログをリターン
        return dialog;
    }

    private class DialogClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int buttonId){

            // switchでタップされたボタンでの条件分岐を行う
            switch (buttonId){
                // positiveボタン
                case DialogInterface.BUTTON_POSITIVE:
                    // positiveボタンが押されたときのメソッドを呼び出し
                    // 処理は継承先のShowDataBaseで実行
                    listener.onDialogPositiveClickExport(CSVExportDialog.this);
                    break;
            }
        }
    }
}
