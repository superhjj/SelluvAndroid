package com.company.selluv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.company.selluv.R;
import com.company.selluv.model.OrderMemberViewVO;
import com.company.selluv.service.ImageTask;

import java.util.List;

public class SellResponseListAdapter extends BaseAdapter{

    private Context context;
    private List<OrderMemberViewVO> orderMemberList;
    private LayoutInflater layoutInflater;
    private ImageTask imageTask=null;
    public SellResponseListAdapter(){

    }

    public SellResponseListAdapter(Context context, List<OrderMemberViewVO> orderMemberList){
        this.orderMemberList = orderMemberList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return orderMemberList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderMemberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView orderProfile;
        TextView orderId;
        TextView orderTime;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemLayout = view;
        ViewHolder viewHolder = null;
        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.sellresponselistlayout, null);
            viewHolder = new ViewHolder();
            viewHolder.orderId = (TextView) itemLayout.findViewById(R.id.ordererId);
            viewHolder.orderTime = (TextView) itemLayout.findViewById(R.id.orderTime);
            viewHolder.orderProfile = (ImageView)itemLayout.findViewById(R.id.ordererProfile);
        }
        else {
            viewHolder = (ViewHolder)itemLayout.getTag();
        }
        viewHolder.orderId.setText(orderMemberList.get(position).getMember_id());
        viewHolder.orderTime.setText(orderMemberList.get(position).getOrder_date());
        viewHolder.orderProfile.setTag(orderMemberList.get(position).getProfile_img());

        imageTask=new ImageTask();
        imageTask.execute(viewHolder.orderProfile);

/*
        Glide.with(context)
                .load("http://192.168.30.33:8089"+viewHolder.orderProfile.getTag())
                .apply(new RequestOptions()
                        .circleCrop()
                ).into(viewHolder.orderProfile);

*/

        return itemLayout;
    }
}
