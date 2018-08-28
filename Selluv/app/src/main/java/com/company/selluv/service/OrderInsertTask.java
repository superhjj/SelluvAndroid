package com.company.selluv.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class OrderInsertTask extends AsyncTask<ListView, Void, String> {
    public final Context context;
    private ListView listView;
    public OrderInsertTask(Context context) {this.context = context;}

    @Override
    protected String doInBackground(ListView... params) {
        listView = params[0];


        try{

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }

}
