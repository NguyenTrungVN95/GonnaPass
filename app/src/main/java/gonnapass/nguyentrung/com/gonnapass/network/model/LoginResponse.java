package gonnapass.nguyentrung.com.gonnapass.network.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("email")
    private String email;
    @SerializedName("session_key")
    private String session_key;

    public LoginResponse(String id, String full_name, String email, String session_key) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.session_key = session_key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
