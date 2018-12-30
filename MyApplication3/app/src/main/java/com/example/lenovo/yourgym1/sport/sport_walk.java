package com.example.lenovo.yourgym1.sport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.yourgym1.R;
import com.example.lenovo.yourgym1.SportDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sport_walk extends Fragment implements AdapterView.OnItemClickListener{

    private String[] Title={"活力燃脂走","急速燃脂走","挺胸抬头走","随便走一走"};

    private int[] Images={R.drawable.sport,R.drawable.sport,R.drawable.sport,R.drawable.sport};

    private int[] Content={R.drawable.sport_walk1,R.drawable.sport_walk2,R.drawable.sport_walk3,R.drawable.sport_walk4};

    //将数据封装成数据源
    List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sport_view, container,false);
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
        listview.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //大多数情况下，position和id相同，并且都从0开始
        String showText = "点击第" + position + "项 ID为：" + id;
        //Toast.makeText(getActivity(), showText, Toast.LENGTH_LONG).show();
        //启动SportDetail.activity
        Intent intent = new Intent(getActivity(), SportDetail.class);
        intent.putExtra("sport_name", Title[position]);
        startActivity(intent);
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.sport_list_item, null);
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
