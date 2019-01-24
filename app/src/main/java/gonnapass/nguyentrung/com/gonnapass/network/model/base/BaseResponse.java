package gonnapass.nguyentrung.com.gonnapass.network.model.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("response_status")
    int response_status;
    @SerializedName("response_msg")
    String response_msg;
    @SerializedName("response_content")
    T response_content;

    public int getResponse_status() {
        return response_status;
    }

    public void setResponse_status(int response_status) {
        this.response_status = response_status;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    public T getResponse_content() {
        return response_content;
    }

    public void setResponse_content(T response_content) {
        this.response_content = response_content;
    }
}
