package gonnapass.nguyentrung.com.gonnapass.ui.update_password;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gonnapass.nguyentrung.com.gonnapass.R;
import gonnapass.nguyentrung.com.gonnapass.network.ApiClient;
import gonnapass.nguyentrung.com.gonnapass.network.ApiService;
import gonnapass.nguyentrung.com.gonnapass.utils.AlertUtils;
import gonnapass.nguyentrung.com.gonnapass.utils.ResponseUtils;
import gonnapass.nguyentrung.com.gonnapass.utils.ValidationUtils;

public class UpdatePasswordActivity extends AppCompatActivity {

    @BindView(R.id.etCode)
    EditText etToken;
    @BindView(R.id.etNewPassword)
    EditText etNewPassword;
    @BindView(R.id.etReNewPassword)
    EditText etRePassword;
    ApiService apiService;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
        ButterKnife.bind(this);

        intent = getIntent();
        String msg=intent.getStringExtra("msg");
        if(!TextUtils.isEmpty(msg)){
            AlertUtils.showAlertMgs(this,msg);
        }

    }

    @OnClick({R.id.btnUpdatePassword})
    void updatePassword() {
        updatePasswordAction();
    }

    @OnClick({R.id.iBntBackForgot})
    void backForgot() {
        finish();
    }

    private void updatePasswordAction() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("data");
        String new_password = etNewPassword.getText().toString().trim();
        String token = etToken.getText().toString().trim();
        String re_Password = etRePassword.getText().toString().trim();

        checkValidate(email, token, new_password, re_Password);

    }

    private void checkValidate(String email, String token, String newPassword, String rePassword) {
        boolean cancel = false;
        View focusView = null;
        // Check for a valid
        if (TextUtils.isEmpty(token)) {
            etToken.setError(getString(R.string.error_isEmpty_username));
            focusView = etToken;
            cancel = true;
        } else if (TextUtils.isEmpty(newPassword)) {
            etNewPassword.setError(getString(R.string.error_isEmpty_password));
            focusView = etNewPassword;
            cancel = true;
        } else if (!ValidationUtils.isValidPasswordNormal(newPassword)) {
            etNewPassword.setError(getString(R.string.error_invalid_password));
            focusView = etNewPassword;
            cancel = true;
        } else if (TextUtils.isEmpty(rePassword)) {
            etRePassword.setError(getString(R.string.error_isEmpty_password));
            focusView = etRePassword;
            cancel = true;
        } else if (!rePassword.equals(newPassword)) {
            etRePassword.setError(getString(R.string.error_re_password));
            focusView = etRePassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            ResponseUtils.updatePasswordResponse(email, newPassword, token, apiService, this);
        }
    }
}
