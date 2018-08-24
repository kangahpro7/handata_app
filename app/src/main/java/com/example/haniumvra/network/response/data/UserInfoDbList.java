package com.example.haniumvra.network.response.data;


import com.example.haniumvra.vo.UserInfoVO;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class UserInfoDbList {

    @SerializedName("drama")
    ArrayList<UserInfoVO> userInfoList = new ArrayList<UserInfoVO>();

}
