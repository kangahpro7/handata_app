package com.example.haniumvra.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 민아 on 2018-08-12.
 */

public class RankDataVo {

    @SerializedName("userid")
    public String user_id = "";
    @SerializedName("userscore")
    public int user_score = 0;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

}
