package com.example.haniumvra.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 민아 on 2018-08-13.
 */

public class VocaVo implements Parcelable {

    @SerializedName("vocaid")
    public int voca_id= 0;
    @SerializedName("dayid")
    public int day_id = 0;
    @SerializedName("levelid")
    public int level_id= 0;
    @SerializedName("unityid")
    public int unity_id = 0;
    @SerializedName("voca")
    public String voca = "";
    @SerializedName("vocam")
    public String voca_m = "";
    @SerializedName("vocac")
    public int voca_c = 0;
    @SerializedName("vocae")
    public String voca_e = "";
    @SerializedName("vocaimg")
    public String voca_img = "";


    public int getVoca_id() {
        return voca_id;
    }

    public void setVoca_id(int voca_id) {
        this.voca_id = voca_id;
    }

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

    public int getUnity_id() {
        return unity_id;
    }

    public void setUnity_id(int unity_id) {
        this.unity_id = unity_id;
    }

    public String getVoca() {
        return voca;
    }

    public void setVoca(String voca) {
        this.voca = voca;
    }

    public String getVoca_m() {
        return voca_m;
    }

    public void setVoca_m(String voca_m) {
        this.voca_m = voca_m;
    }

    public int getVoca_c() {
        return voca_c;
    }

    public void setVoca_c(int voca_c) {
        this.voca_c = voca_c;
    }

    public String getVoca_e() {
        return voca_e;
    }

    public void setVoca_e(String voca_e) {
        this.voca_e = voca_e;
    }

    public String getVoca_img() {
        return voca_img;
    }

    public void setVoca_img(String voca_img) {
        this.voca_img = voca_img;
    }

    protected VocaVo(Parcel in) {
        voca_id = in.readInt();
        day_id = in.readInt();
        level_id = in.readInt();
        unity_id = in.readInt();
        voca = in.readString();
        voca_m = in.readString();
        voca_c = in.readInt();
        voca_e = in.readString();

    }

    public static final Creator<VocaVo> CREATOR = new Creator<VocaVo>() {
        @Override
        public VocaVo createFromParcel(Parcel in) {
            return new VocaVo(in);
        }

        @Override
        public VocaVo[] newArray(int size) {
            return new VocaVo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(voca_id);
        dest.writeInt(day_id);
        dest.writeInt(level_id);
        dest.writeInt(unity_id);
        dest.writeString(voca);
        dest.writeString(voca_m);
        dest.writeInt(voca_c);
        dest.writeString(voca_e);
    }
}
