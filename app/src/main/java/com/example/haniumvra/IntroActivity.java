package com.example.haniumvra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.example.haniumvra.db.DbController;
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.response.LoginResult;
import com.example.haniumvra.network.response.VocaItemListResult;
import com.example.haniumvra.task.MypageVocaAsyncTask;
import com.example.haniumvra.task.UserLoginAsyncTask;
import com.example.haniumvra.task.VocaAsyncTask;
import com.example.haniumvra.user.LoginActivity;
import com.example.haniumvra.utils.D;
import com.example.haniumvra.utils.PreferenceData;
import com.example.haniumvra.vo.VocaVo;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {

    private static final int NEXT_LOGIN = 100;
    private static final int NEXT_GET_CATEGORY = 200;
    private static final int NEXT_MAIN = 300;
    private static final int NEXT_GET_MYPRODUCT = 400;

    private ArrayList<VocaVo> VocaVoS = new ArrayList<VocaVo>();

    private Handler nextStepHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {


            switch (msg.what){

                case NEXT_LOGIN:

                    // 자동 로그인 여부 판단
                    if(PreferenceData.getKeyAutoLogin()){

                        // 자동 로그인시 로그인 요청 바로 들어가게끔
                        // 성공하면 메인으로 바로 이동
                        // 실패하면 로그인 페이지 노출
                        login();
                    }else{
                        PreferenceData.setKeyAutoLogin(false);
                        PreferenceData.setKeyLoginSuccess(false);
                        PreferenceData.setKeyUserId("");
                        PreferenceData.setKeyUserPw("");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);

                                if(VocaVoS != null && VocaVoS.size() > 0)
                                    intent.putParcelableArrayListExtra("vocaList", VocaVoS);

                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }


                    break;


                case NEXT_MAIN:


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);

                            if(VocaVoS != null && VocaVoS.size() > 0)
                                intent.putParcelableArrayListExtra("vocaList", VocaVoS);

                            startActivity(intent);
                            finish();
                        }
                    }, 2000);

                    break;

                //자동 로그인 성공했을 때만
                case NEXT_GET_MYPRODUCT:
                    callMyVoca();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_intro);

        callMyVoca();

    }

    private void login() {

        UserLoginAsyncTask task = new UserLoginAsyncTask(new UserLoginAsyncTask.UserLoginResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(LoginResult result) {

                if (result.isSuccess()) {

                    PreferenceData.setKeyLoginSuccess(true);

                    nextStepHandler.sendEmptyMessage(NEXT_GET_MYPRODUCT);

                } else {
                    PreferenceData.setKeyLoginSuccess(false);
                    PreferenceData.setKeyAutoLogin(false);
                    PreferenceData.setKeyUserId("");
                    PreferenceData.setKeyUserPw("");

                    Toast.makeText(IntroActivity.this, "로그인 실패. 아이디와 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();

                    nextStepHandler.sendEmptyMessage(NEXT_LOGIN);
                }


            }

            @Override
            public void onFailAppAsysncask() {

                PreferenceData.setKeyLoginSuccess(false);
                Toast.makeText(IntroActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

                nextStepHandler.sendEmptyMessage(NEXT_LOGIN);
            }

            @Override
            public void onCancelAppAsyncTask() {

                PreferenceData.setKeyLoginSuccess(false);
                Toast.makeText(IntroActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

                nextStepHandler.sendEmptyMessage(NEXT_LOGIN);
            }
        });

        task.execute(ApiValue.API_USER_LOGIN, PreferenceData.getKeyUserId(), PreferenceData.getKeyUserPw());
    }



    /**
     * 내 단어장 전부 받아오는 task
     */
    private void callMyVoca(){

        MypageVocaAsyncTask MypageVocaAsyncTask = new MypageVocaAsyncTask(new MypageVocaAsyncTask.TaskResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(VocaItemListResult result) {

                D.log("TAG", result.isSuccess() + "\n" + result.getVocaVoArrayList());

                if (result.isSuccess()) {

                    if (result.getVocaVoArrayList() != null && result.getVocaVoArrayList().size() > 0) {

                        DbController.deleteAll(IntroActivity.this);

                        for(VocaVo item : result.getVocaVoArrayList()){
                            DbController.addVocaId(IntroActivity.this, item.getVoca_id());
                        }

                    }
                }

                nextStepHandler.sendEmptyMessage(NEXT_MAIN);
            }

            @Override
            public void onFailAppAsysncask() {


                nextStepHandler.sendEmptyMessage(NEXT_MAIN);
                Toast.makeText(IntroActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {

                nextStepHandler.sendEmptyMessage(NEXT_MAIN);
                Toast.makeText(IntroActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

            }
        });

        MypageVocaAsyncTask.execute(ApiValue.API_MYPAGE_VOCA, PreferenceData.getKeyUserId());
    }



}
