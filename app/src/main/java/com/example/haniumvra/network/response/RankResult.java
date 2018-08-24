package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.RankDataVo;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class RankResult {

    @SerializedName("success")
    public boolean success = true;

    @SerializedName("rankList")
    public ArrayList<RankDataVo> rankList = new ArrayList<RankDataVo>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<RankDataVo> getRankList() {
        return rankList;
    }

    public void setRankList(ArrayList<RankDataVo> rankList) {
        this.rankList = rankList;
    }
}
