package com.example.haniumvra.task;

import android.os.AsyncTask;

import com.example.haniumvra.network.HttpRequest;
import com.example.haniumvra.network.response.RankResult;
import com.example.haniumvra.network.response.RankResult;
import com.example.haniumvra.utils.D;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class RankAsyncTask extends AsyncTask<String, Integer, RankResult> {


    private RankResultHandler handler;


    public interface RankResultHandler{
        public void onSuccessAppAsyncTask(RankResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public RankAsyncTask(RankResultHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected RankResult doInBackground(String... strings) {

        String path = strings[0];
//        int userid = Integer.valueOf(strings[1]);
//        int usernum = Integer.valueOf(strings[1]);
//        String userid = strings[1];

        RankResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("userid", userid);


        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "GET", params);

            D.log("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, RankResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(RankResult RankResult) {
        super.onPostExecute(RankResult);


        if(RankResult != null){
            handler.onSuccessAppAsyncTask(RankResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
