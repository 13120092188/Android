package com.example.lenovo.yourgym1.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.example.lenovo.yourgym1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class item_one extends AppCompatActivity {

    private String[] Title={"腹肌撕裂者","徒手胸肌训练","俯卧撑进阶","背部塑形", "俯卧撑入门", "定向越野", "徒手体能进阶", "平板支撑"};

    private String[] Time={"15分钟","13分钟","20分钟","8分钟","18分钟", "23分钟", "24分钟", "15分钟"};

    private String[] Date={"2018.06.30","2018.09.06","2017.05.04","2018.10.15", "2017.06.04","2017.07.09","2018.11.13","2018.04.08"};

    //将数据封装成数据源
    List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

    private RecyclerView mRecyclerView;
    private item1_RecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_item_one);

        initData();
        initView();

    }
     private void initData(){
         //将数据封装成数据源
         for(int i=0;i<Title.length;i++){
             Map<String,Object> map=new HashMap<String, Object>();
             map.put("title",Title[i]);
             map.put("img",Time[i]);
             map.put("content",Date[i]);
             list.add(map);
         }
     }

     private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置adapter
         mAdapter = new item1_RecycleAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

         mAdapter.setOnItemClickListener(new item1_RecycleAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 //Toast.makeText(item_one.this,"onItemClick",Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(item_one.this, item1_sport_detail.class);
                 startActivity(intent);
             }

//             @Override
//             public void onItemLongClick(View view, int position) {
//                 Toast.makeText(item_one.this,"onItemLongClick",Toast.LENGTH_SHORT).show();
//             }
         });

     }

}