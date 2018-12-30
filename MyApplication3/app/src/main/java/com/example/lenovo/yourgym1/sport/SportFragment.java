package com.example.lenovo.yourgym1.sport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.lenovo.yourgym1.R;
import com.example.lenovo.yourgym1.get_index;
import java.util.ArrayList;
import java.util.List;

public class SportFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private View view;
    private RadioGroup rg_sport;
    private ViewPager vp_sport;
    private HorizontalScrollView hv_sport;
    private List<Fragment> newsList_sport = new ArrayList<Fragment>();
    private SportAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            //初始化view
            view = inflater.inflate(R.layout.sport_layout, container,false);

            rg_sport = (RadioGroup) view.findViewById(R.id.one_rg);
            vp_sport = (ViewPager) view.findViewById(R.id.one_view);
            hv_sport = (HorizontalScrollView) view.findViewById(R.id.one_hv);
            //设置RadioGroup点击事件
            rg_sport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int id) {
                    vp_sport.setCurrentItem(id);
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
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }
    /***
     * 初始化viewpager
     */
    private void initView() {
        sport_fit fm1 = new sport_fit();
        newsList_sport.add(fm1);//将子fragment存入fragment数组中

        sport_run fm2 = new sport_run();
        newsList_sport.add(fm2);//将子fragment存入fragment数组中

        sport_kit fm3 = new sport_kit();
        newsList_sport.add(fm3);//将子fragment存入fragment数组中

        sport_yoga fm4 = new sport_yoga();
        newsList_sport.add(fm4);//将子fragment存入fragment数组中

        sport_walk fm5 = new sport_walk();
        newsList_sport.add(fm5);//将子fragment存入fragment数组中

        sport_cycle fm6 = new sport_cycle();
        newsList_sport.add(fm6);//将子fragment存入fragment数组中

        sport_swim fm7 = new sport_swim();
        newsList_sport.add(fm7);//将子fragment存入fragment数组中

        //设置viewpager适配器
        adapter = new SportAdapter(getActivity().getSupportFragmentManager(),newsList_sport);//不懂？？？？
        vp_sport.setAdapter(adapter);
        //7个viewpager切换不重新加载
        vp_sport.setOffscreenPageLimit(7);
        //设置默认
        vp_sport.setCurrentItem(0);
        //设置viewpager监听事件
        vp_sport.setOnPageChangeListener(this);
    }
    /***
     * 初始化头部导航栏
     * @param inflater
     */
    private void initTab(LayoutInflater inflater) {
        //得到屏幕的宽度
        get_index index = new get_index();
        int width = index.getWidth1();

        //初始化头部布局1 健身
        RadioButton rbButton1  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton1.setId(0);
        rbButton1.setText("健身");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton1,params);

        //初始化头部布局2 跑步
        RadioButton rbButton2  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton2.setId((int)1);
        rbButton2.setText("跑步");
        RadioGroup.LayoutParams params2 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton2,params2);

        //初始化头部布局3 kit
        RadioButton rbButton3  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton3.setId((int)2);
        rbButton3.setText("KIT");
        RadioGroup.LayoutParams params3 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton3,params3);

        //初始化头部布局4 瑜伽
        RadioButton rbButton4  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton4.setId((int)3);
        rbButton4.setText("瑜伽");
        RadioGroup.LayoutParams params4 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton4,params4);

        //初始化头部布局5 行走
        RadioButton rbButton5  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton5.setId((int)4);
        rbButton5.setText("行走");
        RadioGroup.LayoutParams params5 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton5,params5);

        //初始化头部布局6 骑行
        RadioButton rbButton6  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton6.setId((int)5);
        rbButton6.setText("骑行");
        RadioGroup.LayoutParams params6 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton6,params6);

        //初始化头部布局7 游泳
        RadioButton rbButton7  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton7.setId((int)6);
        rbButton7.setText("游泳");
        RadioGroup.LayoutParams params7 = new RadioGroup.LayoutParams((int)(width/4.5),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_sport.addView(rbButton7,params7);

        //默认点击
        rg_sport.check(0);
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
        RadioButton rbButton = (RadioButton) rg_sport.getChildAt(id);
        //设置标题被点击
        rbButton.setChecked(true);
        //偏移设置
        int left = rbButton.getLeft();
        int width = rbButton.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        //移动距离= 左边的位置 + button宽度的一半 - 屏幕宽度的一半
        int len = left + width / 2 - screenWidth / 2;
        //移动
        hv_sport.smoothScrollTo(len, 0);
    }
}
