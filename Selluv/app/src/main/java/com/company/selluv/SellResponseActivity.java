package com.company.selluv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.company.selluv.adapter.SellResponsePagerAdapter;
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

public class SellResponseActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    //private JSONArray orderMembers;
    private ArrayList<ItemVO> itemList;
    private ArrayList<OrderMemberViewVO> orderMemberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        tabLayout = (TabLayout) findViewById(R.id.tableLayout);
        tabLayout.addTab(tabLayout.newTab().setText("OrderMember"));
        tabLayout.addTab(tabLayout.newTab().setText("OrderForm"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        SellResponseListTask th  = new SellResponseListTask(this);
        //getIntent()해서 form_code에 값 줘야 함
        th.execute("http://192.168.30.21:8089/m.orderMemberView?form_code=form-2");
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

                FormWriteTask formWriteTask = new FormWriteTask(SellResponseActivity.this);
                formWriteTask.execute("http://192.168.30.21:8089/m.orderSheetSearch.do/form-11/id1");//

            }
            catch (JSONException e){
                Log.e("SellResponseTask onPostExecute json Exception","error");
                e.printStackTrace();
            }
        }

    }

    class FormWriteTask extends AsyncTask<String, Void, String>{

        private Activity activity;
        public FormWriteTask(Activity activity){this.activity = activity;}

        ProgressDialog loading = new ProgressDialog(SellResponseActivity.this);

        @Override
        protected void onPreExecute() {
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.setMessage("주문서 불러오는 중...");
            loading.show();
        }


        @Override
        protected String doInBackground(String... params) {
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
                Log.e("task","formWriteTask error");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                itemList = new ArrayList<ItemVO>();
                Log.i("result", "result : " + result);
                JSONArray jary = new JSONArray(result);

                itemList.add(new ItemVO("0","주문서 제목","주문서 내용","Y","re","2018-08-26 ~ 2018-08-28"));
                for(int i=0; i<jary.length(); i++) {
                    JSONObject jobj = jary.getJSONObject(i);

                    String itemNum = jobj.getString("itemNum");
                    String itemTitle = jobj.getString("itemTitle");
                    String itemDescript = jobj.getString("itemDescript");
                    String itemNecessry = jobj.getString("itemNecessry");
                    String itemType = jobj.getString("itemType");
                    String options = jobj.getString("options");

                    ItemVO itemVO;

                    if(itemType.equals("ss") || itemType.equals("ms")){
                        itemVO= new ItemVO(itemNum, itemTitle, itemDescript, itemNecessry, itemType, options);
                    }
                    else{
                        itemVO = new ItemVO(itemNum, itemTitle, itemDescript, itemNecessry, itemType);
                    }

                    //CommentView commentView = new CommentView(memberId, commentText);

                    itemList.add(itemVO);
                }
                System.out.println(itemList);
                Log.i("FormWriteActivity onPostExecute" ,"itemList" + itemList);
                loading.dismiss();

                SellResponsePagerAdapter pagerAdapter = new SellResponsePagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), orderMemberList, itemList);
                viewPager.setAdapter(pagerAdapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            catch(JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
