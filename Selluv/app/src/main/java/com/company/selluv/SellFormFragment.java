package com.company.selluv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.company.selluv.adapter.FormItemListViewAdapter;
import com.company.selluv.model.ItemVO;
import com.company.selluv.model.OrderMemberViewVO;

import java.util.ArrayList;
import java.util.HashMap;

public class SellFormFragment extends Fragment {
    private ListView listView;
    private ArrayList<ItemVO> items;
    private HashMap<String, String> answers;

    public SellFormFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_fragment, container, false);

        items = (ArrayList<ItemVO>) getArguments().getSerializable("items");
        Log.d("items",items.toString());

        FormItemListViewAdapter adapter = new FormItemListViewAdapter(getActivity().getApplicationContext(), items, answers);

        listView = (ListView)view.findViewById(R.id.orderdetailList);
        listView.setAdapter(adapter);


        return view;
    }
}
