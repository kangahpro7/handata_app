package com.example.haniumvra.network.response;

import com.example.haniumvra.vo.IdSearchVo;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;


public class IdSearchResult {

    @SerializedName("success")
    public boolean success = true;

    @SerializedName("idList")
    public ArrayList<IdSearchVo> IdInfoVOArrayList = new ArrayList<IdSearchVo>();

    @SerializedName("message")
    String message="";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<IdSearchVo> getIdInfoVOArrayList() {
        return IdInfoVOArrayList;
    }

    public void setIdInfoVOArrayList(ArrayList<IdSearchVo> IdInfoVOArrayList) {
        this.IdInfoVOArrayList = IdInfoVOArrayList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
