package com.example.lenovo.yourgym1.message;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.yourgym1.LoginActivity;
import com.example.lenovo.yourgym1.R;
import com.example.lenovo.yourgym1.get_index;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private View view_message;
    private RadioGroup rg_message;
    private ViewPager vp_message;
    private HorizontalScrollView hv_message;
    private List<Fragment> newsList_message = new ArrayList<Fragment>();
    private MessageAdapter adapter_message;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view_message == null) {
            //初始化view
            view_message = inflater.inflate(R.layout.message_layout, container,false);
            rg_message = (RadioGroup) view_message.findViewById(R.id.message_rg);
            vp_message = (ViewPager) view_message.findViewById(R.id.message_view);
            hv_message = (HorizontalScrollView) view_message.findViewById(R.id.message_hv);
            //设置RadioGroup点击事件
            rg_message.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int id) {
                    vp_message.setCurrentItem(id);//？？？？不懂
                }
            });
            //初始化顶部导航栏
            initTab(inflater);
            //初始化viewpager
            initView();
        }
        /*
         * 底部导航栏切换后 由于没有销毁顶部设置导致如果没有重新设置view
         * 导致底部切换后切回顶部页面数据会消失等bug
         * 以下设置每次重新创建view即可
         */
        ViewGroup parent = (ViewGroup) view_message.getParent();
        if (parent != null) {
            parent.removeView(view_message);
        }
        return view_message;
    }
    /***
     * 初始化viewpager
     */
    private void initView() {
       // List<HTab> hTabs = HTabDb.getSelected();

        Message_recommend fm1 = new Message_recommend();
        newsList_message.add(fm1);//将子fragment存入fragment数组中

        Message_news fm2 = new Message_news();
        newsList_message.add(fm2);//将子fragment存入fragment数组中


        //设置viewpager适配器
        adapter_message = new MessageAdapter(getActivity().getSupportFragmentManager(),newsList_message);//不懂？？？？
        vp_message.setAdapter(adapter_message);
        //两个viewpager切换不重新加载
        vp_message.setOffscreenPageLimit(2);
        //设置默认
        vp_message.setCurrentItem(0);
        //设置viewpager监听事件
        vp_message.setOnPageChangeListener(this);
    }
    /***
     * 初始化头部导航栏
     * @param inflater
     */
    private void initTab(LayoutInflater inflater) {

        get_index index = new get_index();
        int width = index.getWidth1();


        //初始化头部布局1
        RadioButton rbButton1  = ((RadioButton) inflater.inflate(R.layout.message_rb, null));
        rbButton1.setId(0);
        rbButton1.setText("推荐");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(width/2,
        RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_message.addView(rbButton1,params);

        //初始化头部布局2
        RadioButton rbButton2  = ((RadioButton) inflater.inflate(R.layout.message_rb, null));
        rbButton2.setId((int)1);
        rbButton2.setText("私信");
        RadioGroup.LayoutParams params2 = new RadioGroup.LayoutParams(width/2,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_message.addView(rbButton2,params2);

        //默认点击
        rg_message.check((int)0);
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }
    @Override
    public void onPageSelected(int id) {
        setTab(id);
    }
    /***
     * 页面跳转切换头部偏移设置
     * @param id
     */
    private void setTab(int id) {
        RadioButton rbButton = (RadioButton) rg_message.getChildAt(id);
        //设置标题被点击
        rbButton.setChecked(true);
        //偏移设置
        int left = rbButton.getLeft();
        int width = rbButton.getMeasuredWidth()*2;
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        //移动距离= 左边的位置 + button宽度的一半 - 屏幕宽度的一半
        int len = left + width / 2 - screenWidth / 2;
        //移动
        hv_message.smoothScrollTo(len, 0);
    }
}
