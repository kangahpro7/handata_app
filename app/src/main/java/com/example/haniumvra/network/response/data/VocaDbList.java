package com.example.haniumvra.network.response.data;

import com.example.haniumvra.vo.VocaVo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VocaDbList {

    @SerializedName("vocaInfoList")
    ArrayList<VocaVo> vocaInfoList = new ArrayList<VocaVo>();

}
