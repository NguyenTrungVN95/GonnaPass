package gonnapass.nguyentrung.com.gonnapass.network.model;

import com.google.gson.annotations.SerializedName;

public class ForgotRequest {
    @SerializedName("email")
    String email;

    public ForgotRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
