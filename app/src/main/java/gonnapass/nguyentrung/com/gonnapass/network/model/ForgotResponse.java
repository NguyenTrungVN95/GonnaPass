package gonnapass.nguyentrung.com.gonnapass.network.model;

import com.google.gson.annotations.SerializedName;

public class ForgotResponse {
    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
