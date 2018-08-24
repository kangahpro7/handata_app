package com.example.haniumvra.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haniumvra.MainActivity;
import com.example.haniumvra.R;
import com.example.haniumvra.db.DbController;
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.NetworkProgressDialog;
import com.example.haniumvra.network.response.LoginResult;
import com.example.haniumvra.network.response.VocaItemListResult;
import com.example.haniumvra.task.MypageVocaAsyncTask;
import com.example.haniumvra.task.UserLoginAsyncTask;
import com.example.haniumvra.utils.PreferenceData;
import com.example.haniumvra.utils.Utils;
import com.example.haniumvra.vo.VocaVo;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity implements View.OnClickListener {


    private TextView loginBtn, joinBtn, findIdPw;

    private EditText loginId, loginPw;
    private CheckBox autoLogin;

    private NetworkProgressDialog networkProgressDialog;
    private ArrayList<VocaVo> VocaVoS = new ArrayList<VocaVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        networkProgressDialog = new NetworkProgressDialog(this);

        if(getIntent() != null)
            VocaVoS = getIntent().getParcelableArrayListExtra("vocaList");

        loginBtn = (TextView) findViewById(R.id.login_btn);
        joinBtn = (TextView) findViewById(R.id.join_btn);

        loginId = (EditText)findViewById(R.id.login_id);
        loginPw = (EditText)findViewById(R.id.login_pw);

        findIdPw = (TextView) findViewById(R.id.find_id_pw);
        findIdPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, IdSecurityActivity.class);
                if(VocaVoS != null && VocaVoS.size() > 0)
                    intent.putParcelableArrayListExtra("vocaList", VocaVoS);
                startActivity(intent);
            }
        });

        autoLogin = (CheckBox) findViewById(R.id.login_auto);
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceData.setKeyAutoLogin(isChecked);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(loginId.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(loginPw.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Utils.isValidId(loginId.getText().toString())){
                    Toast.makeText(LoginActivity.this, "아이디는 영소문자+숫자 조합으로 4~16자 이내로 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Utils.isValidPw(loginPw.getText().toString())){
                    Toast.makeText(LoginActivity.this, "비밀번호는 영소문자+숫자 조합으로 8~16자 이내로 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                networkProgressDialog.show();

                login();
            }
        });



    }




    private void login() {

        UserLoginAsyncTask task = new UserLoginAsyncTask(new UserLoginAsyncTask.UserLoginResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(LoginResult result) {

                if (result.isSuccess()) {

                    if(result.getUserInfo() != null && result.getUserInfo().size() > 0){

                        PreferenceData.setKeyLoginSuccess(true);

                        PreferenceData.setKeyUserId(loginId.getText().toString());
//                        PreferenceData.setKeyUserName(result.getUserInfo().get(0).getUser_name());
//                        PreferenceData.setKeyUserEmail(result.getUserInfo().get(0).getUser_email());

                        PreferenceData.setKeyUsernum(result.getUserInfo().get(0).getUsernum());
                        PreferenceData.setKeyDayId(result.getUserInfo().get(0).getDayId());


                    }


                    // 자동 로그인 체크시 패스워드 저장
                    if (PreferenceData.getKeyAutoLogin()) {
                        PreferenceData.setKeyUserPw(loginPw.getText().toString());

                        callMyVoca();
                    }else{

                        networkProgressDialog.dismiss();

                        moveMain();
                    }


                } else {
                    networkProgressDialog.dismiss();

                    PreferenceData.setKeyLoginSuccess(false);
                    Toast.makeText(LoginActivity.this, "로그인 실패. 아이디와 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailAppAsysncask() {
                networkProgressDialog.dismiss();

                PreferenceData.setKeyLoginSuccess(false);
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {
                networkProgressDialog.dismiss();

                PreferenceData.setKeyLoginSuccess(false);
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }
        });


        task.execute(ApiValue.API_USER_LOGIN, loginId.getText().toString(), loginPw.getText().toString());
    }


    public void onClick(View v)
    {
        Intent intent = new Intent(this, JoinActivity.class);
        if(VocaVoS != null && VocaVoS.size() > 0)
            intent.putParcelableArrayListExtra("vocaList", VocaVoS);
        startActivity(intent);
    }


    private void moveMain(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        if(VocaVoS != null && VocaVoS.size() > 0)
            intent.putParcelableArrayListExtra("vocaList", VocaVoS);

        startActivity(intent);
        finish();
    }


    /**
     * 자동 로그인하고 내 찜목록 전부 받아오는 task
     */
    private void callMyVoca(){

        MypageVocaAsyncTask mypageVocaAsyncTask = new MypageVocaAsyncTask(new MypageVocaAsyncTask.TaskResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(VocaItemListResult result) {

                networkProgressDialog.dismiss();

                if (result.isSuccess()) {

                    if (result.getVocaVoArrayList() != null && result.getVocaVoArrayList().size() > 0) {

                        for(VocaVo item : result.getVocaVoArrayList()){
                            DbController.addVocaId(LoginActivity.this, item.getVoca_id());
                        }

                    }
                }

                moveMain();
            }

            @Override
            public void onFailAppAsysncask() {

                networkProgressDialog.dismiss();

                moveMain();
            }

            @Override
            public void onCancelAppAsyncTask() {

                networkProgressDialog.dismiss();

                moveMain();
            }
        });

        mypageVocaAsyncTask.execute(ApiValue.API_MYPAGE_VOCA, PreferenceData.getKeyUserId());
    }





    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

