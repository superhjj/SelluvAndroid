package com.company.selluv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.company.selluv.adapter.SellResponseDetailAdapter;
import com.company.selluv.model.ItemVO;
import com.company.selluv.model.OrderMemberDetailVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SellResponseDetailActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<OrderMemberDetailVO> orderDetailList;
    private SellResponseDetailAdapter sellResponseDetailAdapter;
    String memberId;
    String formCode;
    String myId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_response_detail);
        Log.d("SellResponseDetailActivity", "onCreate()호출됨");
        Intent intent = getIntent();
        memberId = intent.getStringExtra("memberId");
        formCode = intent.getStringExtra("formCode");
        myId = "id1";//로그인한 사용자 아이디(폼 작성자)

        listView = (ListView)findViewById(R.id.sell_detail);

        SellResponseDetailTask th = new SellResponseDetailTask();
        //로그인 된 아이디도 날려주는 걸로 바뀌어야 함. -> 바꿈
        th.execute("http://192.168.30.21:8089/m.orderMemberDetail?form_code="+formCode+"&member_id="+memberId+"&my_id="+myId);
    }

    class SellResponseDetailTask extends AsyncTask<String, Void, String>{
        ProgressDialog loading = new ProgressDialog(SellResponseDetailActivity.this);

        @Override
        protected void onPreExecute() {
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.setMessage("주문서 불러오는 중...");
            loading.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
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

            orderDetailList = new ArrayList<OrderMemberDetailVO>();

            try {
                Log.i("result", "result : " + result);
                JSONArray jary = new JSONArray(result);

                for (int i = 0; i < jary.length(); i++) {
                    JSONObject jobj = jary.getJSONObject(i);

                    String orderDate = jobj.getString("order_date");
                    String orderMember = jobj.getString("order_member");
                    JSONArray jsonAnswer = jobj.getJSONArray("answer");
                    String[] answers = new String[jsonAnswer.length()];

                    for (int j = 0; j < jsonAnswer.length(); ++j) {
                        answers[j] = jsonAnswer.getString(j);
                    }

                    /*[{"order_date":"주문서 작성 일자","order_member":"주문자 id","answer":["항목1","항목2","항목3"]},
                    {"order_date":"2018-08-14","order_member":"id4","answer":["id4","M사이즈","3시나 4시쯤 배달해주세요."]}]*/
                    OrderMemberDetailVO orderMemberDetailVO = new OrderMemberDetailVO(orderDate, orderMember, answers);

                    orderDetailList.add(orderMemberDetailVO);
                }
                Log.i("SellResponseDetailActivity onPostExecute", "orderDetailList" + orderDetailList);

                ArrayList<String[]> answers = new ArrayList<>();
                for(int i=0; i<orderDetailList.get(0).getAnswer().length; ++i) {
                    String[] strings = new String[2];
                    strings[0] = orderDetailList.get(0).getAnswer()[i];
                    strings[1] = orderDetailList.get(1).getAnswer()[i];

                    answers.add(strings);
                }

                Log.i("answers",answers.toString());

                sellResponseDetailAdapter = new SellResponseDetailAdapter(SellResponseDetailActivity.this, answers);
                listView.setAdapter(sellResponseDetailAdapter);
                loading.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
