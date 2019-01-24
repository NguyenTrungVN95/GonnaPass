package gonnapass.nguyentrung.com.gonnapass.ui.active;

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
import io.reactivex.disposables.CompositeDisposable;

public class ActiveActivity extends AppCompatActivity {
    private static final String TAG = ActiveActivity.class.getSimpleName();
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    @BindView(R.id.etVerifyCode)
    EditText etVerify;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        intent = getIntent();
        String msg=intent.getStringExtra("msg");
        if(!TextUtils.isEmpty(msg)){
            AlertUtils.showAlertMgs(this,msg);
        }

        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

    }

    @OnClick({R.id.btnVerify})
    void verify(){
        String email=intent.getStringExtra("data");
        String token=etVerify.getText().toString().trim();
        validation(email,token);
    }
    @OnClick(R.id.iBntBackActive)
    void onBack(){
        finish();
    }

    private void validation(String email,String token){

        boolean cancel = false;
        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(token)) {
            etVerify.setError(getString(R.string.error_isEmpty_email));
            focusView = etVerify;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            ResponseUtils.activeResponse(email,token,apiService,ActiveActivity.this);
        }

    }
}
