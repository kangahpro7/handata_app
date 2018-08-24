package com.example.haniumvra.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 민아 on 2018-08-24.
 */

public class UserVocaVo {

    @SerializedName("dayId")
    public int day_id = 0;
    @SerializedName("levelId")
    public int level_id = 0;

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }
}
