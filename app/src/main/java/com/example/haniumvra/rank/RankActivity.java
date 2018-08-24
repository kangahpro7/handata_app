package com.example.haniumvra.rank;

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
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.NetworkProgressDialog;
import com.example.haniumvra.network.response.RankResult;
import com.example.haniumvra.network.response.ServerSuccessCheckResult;
import com.example.haniumvra.task.JoinAsyncTask;
import com.example.haniumvra.task.RankAsyncTask;
import com.example.haniumvra.user.JoinActivity;
import com.example.haniumvra.user.JoinSuccessActivity;
import com.example.haniumvra.utils.PreferenceData;
import com.example.haniumvra.utils.Utils;

import static java.security.AccessController.getContext;


public class RankActivity extends AppCompatActivity {

    private NetworkProgressDialog networkProgressDialog;


    private RecyclerView recyclerView;
    private RankListAdapter rankListAdapter;

    private String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

//        userid = getIntent().getIntExtra("userid", 0);

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

        recyclerView.setNestedScrollingEnabled(false);

        rankListAdapter = new RankListAdapter(getApplicationContext());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(rankListAdapter);

        networkProgressDialog = new NetworkProgressDialog(this);



        networkProgressDialog.show();


        callMyScore();


    }



    @Override
    public void onResume() {
        super.onResume();
        callMyScore();
    }

    public void callMyScore(){
        RankAsyncTask rankAsyncTask = new RankAsyncTask(new RankAsyncTask.RankResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(RankResult result) {

                    networkProgressDialog.dismiss();

                    if (result.isSuccess()) {

                        if (result.getRankList() != null && result.getRankList().size() > 0) {
                            rankListAdapter.setRankItemData(result.getRankList());
                            rankListAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(RankActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(RankActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                    }

                }


            @Override
            public void onFailAppAsysncask() {

                    networkProgressDialog.dismiss();

                Toast.makeText(RankActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {

                    networkProgressDialog.dismiss();

                Toast.makeText(RankActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

            }
        });
        rankAsyncTask.execute(ApiValue.API_RANK_TWO, PreferenceData.getKeyUserId());


    }



    
}
