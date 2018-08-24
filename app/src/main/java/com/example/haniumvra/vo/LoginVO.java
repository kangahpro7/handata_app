package com.example.haniumvra.vo;

import com.google.gson.annotations.SerializedName;

public class LoginVO {

    @SerializedName("user_id")
    private String user_id = "";

    @SerializedName("user_pw")
    private String user_pw = "";

    @SerializedName("user_name")
    private String user_name = "";

    @SerializedName("user_email")
    private String user_email = "";

    @SerializedName("usernum")
    private int usernum = 0;

    @SerializedName("dayId")
    private int dayId = 0;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
}
