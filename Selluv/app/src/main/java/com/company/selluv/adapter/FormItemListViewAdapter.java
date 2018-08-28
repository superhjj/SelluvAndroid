package com.company.selluv.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.company.selluv.R;
import com.company.selluv.model.ItemVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormItemListViewAdapter extends BaseAdapter {
    //ITEM_VIEW_LIST = {"RE", "SA","SN","SS","MS","TI","AI", "MM"};
    private static final int ITEM_VIEW_TYPE_RE = 0;
    private static final int ITEM_VIEW_TYPE_SA = 1;
    private static final int ITEM_VIEW_TYPE_SN = 2;
    private static final int ITEM_VIEW_TYPE_SS = 3;
    private static final int ITEM_VIEW_TYPE_MS = 4;
    private static final int ITEM_VIEW_TYPE_TI = 5;
    private static final int ITEM_VIEW_TYPE_AI = 6;
    private static final int ITEM_VIEW_TYPE_MM = 7;
   // private static final int ITEM_VIEW_TYPE_END = 8;
    private static final int ITEM_VIEW_TYPE_MAX=8;


    private ArrayList<ItemVO> listViewItemList;
    public HashMap<String, String> answers;

    private Context context = null;
    private LayoutInflater layoutInflater = null;

    public FormItemListViewAdapter(){

    }

    public FormItemListViewAdapter(Context context, ArrayList<ItemVO> listViewItemList, HashMap<String, String> answers){
        this.context = context;
        this.listViewItemList = listViewItemList;
        this.answers = answers;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return  listViewItemList.get(position);
        //return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX ;
    }

    // position 위치의 아이템 타입 리턴.
    @Override
    public int getItemViewType(int position) {
        String strType = listViewItemList.get(position).getItemType() ;
        int intType = 0;
        switch (strType){
            case "re": intType = ITEM_VIEW_TYPE_RE;
                break;
            case "sa": intType = ITEM_VIEW_TYPE_SA;
                break;
            case "ti": intType = ITEM_VIEW_TYPE_TI;
                break;
            case "ms": intType = ITEM_VIEW_TYPE_MS;
                break;
            case "ss": intType = ITEM_VIEW_TYPE_SS;
                break;
            case "sn": intType = ITEM_VIEW_TYPE_SN;
                break;
            case "ai": intType = ITEM_VIEW_TYPE_AI;
                break;
            case "mm": intType = ITEM_VIEW_TYPE_MM;
                break;
/*            case "end": intType = ITEM_VIEW_TYPE_END;
                break;*/
        }
        return intType;
    }



    /*
        class ViewHolder{
        TextView tvDate;
        TextView tvTemp;
        TextView tvDesc;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemLayout = view;
        ViewHolder viewHolder = null;

        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.forecast_row, null);
            viewHolder = new ViewHolder();
            viewHolder.tvDate = (TextView) itemLayout.findViewById(R.id.tvDate);
            viewHolder.tvTemp = (TextView) itemLayout.findViewById(R.id.tvTemp);
            viewHolder.tvDesc = (TextView) itemLayout.findViewById(R.id.tvDesc);
            viewHolder.imageView = (ImageView) itemLayout.findViewById(R.id.imageView);
            itemLayout.setTag(viewHolder);
        }else{
            viewHolder  =(ViewHolder)itemLayout.getTag();
        }

        viewHolder.tvDate.setText(ConvertDate.CONVERT_DATE(weather.foreCastList.get(position).dt, city));
        viewHolder.tvTemp.setText(weather.foreCastList.get(position).main.temp+"\u2103");
        viewHolder.tvDesc.setText(weather.foreCastList.get(position).weather.desription);
        viewHolder.imageView.setTag(weather.foreCastList.get(position).weather.icon);

        //execute thread for getting image
        ImageViewTask task = new ImageViewTask(context);
        task.execute(viewHolder.imageView);
        return itemLayout;
    }
    */


/*    class ViewHolder{
        TextView tvTitle;
        TextView tvContent;
        TextView tvNeed;
        EditText etType;
        EditText etZipCode;
        Button btnAddr;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        int viewType = getItemViewType(position) ;
        //ViewHolder viewHolder = null;
        View itemLayout = null;

        System.out.print(viewType);
        if (convertView == null) {
            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ItemVO listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_SA:
                    itemLayout = layoutInflater.inflate(R.layout.salayout, null);
                    setLayout(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                break;
                case ITEM_VIEW_TYPE_TI:
                    itemLayout = layoutInflater.inflate(R.layout.tllayout, null);
                    setLayout(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_RE:
                    itemLayout = layoutInflater.inflate(R.layout.relayout, null);
                    setLayoutRE(itemLayout, listViewItem);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_MS:
                    itemLayout = layoutInflater.inflate(R.layout.mslayout, null);
                    setLayoutMS(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_SS:
                    itemLayout = layoutInflater.inflate(R.layout.sslayout, null);
                    setLayoutSS(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_SN:
                    itemLayout = layoutInflater.inflate(R.layout.snlayout, null);
                    setLayout(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_AI:
                    itemLayout = layoutInflater.inflate(R.layout.ailayout, null);
                    setLayout(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;
                case ITEM_VIEW_TYPE_MM:
                    itemLayout = layoutInflater.inflate(R.layout.mmlayout, null);
                    setLayout(itemLayout, listViewItem, position);
                    convertView = itemLayout;
                    break;



/*                case ITEM_VIEW_TYPE_END:
                    itemLayout = layoutInflater.inflate(R.layout.endlayout, null);
                    //setLayoutEND(itemLayout, listViewItem);
                    convertView = itemLayout;
                    break;*/

            }
        }

    //    Log.i("getview",answers.toString());

        return convertView;
    }

    public void addItem(ItemVO ItemVO){
        listViewItemList.add(ItemVO);
    }


    public void setLayout(View itemLayout, final ItemVO listViewItem, final int position){
        TextView content = (TextView)itemLayout.findViewById(R.id.tvContent);
        TextView title = (TextView)itemLayout.findViewById(R.id.tvTitle);
        TextView need = (TextView)itemLayout.findViewById(R.id.tvNeed);
        final EditText etType = (EditText)itemLayout.findViewById(R.id.etType);

        title.setText(listViewItem.getItemTitle());
        content.setText(listViewItem.getItemDescript());

        etType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                answers.put(listViewItem.getItemNum(),etType.getText().toString());
                Log.i("aftertextchange map",answers.toString());
            }
        });


        if(listViewItem.getItemNecessry().equals("Y")){
            need.setText("★");
        }
        else{
            need.setHeight(0);
        }
        if(content.getText().equals("") || content.getText() == null){
            content.setHeight(0);
        }

        //etType.setId(R.string.itemID + position);
        if(listViewItem.getItemType().equals("ai")){
            EditText etZipCode = (EditText)itemLayout.findViewById(R.id.etZipCode);
            etZipCode.setId(R.string.zipID+position);
            Button btnAddr = (Button)itemLayout.findViewById(R.id.btnAddr);
            btnAddr.setId(R.string.addrBtnId+position);
        }
    }

    public void setLayoutRE(View itemLayout, ItemVO listViewItem){
        TextView content = (TextView)itemLayout.findViewById(R.id.tvContent);
        TextView title = (TextView)itemLayout.findViewById(R.id.tvTitle);
        TextView period = (TextView)itemLayout.findViewById(R.id.tvPeriod);

        title.setText(listViewItem.getItemTitle());
        content.setText(listViewItem.getItemDescript());

        if(listViewItem.getItemNecessry().equals("Y")){
            period.setText(listViewItem.getOptions());
        }
        else{
            period.setHeight(0);
        }
        if(content.getText().equals("") || content.getText() == null){
            content.setHeight(0);
        }
    }


    public void setLayoutMS(View itemLayout, ItemVO listViewItem, int position){
        TextView content = (TextView)itemLayout.findViewById(R.id.tvContent);
        TextView title = (TextView)itemLayout.findViewById(R.id.tvTitle);
        TextView need = (TextView)itemLayout.findViewById(R.id.tvNeed);

        title.setText(listViewItem.getItemTitle());
        content.setText(listViewItem.getItemDescript());
        if(listViewItem.getItemNecessry().equals("Y")){
            need.setText("★");
        }
        else{
            need.setHeight(0);
        }

        if(content.getText().equals("") || content.getText() == null){
            content.setHeight(0);
        }

        String optionStr = listViewItem.getOptions();
        String[] options = optionStr.split(",");

        View optionLayout = null;
        LinearLayout ll  = (LinearLayout)itemLayout.findViewById(R.id.llMSOptionInput);
        ll.setId(R.id.llMSOptionInput+position);

        for(int i=0;i<options.length;++i){
            CheckBox cbOption = new CheckBox(context);
            cbOption.setText(options[i]);
            //int id = View.generateViewId();

            cbOption.setId(R.string.optionID+position+R.string.dash+i);
            ll.addView(cbOption);
        }



    }

    public void setLayoutSS(View itemLayout, final ItemVO listViewItem, int position){
        TextView content = (TextView)itemLayout.findViewById(R.id.tvContent);
        TextView title = (TextView)itemLayout.findViewById(R.id.tvTitle);
        TextView need = (TextView)itemLayout.findViewById(R.id.tvNeed);

        title.setText(listViewItem.getItemTitle());
        content.setText(listViewItem.getItemDescript());
        if(listViewItem.getItemNecessry().equals("Y")){
            need.setText("★");
        }
        else{
            need.setHeight(0);
        }

        if(content.getText().equals("") || content.getText() == null){
            content.setHeight(0);
        }

        String optionStr = listViewItem.getOptions();
        String[] options = optionStr.split(",");

        View optionLayout = null;
        LinearLayout ll  = (LinearLayout)itemLayout.findViewById(R.id.llSSOptionInput);


        RadioGroup rg = new RadioGroup(context);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.setId(position);

        for(int i=0;i<options.length;++i){
            RadioButton rb = new RadioButton(context);
            rb.setText(options[i]);
            //int id = View.generateViewId();
            rb.setId(R.string.optionID+position+R.string.dash+i);
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radio_btn = (RadioButton) radioGroup.findViewById(i);
                answers.put(listViewItem.getItemNum(), radio_btn.getText().toString());
                Log.i("radio change",answers.toString());
            }
        });

        ll.addView(rg);

    }
}
