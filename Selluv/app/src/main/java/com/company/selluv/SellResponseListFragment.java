package com.company.selluv;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class SellResponseListFragment extends Fragment {
    private ListView listView;
    private  ArrayList<OrderMemberViewVO> orderMembers;
    private String formCode;
    private String memberId;
    public SellResponseListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sellresponsedetail_fragment, container, false);

        orderMembers = (ArrayList<OrderMemberViewVO>) getArguments().getSerializable("orderMembers");
        Log.d("orderMembers",orderMembers.toString());
        SellResponseListAdapter adapter = new SellResponseListAdapter(getActivity().getApplicationContext(), orderMembers);

        listView = (ListView)view.findViewById(R.id.lvResponseDetail);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("on item click", "clicked"+position);
                String formCode = orderMembers.get(position).getForm_code();
                String memberId = orderMembers.get(position).getMember_id();
                transform(formCode, memberId);

            }
        });

        return view;
    }

    public void transform(String formCode, String memberId){
        this.formCode = formCode;
        this.memberId = memberId;
        Intent intent = new Intent();
        intent.putExtra("memberId", memberId);
        intent.putExtra("formCode", formCode);
        intent.setClass(getActivity().getApplicationContext(), SellResponseDetailActivity.class);
        startActivity(intent);
    }
}
