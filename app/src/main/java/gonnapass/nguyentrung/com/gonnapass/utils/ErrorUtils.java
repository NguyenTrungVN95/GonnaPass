package gonnapass.nguyentrung.com.gonnapass.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ErrorUtils {
    public static void showError(Throwable e,View v) {
        String message = "";
        try {
            if (e instanceof IOException) {
                message = "No internet connection!";
            } else if (e instanceof HttpException) {
                HttpException error = (HttpException) e;
                String errorBody = error.response().errorBody().string();
                JSONObject jObj = new JSONObject(errorBody);

                message = jObj.getString("error");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (TextUtils.isEmpty(message)) {
            message = "Unknown error occurred! Check LogCat.";
        }

        Snackbar snackbar = Snackbar
                .make(v, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
