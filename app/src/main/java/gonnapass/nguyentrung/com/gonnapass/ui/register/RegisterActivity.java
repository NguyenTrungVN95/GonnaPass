package gonnapass.nguyentrung.com.gonnapass.ui.register;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gonnapass.nguyentrung.com.gonnapass.R;
import gonnapass.nguyentrung.com.gonnapass.network.ApiClient;
import gonnapass.nguyentrung.com.gonnapass.network.ApiService;
import gonnapass.nguyentrung.com.gonnapass.utils.ProgressDialog;
import gonnapass.nguyentrung.com.gonnapass.utils.ResponseUtils;
import gonnapass.nguyentrung.com.gonnapass.utils.ValidationUtils;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.coordinator_layout)
    ConstraintLayout coordinatorLayout;
    @BindView(R.id.etUserName)
    AutoCompleteTextView etUserName;
    @BindView(R.id.etEmail)
    AutoCompleteTextView etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView((R.id.etRePassword))
    EditText etRePassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    protected ProgressDialog mNetworkProgressDialog;

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

    }

    @OnClick(R.id.btnRegister)
    void onRegisterClick() {
        registerUserAction();
    }

    @OnClick(R.id.iBntBackRegister)
    void onBack() {
        finish();
    }

    /**
     * Registering new user
     * sending unique id as device identification
     */
    private void registerUserAction() {
        String user_name = etUserName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String re_password = etRePassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        checkValidate(user_name, email, phone, password, re_password, "");


    }

    private void checkValidate(String user_name, String email, String phone, String password, String re_password, String address) {
        boolean cancel = false;
        View focusView = null;
        // Check for a valid
        if (TextUtils.isEmpty(user_name)) {
            etUserName.setError(getString(R.string.error_isEmpty_username));
            focusView = etUserName;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_isEmpty_email));
            focusView = etEmail;
            cancel = true;
        } else if (!ValidationUtils.emailValidator(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
            cancel = true;
        } else if (TextUtils.isEmpty(phone)) {
            etPhone.setError(getString(R.string.error_isEmpty_phone));
            focusView = etPhone;
            cancel = true;
        } else if (!ValidationUtils.isValidMobile(phone)) {
            etPhone.setError(getString(R.string.error_invalid_phone));
            focusView = etPhone;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.error_isEmpty_password));
            focusView = etPassword;
            cancel = true;
        } else if (!ValidationUtils.isValidPasswordNormal(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        } else if (!password.equals(re_password)) {
            etRePassword.setError(getString(R.string.error_re_password));
            focusView = etRePassword;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            ResponseUtils.registerResponse(user_name, email, phone, password, address, apiService, this);

        }
    }


}
