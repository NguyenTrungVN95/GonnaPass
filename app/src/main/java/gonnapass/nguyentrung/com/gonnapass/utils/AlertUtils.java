package gonnapass.nguyentrung.com.gonnapass.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import gonnapass.nguyentrung.com.gonnapass.R;
import gonnapass.nguyentrung.com.gonnapass.ui.login.LoginActivity;


public class AlertUtils {

    public static void showAlertMgs(final Context context, CharSequence message) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_msg_response, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final android.app.AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        TextView bnt_mgs = (TextView) view.findViewById(R.id.bntMgs);
        TextView mgs = (TextView) view.findViewById(R.id.msg);

        mgs.setText(message);

        bnt_mgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    //Register Done
    public static void showAlertError(final Context context) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_error, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final android.app.AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        TextView bnt_err = (TextView) view.findViewById(R.id.bntError);

        bnt_err.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //Register fail
    public static void showAlertRegisterFail(final Context context, CharSequence message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.fail_mgs),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
        builder.show();
    }

    public static void showAlertUpdatePassDone(final Context context, CharSequence message) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_msg_response, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final android.app.AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        TextView bnt_mgs = (TextView) view.findViewById(R.id.bntMgs);
        TextView mgs = (TextView) view.findViewById(R.id.msg);

        mgs.setText(message);

        bnt_mgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,LoginActivity.class));
                ;
            }
        });


    }

    public static void showAlertActiveDone(final Context context, CharSequence message) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_msg_response, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final android.app.AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        TextView bnt_mgs = (TextView) view.findViewById(R.id.bntMgs);
        TextView mgs = (TextView) view.findViewById(R.id.msg);

        mgs.setText(message);

        bnt_mgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.gotoLogin(context);
                context.startActivity(new Intent(context,LoginActivity.class));
                ;
            }
        });


    }
}
