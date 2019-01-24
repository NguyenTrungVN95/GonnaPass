package gonnapass.nguyentrung.com.gonnapass.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ConvertUtils {
    public static RequestBody createPostRequest(Object request) {
        String jsonRawString = new Gson().toJson(request);
        return RequestBody.create(MultipartBody.FORM, TextUtils.isEmpty(jsonRawString) ? "" : jsonRawString);
    }
}
