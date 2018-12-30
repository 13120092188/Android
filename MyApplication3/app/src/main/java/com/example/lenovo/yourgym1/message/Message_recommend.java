package com.example.lenovo.yourgym1.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.yourgym1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message_recommend extends Fragment {

    private String[] Title={"腹肌撕裂者","徒手胸肌训练","徒手胸肌进阶","背部塑形"};

    private int[] Images={R.drawable.me,R.drawable.message,R.drawable.sport,R.drawable.shop};

    private int[] Content={R.drawable.shop1, R.drawable.shop2, R.drawable.shop3, R.drawable.shop4};

    //将数据封装成数据源
    List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_view, container,false);
        //将数据封装成数据源
        for(int i=0;i<Title.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("title",Title[i]);
            map.put("img",Images[i]);
            map.put("content",Content[i]);
            list.add(map);
        }
        ListView listview=(ListView) view.findViewById(R.id.listView);
        listview.setAdapter(new MyAdapter());

        return view;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder mHolder;
            if(convertView==null){
                view = LayoutInflater.from(getActivity()).inflate(R.layout.message_list_item, null);
                mHolder=new ViewHolder();
                mHolder.card_title=(TextView)view.findViewById(R.id.cardTitle);
                mHolder.card_image=(ImageView)view.findViewById(R.id.cardImg);
                mHolder.card_content=(ImageView) view.findViewById(R.id.cardContent);
                view.setTag(mHolder);  //将ViewHolder存储在View中
            }else {
                view=convertView;
                mHolder=(ViewHolder)view.getTag();  //重新获得ViewHolder
            }
            mHolder.card_title.setText(list.get(position).get("title").toString());
            mHolder.card_image.setImageResource((int)list.get(position).get("img"));
            mHolder.card_content.setImageResource((int)list.get(position).get("content"));
            return view;
        }
    }

    class ViewHolder{
        TextView card_title;
        ImageView card_image;
        ImageView card_content;
    }


}
