package com.a044.a051.homework.gymclub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private List<card> mCardList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView cardName;
        TextView cardName1;

        public ViewHolder(View view) {
            super(view);
            cardImage=(ImageView) view.findViewById(R.id.iv);
            cardName=(TextView) view.findViewById(R.id.tv1);
            cardName1=(TextView) view.findViewById(R.id.tv2);

        }
    }


    public CardAdapter(List<card> fruitList) {
        mCardList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        card thrcard=mCardList.get(position);
        holder.cardImage.setImageResource(thrcard.getImageid());
        holder.cardName.setText(thrcard.getName());
        holder.cardName1.setText(thrcard.getNames());

    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }
}
