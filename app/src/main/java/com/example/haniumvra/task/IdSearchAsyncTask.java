package com.example.haniumvra.task;

import android.os.AsyncTask;

import com.example.haniumvra.network.HttpRequest;
import com.example.haniumvra.network.response.IdSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Note on 2018-07-19.
 */

public class IdSearchAsyncTask extends AsyncTask<String, Integer, IdSearchResult> {

    private IdSearchHandler handler;

    public interface IdSearchHandler{
        public void onSuccessAppAsyncTask(IdSearchResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }

    public IdSearchAsyncTask(IdSearchHandler handler){this.handler = handler;}

    @Override
    protected void  onPreExecute(){super.onPreExecute();}

    @Override
    protected IdSearchResult doInBackground(String... strings) {
    String path = strings[0];
    String username = strings[1];
    String useremail = strings[2];

    IdSearchResult result = null;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("username",username);
        params.put("useremail",useremail);


        HttpRequest request = new HttpRequest();

        try{
            String str = request.callRequestServer(path,"POST",params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str,IdSearchResult.class);
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return result;
    }


    @Override
    protected  void onPostExecute(IdSearchResult idSearchResult){
        super.onPostExecute(idSearchResult);

        if(idSearchResult != null){
            handler.onSuccessAppAsyncTask(idSearchResult);
        }
        else{
            handler.onFailAppAsysncask();
        }
    }
}
