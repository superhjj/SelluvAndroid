package com.company.selluv;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.company.selluv.adapter.FormItemListViewAdapter;
import com.company.selluv.model.ItemVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormWriteActivity extends AppCompatActivity {
    private ListView listView;
    private FormItemListViewAdapter formItemListViewAdapter;
    public HashMap<String, String> answerMap;
    String title;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_write);
        Log.d("formWrite", "onCreate()호출됨");

        //itemList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.lvFormLayout);
        answerMap = new HashMap<>();


        //listView.getCount

        //user peed에서 intent로 form re 정보 받기!

        FormWriteTask th = new FormWriteTask();
        th.execute("http://192.168.30.21:8089/m.orderSheetSearch.do/form-11/id1");

    }

    /*

       class MemberLogin extends AsyncTask<String, Void, String>{
        String addr = "%s/m.login?id=%s&pwd=%s";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject obj = new JSONObject(s);
                String id = obj.getString("memberId");
                String pwd = obj.getString("pwd");

                SharedData.memberId = id;
                SharedData.memberPwd = pwd;

                Toast.makeText(Login.this, "id : " + SharedData.memberId + "pwd : " + SharedData.memberPwd, Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            EditText idTxt = (EditText) findViewById(R.id.etId);
            EditText pwdTxt = (EditText) findViewById(R.id.etPwd);

            StringBuilder sb = null;
            String input_id = idTxt.getText().toString();
            String input_pwd = pwdTxt.getText().toString();

            Log.d(TAG, "입력한 아이디 : " + input_id);
            Log.d(TAG, "입력한 비밀번호 : " + input_pwd);

            String server = String.format(addr, SharedData.SERVER_URL, input_id, input_pwd);
            try{
                URL url = new URL(server);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(10000);
                con.setRequestMethod("POST");
                con.setDoInput(true);

                int responseCode = con.getResponseCode();
                Log.d(TAG, "응답코드 : " + responseCode);

                if(responseCode == HttpURLConnection.HTTP_OK){
                    sb = new StringBuilder();
                    String line = null;

                    InputStreamReader isr = new InputStreamReader(con.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while((line = br.readLine()) != null){
                        sb.append(line);
                    }

                    Log.d(TAG, sb.toString());
                }

                con.disconnect();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return sb.toString();
        }
    }

    */
/*    class OrderInsertTask extends  AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }*/



    class FormWriteTask extends AsyncTask<String, Void, String>{
        ProgressDialog loading = new ProgressDialog(FormWriteActivity.this);

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

            final ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
            try {
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

                formItemListViewAdapter = new FormItemListViewAdapter(FormWriteActivity.this, itemList, answerMap);
                listView.setAdapter(formItemListViewAdapter);
                loading.dismiss();

            }
            catch(JSONException e) {
                e.printStackTrace();
            }

            Button btn = new Button(listView.getContext());
            btn.setText("제출하기");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(), "제출", Toast.LENGTH_LONG);
                    Log.i("제출 버튼 : ", "제출버튼 선택" + answerMap);

                    StringBuilder sb = new StringBuilder();

                    title = "";
                    answer = "";

                    for(int i=1; i<itemList.size(); ++i){
                        sb.append(itemList.get(i).getItemTitle());
                        if(i != itemList.size()-1) {
                            sb.append("/");
                        }
                    }
                    title = sb.toString();

                    sb.setLength(0);

                        for(int i=1; i<itemList.size(); ++i){
                        if(answerMap.get(itemList.get(i).getItemNum()) == null){
                            sb.append("없음");
                        }
                        else {
                            sb.append(answerMap.get(itemList.get(i).getItemNum()));
                        }
                        if(i != itemList.size()-1) {
                            sb.append("/");
                        }
                    }
                    answer = sb.toString();

                    Log.i("title", title);
                    Log.i("answer",answer);
                    FormRegisterTask formRegisterTask = new FormRegisterTask();


                   formRegisterTask.execute("http://192.168.30.21:8089/m.orderResponseInsert");


                }
            });
            listView.addFooterView(btn);
            //FormRegisterTask formRegisterTask = new FormRegisterTask();
            //formRegisterTask.execute("http://192.168.30.21:8089/m.orderResponseInsert");
        }
    }

    class FormRegisterTask extends  AsyncTask<String, Void, String>{
        String response;
        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(20000);
                con.setUseCaches(false); // 캐시 사용 안 함
                con.setRequestMethod("POST");
                con.setDoInput(true);//데이터 받기
                con.setDoOutput(true);//데이터 받고 주는 양방향 통신
                con.setRequestProperty("Connection", "Keep-Alive");
                con.setRequestProperty("content-type", "application/x-www-form-urlencoded");

//                int responseCode = con.getResponseCode();

                //(@RequestParam("itemInsert") String res, @RequestParam("titleInsert") String title,
                //         @RequestParam("peedId") String peedId, @RequestParam("formCode") String formCode, @RequestParam("ordererId")String ordererId)

                String peedId = "id1";//폼 작성자의 아이디
                String formCode = "form-11";//해당 폼 아이디
                String ordererId = "id3";//주문서 작성자 아이디

                StringBuffer sb = new StringBuffer();
                sb.append("itemInsert").append("=").append(answer).append("&");
                sb.append("titleInsert").append("=").append(title).append("&");   // php 변수 앞에 '$' 붙이지 않는다
                sb.append("peedId").append("=").append(peedId).append("&");           // 변수 구분은 '&' 사용
                sb.append("formCode").append("=").append(formCode).append("&");
                sb.append("ordererId").append("=").append(ordererId);

                Log.i("url string buffer: ", sb.toString());

                OutputStream os = con.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송
                bw.write(sb.toString());
                bw.flush();
                bw.close();
                os.close();

                Log.i("전송"," " + con.getResponseCode());
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // 연결에 성공한 경우
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); // 서버의 응답을 읽기 위한 입력 스트림

                    while ((line = br.readLine()) != null) // 서버의 응답을 읽어옴
                        response += line;
                }

  //              Log.i("url connection", " " + responseCode);

/*                if (responseCode == HttpURLConnection.HTTP_OK) { // 200


                    OutputStreamWriter outStream = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                    PrintWriter writer = new PrintWriter(outStream);
                    writer.write(sb.toString());
                    writer.flush();
                } else {
                    sb.append("Connection failed");
                }*/

                con.disconnect();
                return sb.toString();

            }catch (MalformedURLException me) {
                me.printStackTrace();
                return me.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i("on post register","전송 결과 : "+response);
        }
    }

}
