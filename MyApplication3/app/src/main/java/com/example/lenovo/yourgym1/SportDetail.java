package com.example.lenovo.yourgym1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SportDetail extends AppCompatActivity {

    private String[] titles={"腹肌撕裂者","徒手胸肌训练","徒手胸肌进阶","背部塑形",
                            "跑前热身","跑后拉伸","小腿按摩","跑步核心练习",
                            "跑步机", "智能手环","心率监测","体脂秤",
                            "瑜伽入门训练","阿斯汤迪迦","瑜伽-基础力量", "晨间唤醒",
                            "活力燃脂走","急速燃脂走","挺胸抬头走","随便走一走",
                            "公路骑行","环山骑行","青藏骑行","上天骑行",
                            "自由泳","蛙泳","蝶泳","狗刨"};

    private int[] images={R.drawable.sport_fit1,R.drawable.sport_fit2,R.drawable.sport_fit3,R.drawable.sport_fit4,
            R.drawable.sport_run1,R.drawable.sport_run2,R.drawable.sport_run3,R.drawable.sport_run4,
            R.drawable.sport_kit1,R.drawable.sport_kit2,R.drawable.sport_kit3,R.drawable.sport_kit4,
            R.drawable.sport_yoga1,R.drawable.sport_yoga2,R.drawable.sport_yoga3,R.drawable.sport_yoga4,
            R.drawable.sport_walk1,R.drawable.sport_walk2,R.drawable.sport_walk3,R.drawable.sport_walk4,
            R.drawable.sport_cycle1,R.drawable.sport_cycle2,R.drawable.sport_cycle3,R.drawable.sport_cycle4,
            R.drawable.sport_swim1,R.drawable.sport_swim2,R.drawable.sport_swim3,R.drawable.sport_swim4};

    private String[] details={"腹肌撕裂者","徒手胸肌训练","徒手胸肌进阶","背部塑形",
            "跑前热身","跑后拉伸","小腿按摩","跑步核心练习",
            "跑步机", "智能手环","心率监测","体脂秤",
            "瑜伽入门训练","阿斯汤迪迦","瑜伽-基础力量", "晨间唤醒",
            "活力燃脂走","急速燃脂走","挺胸抬头走","随便走一走",
            "公路骑行","环山骑行","青藏骑行","上天骑行",
            "自由泳","蛙泳","蝶泳","狗刨"};

    TextView title;
    ImageView image;
    TextView detail;
    Button button;
    String sport_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView)findViewById(R.id.SportTitle);
        image = (ImageView)findViewById(R.id.img);
        detail = (TextView)findViewById(R.id.detail);
        button = (Button)findViewById(R.id.add);

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
        detail.setText(details[index] + " is a hard work! The real strong people can do it! And I believe you are!");

        button.setText("加入课程");
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(SportDetail.this,"添加成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
