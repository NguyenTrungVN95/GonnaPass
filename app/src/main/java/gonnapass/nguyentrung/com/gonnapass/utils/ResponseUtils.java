package gonnapass.nguyentrung.com.gonnapass.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import gonnapass.nguyentrung.com.gonnapass.network.ApiService;
import gonnapass.nguyentrung.com.gonnapass.network.model.ActiveRequest;
import gonnapass.nguyentrung.com.gonnapass.network.model.ActiveResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.ChangePasswordRequest;
import gonnapass.nguyentrung.com.gonnapass.network.model.ChangePasswordResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.ForgotRequest;
import gonnapass.nguyentrung.com.gonnapass.network.model.ForgotResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.LoginRequest;
import gonnapass.nguyentrung.com.gonnapass.network.model.LoginResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.RegisterRequest;
import gonnapass.nguyentrung.com.gonnapass.network.model.RegisterResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.base.BaseResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ResponseUtils {
    private static CompositeDisposable disposable = new CompositeDisposable();
    static ProgressDialog  dialog;
    //Login Response
    public static void loginResponse(final View v,final String email, final String password, final ApiService apiService, final Context context) {
        disposable.clear();
        final String[] msg = {""};
        dialog =DialogUtils.showLoadingDialog(context);
        LoginRequest userLoginRequest=new LoginRequest(email,password);
        disposable.add(
                apiService
                        .login(ConvertUtils.createPostRequest(userLoginRequest))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BaseResponse<LoginResponse>>() {
                            @Override
                            public void onSuccess(BaseResponse<LoginResponse> response) {
                                int response_status = response.getResponse_status();
                                if (response_status == 0) {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_content().getFull_name();
                                    IntentUtils.gotoHome(context);

                                } else {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_msg();
                                    AlertUtils.showAlertRegisterFail(context, msg[0]);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Error", e.getMessage());
                                ErrorUtils.showError(e,v);

                            }
                        }));
    }

    //Register Response
    public static void registerResponse(final String user_name, final String email,final String phone,final String password,final String address, ApiService apiService, final Context context) {
        disposable.clear();
        final String[] msg = {""};
        dialog =DialogUtils.showLoadingDialog(context);
        RegisterRequest userRequest = new RegisterRequest(user_name, email, password, phone, address);
        disposable.add(
                apiService
                        .register(ConvertUtils.createPostRequest(userRequest))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BaseResponse<RegisterResponse>>() {
                            @Override
                            public void onSuccess(BaseResponse<RegisterResponse> response) {
                                int response_status = response.getResponse_status();
                                if (response_status == 0) {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_content().getMsg();
                                    IntentUtils.gotoActive(email,msg[0],context);

                                } else {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_msg();
                                    AlertUtils.showAlertError(context);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Error", e.getMessage());
                                //ErrorUtils.showError(e,coordinatorLayout);

                            }
                        }));
    }

    //Active Response
    public static void activeResponse(final String email,final String verify,final ApiService apiService, final Context context) {
        disposable.clear();
        final String[] msg = {""};
        ActiveRequest userActiveRequest=new ActiveRequest(email,verify);
        disposable.add(
                apiService
                        .active(ConvertUtils.createPostRequest(userActiveRequest))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BaseResponse<ActiveResponse>>() {
                            @Override
                            public void onSuccess(BaseResponse<ActiveResponse> response) {
                                int response_status = response.getResponse_status();
                                if (response_status == 0) {
                                    dialog.dismiss();
                                    AlertUtils.showAlertActiveDone(context,msg[0]);

                                } else {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_msg();
                                    AlertUtils.showAlertMgs(context,msg[0]);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Error", e.getMessage());

                            }
                        }));

    }

    //Forgot Response
    public static void forgotResponse(final String email,final ApiService apiService, final Context context) {
        disposable.clear();
        dialog =DialogUtils.showLoadingDialog(context);
        final String[] msg = {""};
        ForgotRequest userForgotRequest=new ForgotRequest(email);
        disposable.add(
                apiService
                        .forgot(ConvertUtils.createPostRequest(userForgotRequest))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BaseResponse<ForgotResponse>>() {
                            @Override
                            public void onSuccess(BaseResponse<ForgotResponse> response) {
                                int response_status = response.getResponse_status();
                                if (response_status == 0) {
                                    msg[0] = response.getResponse_msg();
                                    dialog.dismiss();
                                    IntentUtils.gotoUpdatePassword(email,msg[0],context);
                                } else {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_msg();
                                    AlertUtils.showAlertMgs(context, msg[0]);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Error", e.getMessage());

                            }
                        }));

    }

    //Update password
    public static void updatePasswordResponse(final String email,final String newPassword,final String token, final ApiService apiService, final Context context) {
        disposable.clear();
        final String[] msg = {""};
        dialog =DialogUtils.showLoadingDialog(context);
        ChangePasswordRequest changePasswordRequest=new ChangePasswordRequest(email,newPassword,token);
        disposable.add(
                apiService
                        .changePassword(ConvertUtils.createPostRequest(changePasswordRequest))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BaseResponse<ChangePasswordResponse>>() {
                            @Override
                            public void onSuccess(BaseResponse<ChangePasswordResponse> response) {
                                int response_status = response.getResponse_status();
                                if (response_status == 0) {
                                    dialog.dismiss();
                                    AlertUtils.showAlertUpdatePassDone(context,"Cập nhật mật khẩu thành công");
                                } else {
                                    dialog.dismiss();
                                    msg[0] = response.getResponse_msg();
                                    AlertUtils.showAlertMgs(context, msg[0]);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Error", e.getMessage());

                            }
                        }));

    }


}
