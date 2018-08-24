package com.example.haniumvra.myvoca;

public class MyVocaItemData {
    private int image;
    private String voca;
    private String voca_m;


    public int getImage() {
        return this.image;
    }

    public String getVoca() {
        return this.voca;
    }

    public String getVoca_m() {
        return this.voca_m;
    }

    public MyVocaItemData(int image, String voca, String voca_m) {
        this.image = image;
        this.voca = voca;
        this.voca_m = voca_m;
    }
}
