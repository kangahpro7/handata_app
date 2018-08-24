package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.LoginVO;
import com.example.haniumvra.vo.LoginVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResult {


    @SerializedName("success")
    public boolean success = false;

    @SerializedName("usernum")
    public int usernum = 0;

    @SerializedName("dayId")
    public int day_id = 0;

    @SerializedName("userInfo")
    private ArrayList<LoginVO> userInfo = new ArrayList<LoginVO>();


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<LoginVO> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ArrayList<LoginVO> userInfo) {
        this.userInfo = userInfo;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }
}
