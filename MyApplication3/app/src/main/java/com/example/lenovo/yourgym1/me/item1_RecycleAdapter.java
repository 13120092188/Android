package com.example.lenovo.yourgym1.me;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lenovo.yourgym1.R;

import java.util.List;
import java.util.Map;

public class item1_RecycleAdapter extends RecyclerView.Adapter<item1_RecycleAdapter.ViewHolder> {

    private List<Map<String,Object>> list;

    //构造器，接受数据集
    public item1_RecycleAdapter(List<Map<String,Object>> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item1_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //将数据填充到具体的view中
        holder.icon.setText(list.get(position).get("img").toString());
        holder.title.setText(list.get(position).get("title").toString());
        holder.desc.setText(list.get(position).get("content").toString());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position1 = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position1);
                }
            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position1 = holder.getLayoutPosition();
//                    mOnItemClickListener.onItemLongClick(holder.itemView, position1);
//                    return false;
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView icon;
        public TextView title;
        public TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            //由于itemView是item的布局文件，我们需要的是里面的textView，因此利用itemView.findViewById获
            //取里面的textView实例，后面通过onBindViewHolder方法能直接填充数据到每一个textView了
            icon = (TextView) itemView.findViewById(R.id.cardImg);
            title = (TextView) itemView.findViewById(R.id.cardTitle);
            desc = (TextView) itemView.findViewById(R.id.cardContent);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
