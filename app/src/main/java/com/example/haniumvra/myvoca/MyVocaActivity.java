package com.example.haniumvra.myvoca;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haniumvra.R;
import com.example.haniumvra.db.DbController;
import com.example.haniumvra.myvoca.MyVocaListAdapter;
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.NetworkProgressDialog;
import com.example.haniumvra.network.response.RankResult;
import com.example.haniumvra.network.response.ServerSuccessCheckResult;
import com.example.haniumvra.network.response.VocaItemListResult;
import com.example.haniumvra.task.JoinAsyncTask;
import com.example.haniumvra.task.MyPageDelAsyncTask;
import com.example.haniumvra.task.MypageVocaAsyncTask;
import com.example.haniumvra.user.JoinActivity;
import com.example.haniumvra.user.JoinSuccessActivity;
import com.example.haniumvra.utils.PreferenceData;
import com.example.haniumvra.utils.Utils;
import com.example.haniumvra.vo.VocaVo;

import static java.security.AccessController.getContext;


public class MyVocaActivity extends AppCompatActivity {

    private NetworkProgressDialog networkProgressDialog;


    private RecyclerView recyclerView;
    private MyVocaListAdapter myVocaListAdapter;
    private TextView emptyText;

    private int usernum = 0;
   

    private View.OnClickListener itemDelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            networkProgressDialog.show();

            VocaVo vo = (VocaVo) v.getTag();

            MyPageDelAsyncTask MyPageDelAsyncTask = new MyPageDelAsyncTask(MyVocaActivity.this, new MyPageDelAsyncTask.TaskResultHandler() {
                @Override
                public void onSuccessAppAsyncTask(ServerSuccessCheckResult result) {

                    if(!result.isSuccess()){
                        networkProgressDialog.dismiss();
                        Toast.makeText(MyVocaActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                    }else{
                        callMyVoca();
                    }

                }

                @Override
                public void onFailAppAsysncask() {
                    networkProgressDialog.dismiss();
                    Toast.makeText(MyVocaActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelAppAsyncTask() {
                    networkProgressDialog.dismiss();
                    Toast.makeText(MyVocaActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                }
            });

            MyPageDelAsyncTask.execute(ApiValue.API_MYPAGE_DEL, PreferenceData.getKeyUserId(), String.valueOf(vo.getVoca_id()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvoca);

        usernum = getIntent().getIntExtra("usernum", 0);

//        topbarView = (TopbarView) findViewById(R.id.title);
//        topbarView.setType(TopbarView.TOPBAR_TYPE.BACK_TITLE);
//        topbarView.setTopBarTitle(getIntent().getStringExtra("title"));
//        topbarView.setTopMenuBackClick(new TopbarView.ItemClick() {
//            @Override
//            public void onItemClick() {
//                finish();
//            }
//        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        emptyText = (TextView)findViewById(R.id.empty_text);

        recyclerView.setNestedScrollingEnabled(false);

        myVocaListAdapter = new MyVocaListAdapter(getApplicationContext(), itemDelListener);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(myVocaListAdapter);

        networkProgressDialog = new NetworkProgressDialog(this);



        networkProgressDialog.show();


        callMyVoca();


    }



    @Override
    public void onResume() {
        super.onResume();
        callMyVoca();
    }

    public void callMyVoca(){
        MypageVocaAsyncTask MypageVocaAsyncTask = new MypageVocaAsyncTask(new MypageVocaAsyncTask.TaskResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(VocaItemListResult result) {
                networkProgressDialog.dismiss();

                    if (result.isSuccess()) {

                        if (result.getVocaVoArrayList() != null && result.getVocaVoArrayList().size() > 0) {

                            recyclerView.setVisibility(View.VISIBLE);
                            emptyText.setVisibility(View.GONE);

                            myVocaListAdapter.setMyVocaItemData(result.getVocaVoArrayList());
                            myVocaListAdapter.notifyDataSetChanged();

                            DbController.deleteAll(getApplicationContext());

                            for(VocaVo item : result.getVocaVoArrayList()){
                                DbController.addVocaId(getApplicationContext(), item.getVoca_id());
                            }

                        } else{
                            recyclerView.setVisibility(View.GONE);
                            emptyText.setVisibility(View.GONE);

                        }

                    }else{

                        recyclerView.setVisibility(View.GONE);
                        emptyText.setVisibility(View.GONE);


                    }

                }




            @Override
            public void onFailAppAsysncask() {

                    networkProgressDialog.dismiss();

                Toast.makeText(MyVocaActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {

                    networkProgressDialog.dismiss();

                Toast.makeText(MyVocaActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

            }
        });
        MypageVocaAsyncTask.execute(ApiValue.API_MYPAGE_VOCA, PreferenceData.getKeyUserId());


    }




}
