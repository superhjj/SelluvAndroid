/*
package com.company.selluv.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.company.selluv.FormWriteActivity;
import com.company.selluv.adapter.FormItemListViewAdapter;
import com.company.selluv.adapter.SellResponseListAdapter;
import com.company.selluv.model.ItemVO;
import com.company.selluv.model.OrderMemberViewVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SellResponseListTask extends AsyncTask<String, Void, String> {
    public final Context context;
    public SellResponseListTask(Context context) {this.context = context;}

    @Override
    protected String doInBackground(String... strings) {

        String urlStr = "http://192.68.30.21/m.";
        try{
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(20000);
            con.setRequestMethod("GET");
            con.setDoInput(true);//데이터 받기
            con.setDoOutput(true);//데이터 받고 주는 양방향 통신

            int responseCode = con.getResponseCode();
            StringBuilder sb = new StringBuilder();

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } else {
                sb.append("Connection failed");
            }

            con.disconnect();
            return sb.toString();

        }
        catch (Exception e){
            Log.e("task","formWriteTask error");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        List<OrderMemberViewVO> orderMemberList = new ArrayList<OrderMemberViewVO>();
        try{
            Log.i("result", "result : " + result);
            JSONArray jary = new JSONArray(result);

            for(int i=0; i<jary.length(); i++) {
                JSONObject jobj = jary.getJSONObject(i);

                String formTitle = jobj.getString("form_title");
                String formCode = jobj.getString("form_code");
                String memberId = jobj.getString("member_id");
                String orderDate = jobj.getString("order_date");
                String profileImg = jobj.getString("profile_img");

                OrderMemberViewVO ov = new OrderMemberViewVO(formTitle,formCode,memberId, orderDate, profileImg);

                orderMemberList.add(ov);
            }
            Log.i("FormWriteActivity onPostExecute", "orderMemberList : " + orderMemberList);

            SellResponseListAdapter sellResponseListAdapter = new SellResponseListAdapter(context, orderMemberList)

            formItemListViewAdapter = new FormItemListViewAdapter(FormWriteActivity.this, itemList);
            listView.setAdapter(formItemListViewAdapter);
            loading.dismiss();
        }
        catch (JSONException e){
            Log.e("SellResponseTask onPostExecute json Exception","error");
            e.printStackTrace();
        }


    }

}
*/
