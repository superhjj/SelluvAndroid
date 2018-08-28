package com.company.selluv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.selluv.R;
import com.company.selluv.model.OrderDetailViewVO;
import com.company.selluv.model.OrderMemberDetailVO;
import com.company.selluv.service.ImageTask;

import java.util.ArrayList;
import java.util.List;

public class SellResponseDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String[]> orderDetails;
    private LayoutInflater layoutInflater;

    public SellResponseDetailAdapter(){}

    public SellResponseDetailAdapter(Context context, ArrayList<String[]> orderDetails){
        this.orderDetails = orderDetails;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView tvItemTitle;
        TextView tvItemResponse;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemLayout = view;
        ViewHolder viewHolder = null;
        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.orderdetaillayout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvItemTitle = (TextView) itemLayout.findViewById(R.id.tvItemTitle);
            viewHolder.tvItemResponse = (TextView) itemLayout.findViewById(R.id.tvItemResponse);
        }
        else {
            viewHolder = (ViewHolder)itemLayout.getTag();
        }

        viewHolder.tvItemTitle.setText(orderDetails.get(position)[0]);
        viewHolder.tvItemResponse.setText(orderDetails.get(position)[1]);

        return itemLayout;
    }
}
