package com.example.haniumvra.task;


import android.content.Context;
import android.os.AsyncTask;

import com.example.haniumvra.db.DbController;
import com.example.haniumvra.network.ApiValue;
import com.example.haniumvra.network.HttpRequest;
import com.example.haniumvra.network.response.ServerSuccessCheckResult;
import com.example.haniumvra.utils.D;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.HashMap;
import java.util.Map;

public class MyPageDelAsyncTask extends AsyncTask<String, Integer, ServerSuccessCheckResult> {

    private TaskResultHandler handler;
    private Context context;
    private int voca_id = 0;

    private boolean isAddHeart = false;

    public interface TaskResultHandler{
        public void onSuccessAppAsyncTask(ServerSuccessCheckResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }

    public MyPageDelAsyncTask(Context context, TaskResultHandler handler){
        this.context = context;
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ServerSuccessCheckResult doInBackground(String... strings) {

        String path = strings[0];
        String usernum = strings[1];
        voca_id = Integer.parseInt(strings[2]);


        ServerSuccessCheckResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usernum", usernum);
        params.put("vocaid", voca_id);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            D.log("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, ServerSuccessCheckResult.class);

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(ServerSuccessCheckResult AppAsyncTaskResult) {
        super.onPostExecute(AppAsyncTaskResult);

        if(AppAsyncTaskResult != null){

            try {

                    DbController.deleteVocaId(context, voca_id);

            }catch (Exception e){
                e.printStackTrace();
            }


            handler.onSuccessAppAsyncTask(AppAsyncTaskResult);
        }else{
            handler.onFailAppAsysncask();
        }

    }
}

//c+r
