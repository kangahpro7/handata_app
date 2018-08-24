package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.UserInfoVO;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class UserInfoResult {

    @SerializedName("success")
    public boolean success = true;

    @SerializedName("message")
    public String message = "";

    @SerializedName("signup")
    public ArrayList<UserInfoVO> UserInfoVOArrayList = new ArrayList<UserInfoVO>();
}
