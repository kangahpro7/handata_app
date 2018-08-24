package com.example.haniumvra.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.haniumvra.network.HttpRequest;
import com.example.haniumvra.network.response.VocaItemListResult;
import com.example.haniumvra.utils.D;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;


public class MypageVocaAsyncTask extends AsyncTask<String, Integer, VocaItemListResult> {
    private TaskResultHandler handler;


    public interface TaskResultHandler{
        public void onSuccessAppAsyncTask(VocaItemListResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }

    public MypageVocaAsyncTask(TaskResultHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected VocaItemListResult doInBackground(String... strings) {

        String path = strings[0];
        String userid = strings[1];

        VocaItemListResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            D.log("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, VocaItemListResult.class);

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(VocaItemListResult AppAsyncTaskResult) {
        super.onPostExecute(AppAsyncTaskResult);

        if(AppAsyncTaskResult != null){
            handler.onSuccessAppAsyncTask(AppAsyncTaskResult);
        }else{
            handler.onFailAppAsysncask();
        }

    }
}