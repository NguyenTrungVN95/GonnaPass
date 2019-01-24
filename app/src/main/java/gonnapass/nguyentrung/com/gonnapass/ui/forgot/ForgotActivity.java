package gonnapass.nguyentrung.com.gonnapass.ui.forgot;

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
import gonnapass.nguyentrung.com.gonnapass.utils.ResponseUtils;
import gonnapass.nguyentrung.com.gonnapass.utils.ValidationUtils;

public class ForgotActivity extends AppCompatActivity {

    @BindView(R.id.etSentToEmail)
    EditText etSentToEmail;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        apiService=ApiClient.getClient(getApplicationContext()).create(ApiService.class);
    }
    @OnClick({R.id.btnVerify})
    void verify(){
        String email=etSentToEmail.getText().toString().trim();
        validation(email);
    }

    @OnClick(R.id.iBntBackForgot)
    void onBack(){
        finish();
    }

    private void validation(String email){
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            etSentToEmail.setError(getString(R.string.error_isEmpty_email));
            focusView = etSentToEmail;
            cancel = true;
        } else if (!ValidationUtils.emailValidator(email)) {
            etSentToEmail.setError(getString(R.string.error_invalid_email));
            focusView = etSentToEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //DialogUtils.showLoadingDialog(this);
            ResponseUtils.forgotResponse(email,apiService,this);
        }
    }
}
