package com.example.haniumvra;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.haniumvra.myvoca.MyVocaActivity;
import com.example.haniumvra.rank.RankActivity;
import com.example.haniumvra.user.LoginActivity;
import com.example.haniumvra.utils.PreferenceData;
import com.example.haniumvra.view.TopbarView;
import com.example.haniumvra.vo.UserInfoVO;
import com.example.haniumvra.vo.VocaVo;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;


public class MainActivity extends AppCompatActivity {

    private TopbarView mainTopbarView;
    private ArrayList<VocaVo> vocaVoArrayList = new ArrayList<VocaVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent() != null){
            vocaVoArrayList = getIntent().getParcelableArrayListExtra("vocaList");
        }

        mainTopbarView = (TopbarView) findViewById(R.id.title);
        mainTopbarView.setType(TopbarView.TOPBAR_TYPE.MAIN);

    }

    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.start_logout:

                PreferenceData.setKeyAutoLogin(false);
                PreferenceData.setKeyLoginSuccess(false);
                PreferenceData.setKeyUserId("");
                PreferenceData.setKeyUserPw("");
                PreferenceData.setKeyUserEmail("");
                PreferenceData.setKeyUserName("");

                intent = new Intent(getApplicationContext(), IntroActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                break;

            case R.id.start_my_voca:

                intent = new Intent(MainActivity.this, MyVocaActivity.class);
                startActivity(intent);


                break;

            case R.id.start_btn_unity:

                UserInfoVO vo = (UserInfoVO) v.getTag();

                intent = new Intent(MainActivity.this, UnityPlayerActivity.class);
                intent.putExtra("userid", PreferenceData.getKeyUserId());
                intent.putExtra("usernum", PreferenceData.getKeyUsernum());
                intent.putExtra("dayId", PreferenceData.getKeyDayId());
                Log.d("usernum", "유저넘버 : " + PreferenceData.getKeyUsernum());
                Log.d("userid", "유저아이디 : " + PreferenceData.getKeyUserId());
                Log.d("dayId", "일차 : " + PreferenceData.getKeyDayId());
                startActivity(intent);


                break;

            case R.id.start_rank:

                intent = new Intent(MainActivity.this, RankActivity.class);
                startActivity(intent);


                break;

           
        }
    }
}
