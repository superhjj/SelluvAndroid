package com.company.selluv.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.company.selluv.SellFormFragment;
import com.company.selluv.SellResponseDetailFragment;
import com.company.selluv.SellResponseListFragment;
import com.company.selluv.model.ItemVO;
import com.company.selluv.model.OrderMemberViewVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SellResponsePagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;
    private ArrayList<OrderMemberViewVO> orderMembers;
    private ArrayList<ItemVO> items;
    private SellResponseListFragment sellResponseListFragment;
    private SellFormFragment sellFormFragment;

    //(getSupportFragmentManager(), tabLayout.getTabCount(), orderMemberList, itemList);
    public SellResponsePagerAdapter(FragmentManager fm, int tabCount, ArrayList<OrderMemberViewVO> orderMembers, ArrayList<ItemVO> items) {
        super(fm);
        this.tabCount = tabCount;
        this.items = items;
        this.orderMembers = orderMembers;

        sellResponseListFragment = new SellResponseListFragment();
        try {

            Log.e("orderMembers",orderMembers.toString());
            Bundle sellBundle = new Bundle(1);
            sellBundle.putSerializable("orderMembers", orderMembers);
            sellResponseListFragment.setArguments(sellBundle);
        }
        catch (Exception e){
            e.printStackTrace();
        }

       sellFormFragment = new SellFormFragment();
        try{
            Log.e("items",items.toString());
            Bundle formBundle = new Bundle(1);
            formBundle.putSerializable("items", items);
            sellFormFragment.setArguments(formBundle);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return sellResponseListFragment;
            case 1:
                return sellFormFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
