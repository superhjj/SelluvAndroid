package com.company.selluv;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.company.selluv.adapter.FormItemListViewAdapter;
import com.company.selluv.adapter.SellResponseListAdapter;
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

public class OrderResponseActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<OrderMemberViewVO> orderMemberList;
    private SellResponseListAdapter sellResponseListAdapter;
    private String myId;//로그인한 사용자 id(form 작성자 일 경우)
    private String memberId;//폼 제출자 아이디
    private String formCode;//해당 폼의 코드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_response);
        Log.d("OrderResponseActivity", "onCreate()호출됨");

        //itemList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.orderdetailList);
        //listView.getCount

        //user peed에서 intent로 form re 정보 받기!
        myId="id1";
        formCode = "form-2";

        SellResponseListTask th  = new SellResponseListTask(this);
        th.execute("http://192.168.30.21:8089/m.orderMemberView?form_code="+formCode);
    }

    public void transform(String formCode, String memberId){
        this.formCode = formCode;
        this.memberId = memberId;
        Intent intent = new Intent();
        intent.putExtra("memberId", memberId);
        intent.putExtra("formCode", formCode);
        intent.setClass(getApplicationContext(), SellResponseDetailActivity.class);
        startActivity(intent);
    }

    class SellResponseListTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        public SellResponseListTask(Activity activity){this.activity = activity;}

        @Override
        protected String doInBackground(String... params) {
            Log.d("SellResponse","doback");
            try{
                URL url = new URL(params[0]);
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
                Log.e("task","SellResponseListTask error");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            orderMemberList = new ArrayList<OrderMemberViewVO>();
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

                sellResponseListAdapter = new SellResponseListAdapter(activity, orderMemberList);
                listView.setAdapter(sellResponseListAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.i("on item click", "clicked"+position);
                        String formCode = orderMemberList.get(position).getForm_code();
                        String memberId = orderMemberList.get(position).getMember_id();
                        transform(formCode, memberId);

                    }
                });
                /*
                        myListAdapter = new MyListAdapter(MainActivity.this,list_itemArrayList);
        listView.setAdapter(myListAdapter);

//onItemClickListener를 추가
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
});

                */


            }
            catch (JSONException e){
                Log.e("SellResponseTask onPostExecute json Exception","error");
                e.printStackTrace();
            }


        }

    }
}
