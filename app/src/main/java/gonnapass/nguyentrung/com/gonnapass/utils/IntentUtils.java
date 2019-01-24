package gonnapass.nguyentrung.com.gonnapass.utils;

import android.content.Context;
import android.content.Intent;

import gonnapass.nguyentrung.com.gonnapass.ui.active.ActiveActivity;
import gonnapass.nguyentrung.com.gonnapass.ui.home.HomeActivity;
import gonnapass.nguyentrung.com.gonnapass.ui.login.LoginActivity;
import gonnapass.nguyentrung.com.gonnapass.ui.update_password.UpdatePasswordActivity;

public class IntentUtils {
    public static void gotoActive(final String data,final String msg,final Context context) {
        Intent intent=new Intent(context,ActiveActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("msg", msg);

        context.startActivity(intent);
    }

    public static void gotoUpdatePassword(final String data,final String msg,final Context context){
        Intent intent=new Intent(context,UpdatePasswordActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("msg", msg);

        context.startActivity(intent);
    }

    public static void gotoHome(final Context context){
        Intent intent=new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    public static void gotoLogin(final Context context){
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

}
