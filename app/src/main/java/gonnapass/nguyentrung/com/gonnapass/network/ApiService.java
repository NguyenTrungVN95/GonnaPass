package gonnapass.nguyentrung.com.gonnapass.network;

import gonnapass.nguyentrung.com.gonnapass.network.model.ActiveResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.ForgotResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.LoginResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.RegisterResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.base.BaseResponse;
import gonnapass.nguyentrung.com.gonnapass.network.model.ChangePasswordResponse;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    // Register new user
    @POST("api/app/registration/create")
    Single<BaseResponse<RegisterResponse>> register(@Body RequestBody userRequest);
    // Login
    @POST("api/app/session/create")
    Single<BaseResponse<LoginResponse>> login(@Body RequestBody userLoginRequest);
    // Active account
    @POST("api/app/activation/create")
    Single<BaseResponse<ActiveResponse>> active(@Body RequestBody userActiveRequest);
    //Forgot password
    @POST(" api/app/password/create")
    Single<BaseResponse<ForgotResponse>> forgot(@Body RequestBody userForgotRequest);
    //Update password
    @POST("api/app/password/update")
    Single<BaseResponse<ChangePasswordResponse>> changePassword(@Body RequestBody userChangePasswordRequest);

}
