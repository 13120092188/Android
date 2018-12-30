package com.example.lenovo.yourgym1.shop;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.yourgym1.LoginActivity;
import com.example.lenovo.yourgym1.R;
import com.example.lenovo.yourgym1.RegisterActivity;

public class shop_detail extends AppCompatActivity {

    private String[] titles={"瑜伽教练","美食博主","肌肉男神","氧气美女"};

    private int[] images={R.drawable.sport_yoga1,R.drawable.sport_fit1,R.drawable.sport_fit3,R.drawable.sport_swim2};

    private String[] details={"18823234524","15834569874","18295866325","15868955280"};


    TextView title;
    ImageView image;
    TextView mContact;
    String sport_name;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        title = (TextView)findViewById(R.id.Title);
        image = (ImageView)findViewById(R.id.img);
        mContact = (TextView)findViewById(R.id.contact);
        mContact.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线

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
        mContact.setText("联系方式："+details[index]);

        phone = details[index];


        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用拨打电话的接口
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
