package com.example.haniumvra;

import android.app.Application;

import com.example.haniumvra.utils.PreferenceData;


public class PjApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceData.init(this);
    }
}
