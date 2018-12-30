package com.example.lenovo.yourgym1.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.yourgym1.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> mList;
    private Context context;
    public MyAdapter(List<String> mList,Context context){
        this.mList = mList;
        this.context = context;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_view,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_msg.setText(mList.get(i));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_msg;
        public MyViewHolder(View itemView) {
            super(itemView);
            //tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }
}