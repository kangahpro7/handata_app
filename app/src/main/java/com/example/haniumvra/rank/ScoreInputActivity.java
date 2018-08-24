/*package com.example.haniumvra.rank;

import android.content.Intent;
import android.widget.Toast;

import com.example.haniumvra.R;
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.response.ServerSuccessCheckResult;
import com.example.haniumvra.task.RankAsyncTask;
import com.example.haniumvra.user.JoinActivity;
import com.example.haniumvra.user.JoinSuccessActivity;



public class ScoreInputActivity {

    public void callMyScore(){
        RankAsyncTask rankAsyncTask = new RankAsyncTask(new RankAsyncTask.RankResultHandler() {

            @Override
            public void onSuccessAppAsyncTask(ServerSuccessCheckResult result) {

                networkProgressDialog.dismiss();

                if(result != null){
                    if(result.success){

                        Intent intent = new Intent(JoinActivity.this, JoinSuccessActivity.class);
//                                if(dramaVOS != null && dramaVOS.size() > 0)
//                                    intent.putParcelableArrayListExtra("dramaList", dramaVOS);
                        startActivity(intent);
                        finish();
                    }else{

                        Toast.makeText(JoinActivity.this, result.message, Toast.LENGTH_SHORT).show();

                    }

                }else{

                    Toast.makeText(JoinActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailAppAsysncask() {
                networkProgressDialog.dismiss();

                Toast.makeText(JoinActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {
                networkProgressDialog.dismiss();

                Toast.makeText(JoinActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }
        });

        networkProgressDialog.show();

        rankAsyncTask.execute(ApiValue.API_RANK, userId, userScore);


    }
}
*/