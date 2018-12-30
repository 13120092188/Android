package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InformationView extends AppCompatActivity {

    private List<card> cardlist=new ArrayList<>();

    String[] tranier,sports;

    public static void actionStart(Context context) {
        Intent intent=new Intent(context,InformationView.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent=getIntent();
        tranier=intent.getStringArrayExtra("data1");
        sports=intent.getStringArrayExtra("data2");
        initCards(tranier,sports);

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter adapter=new CardAdapter(cardlist);
        recyclerView.setAdapter(adapter);
    }

    private void initCards(String[] trainer,String[] sports) {

            for(int i=0;i<trainer.length;i++) {
                card apple=new card(trainer[i],sports[i],R.drawable.sports3);
                cardlist.add(apple);
            }


    }
}
