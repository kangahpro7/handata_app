package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.UserInfoVO;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class UserInfoListResult {

    @SerializedName("success")
    public boolean success = true;

    @SerializedName("categoryProductList")
    public ArrayList<UserInfoVO> userinfos = new ArrayList<UserInfoVO>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<UserInfoVO> getUserInfo() {
        return userinfos;
    }

    public void setUserinfos(ArrayList<UserInfoVO> userinfos) {
        this.userinfos = userinfos;
    }
}
