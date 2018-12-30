package com.example.lenovo.yourgym1.shop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.yourgym1.R;
import com.example.lenovo.yourgym1.SportDetail;

public class shop_gym_detail extends AppCompatActivity {

    private String[] titles={"望京店","长安街店","交大店","动物园店"};

    private int[] images={R.drawable.shop_gym1,R.drawable.shop_gym2,R.drawable.shop_gym3,R.drawable.shop_gym4};

    TextView title;
    ImageView image;
    TextView mAddress;
    String sport_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_gym_detail);

        title = (TextView)findViewById(R.id.Title);
        image = (ImageView)findViewById(R.id.img);
        mAddress = (TextView)findViewById(R.id.contact);

        //获取传来的运动名
        sport_name = getIntent().getStringExtra("sport_name");

        int index = 0;
        for(int i =0; i<titles.length; i++){
            if(sport_name.equals(titles[i])){
                index = i;
                break;
            }
        }

        title.setText(sport_name);
        image.setImageResource(images[index]);

        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用地图接口
                Toast.makeText(shop_gym_detail.this,"打开地图！",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
