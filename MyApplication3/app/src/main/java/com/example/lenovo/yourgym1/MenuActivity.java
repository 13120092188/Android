package com.example.lenovo.yourgym1;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.yourgym1.me.MeFragment;
import com.example.lenovo.yourgym1.message.MessageFragment;
import com.example.lenovo.yourgym1.shop.ShopFragment;
import com.example.lenovo.yourgym1.sport.SportFragment;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    private SportFragment sportFragment;
    private MessageFragment messageFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;

    private View sportLayout;
    private View messageLayout;
    private View shopLayout;
    private View meLayout;

    private ImageView sportImage;
    private ImageView messageImage;
    private ImageView shopImage;
    private ImageView meImage;

    private TextView sportText;
    private TextView messageText;
    private TextView shopText;
    private TextView meText;

    private FragmentManager fragmentManager;

    public get_index index1;//用于获取屏幕宽高的类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置视图
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //初始化界面
        initViews();
        //获取fragmentManager
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);

        //获取屏幕的宽高，并传递到get_index类中
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();
        //传递宽和高给get_index方法
        index1 = new get_index();
        index1.setWidth1(width1);
        index1.setHeight1(height1);

    }

    private void initViews(){
        sportLayout = findViewById(R.id.sport_layout);
        messageLayout = findViewById(R.id.message_layout);
        shopLayout = findViewById(R.id.shop_layout);
        meLayout = findViewById(R.id.me_layout);

        sportImage = (ImageView) findViewById(R.id.sport_image);
        messageImage = (ImageView) findViewById(R.id.message_image);
        shopImage = (ImageView) findViewById(R.id.shop_image);
        meImage = (ImageView) findViewById(R.id.me_image);

        sportText = (TextView) findViewById(R.id.sport_text);
        messageText = (TextView) findViewById(R.id.message_text);
        shopText = (TextView) findViewById(R.id.shop_text);
        meText = (TextView) findViewById(R.id.me_text);

        sportLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        shopLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.sport_layout:
                setTabSelection(0);
                break;
            case R.id.message_layout:
                setTabSelection(1);
                break;
            case R.id.shop_layout:
                setTabSelection(2);
                break;
            case R.id.me_layout:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index){
        //每次选中之前先清除掉上次选中的状态
        clearSelection();
        //获取FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的Fragment,以防止多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch(index){
            case 0:
                //当点击了sport时，改变控件的图片和文字颜色
                sportImage.setImageResource(R.drawable.sport);
                sportText.setTextColor(Color.BLACK);//564b5b
                if(sportFragment == null){
                    //如果SportFragment为空，创建一个并添加到界面上
                    sportFragment = new SportFragment();
                    transaction.add(R.id.content, sportFragment);
                } else{
                    //不为空，直接显示出来
                    transaction.show(sportFragment);
                }
                break;

            case 1:
                //当点击了时，改变控件的图片和文字颜色
                messageImage.setImageResource(R.drawable.message);
                messageText.setTextColor(Color.BLACK);
                if(messageFragment == null){
                    //如果SportFragment为空，创建一个并添加到界面上
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content, messageFragment);
                } else{
                    //不为空，直接显示出来
                    transaction.show(messageFragment);
                }
                break;//不加break为什么会有错误？？？

            case 2:
                //当点击了时，改变控件的图片和文字颜色
                shopImage.setImageResource(R.drawable.shop);
                shopText.setTextColor(Color.BLACK);
                if(shopFragment == null){
                    //如果SportFragment为空，创建一个并添加到界面上
                    shopFragment = new ShopFragment();
                    transaction.add(R.id.content, shopFragment);
                } else{
                    //不为空，直接显示出来
                    transaction.show(shopFragment);
                }
                break;

            case 3:
                //当点击了时，改变控件的图片和文字颜色
                meImage.setImageResource(R.drawable.me);
                meText.setTextColor(Color.BLACK);
                if(meFragment == null){
                    //如果SportFragment为空，创建一个并添加到界面上
                    meFragment = new MeFragment();
                    transaction.add(R.id.content, meFragment);
                } else{
                    //不为空，直接显示出来
                    transaction.show(meFragment);
                }
                break;

            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void clearSelection(){
        sportImage.setImageResource(R.drawable.sport_unselect);
        sportText.setTextColor(Color.parseColor("#82858b"));
        messageImage.setImageResource(R.drawable.message_unselect);
        messageText.setTextColor(Color.parseColor("#82858b"));
        shopImage.setImageResource(R.drawable.shop_unselect);
        shopText.setTextColor(Color.parseColor("#82858b"));
        meImage.setImageResource(R.drawable.me_unselect);
        meText.setTextColor(Color.parseColor("#82858b"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(sportFragment != null){
            transaction.hide(sportFragment);
        }

        if(messageFragment != null){
            transaction.hide(messageFragment);
        }

        if(shopFragment != null){
            transaction.hide(shopFragment);
        }

        if(meFragment != null){
            transaction.hide(meFragment);
        }

    }

}

