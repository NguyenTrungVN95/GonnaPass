package gonnapass.nguyentrung.com.gonnapass.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gonnapass.nguyentrung.com.gonnapass.R;
import gonnapass.nguyentrung.com.gonnapass.network.ApiClient;
import gonnapass.nguyentrung.com.gonnapass.network.ApiService;
import gonnapass.nguyentrung.com.gonnapass.ui.forgot.ForgotActivity;
import gonnapass.nguyentrung.com.gonnapass.ui.register.RegisterActivity;
import gonnapass.nguyentrung.com.gonnapass.utils.ResponseUtils;
import gonnapass.nguyentrung.com.gonnapass.utils.ValidationUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ApiService apiService;
    @BindView(R.id.email_sign_in_button)
    TextView mEmailSignInButton;
    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.linearLayout)
    View mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

    }
    @OnClick({R.id.email_sign_in_button})
    void onLogin(){
        attemptLogin();
    }

    @OnClick({R.id.btnRegister})
    void onRegister(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    @OnClick({R.id.forgot})
    void onForgot(){
        startActivity(new Intent(LoginActivity.this,ForgotActivity.class));

    }
    @OnClick(R.id.iBntBackLogin)
    void onBack(){
        //finish();
    }
    private void attemptLogin() {

        String email = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_isEmpty_email));
            focusView = mEmailView;
            cancel = true;
        }else if (!ValidationUtils.emailValidator(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_isEmpty_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (!ValidationUtils.isValidPasswordNormal(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            ResponseUtils.loginResponse(mLinearLayout,email,password,apiService,this);
        }
    }


}

