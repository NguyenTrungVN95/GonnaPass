package gonnapass.nguyentrung.com.gonnapass.network.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("full_name")
    String fullName;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("phone")
    String phone;
    @SerializedName("address")
    String address;

    public RegisterRequest(String fullName, String email, String password, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
