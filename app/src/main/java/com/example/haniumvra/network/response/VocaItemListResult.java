package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.VocaVo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 민아 on 2018-08-13.
 */

public class VocaItemListResult {

    @SerializedName("success")
    public boolean success = true;

    @SerializedName("vocaInfoList")
    public ArrayList<VocaVo> vocaVoArrayList = new ArrayList<VocaVo>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<VocaVo> getVocaVoArrayList() {
        return vocaVoArrayList;
    }

    public void setVocaVoArrayList(ArrayList<VocaVo> vocaVoArrayList) {
        this.vocaVoArrayList = vocaVoArrayList;
    }
    
}
