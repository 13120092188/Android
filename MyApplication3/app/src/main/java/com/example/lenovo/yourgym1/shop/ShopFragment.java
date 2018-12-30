package com.example.lenovo.yourgym1.shop;

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

public class ShopFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private View view_shop;
    private RadioGroup rg_shop;
    private ViewPager vp_shop;
    private HorizontalScrollView hv_shop;
    private List<Fragment> newsList_shop = new ArrayList<Fragment>();
    private ShopAdapter adapter_shop;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view_shop == null) {
            //初始化view
            view_shop = inflater.inflate(R.layout.shop_layout, container,false);
            rg_shop = (RadioGroup) view_shop.findViewById(R.id.shop_rg);
            vp_shop = (ViewPager) view_shop.findViewById(R.id.shop_view);
            hv_shop = (HorizontalScrollView) view_shop.findViewById(R.id.shop_hv);
            //设置RadioGroup点击事件
            rg_shop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int id) {
                    vp_shop.setCurrentItem(id);//？？？？不懂
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
        ViewGroup parent = (ViewGroup) view_shop.getParent();
        if (parent != null) {
            parent.removeView(view_shop);
        }

        return view_shop;
    }
    /***
     * 初始化viewpager
     */
    private void initView(){
        // List<HTab> hTabs = HTabDb.getSelected();

        Shop_follow fm1 = new Shop_follow();
        newsList_shop.add(fm1);//将子fragment存入fragment数组中

        Shop_diet fm2 = new Shop_diet();
        newsList_shop.add(fm2);//将子fragment存入fragment数组中

        Shop_doyen fm3 = new Shop_doyen();
        newsList_shop.add(fm3);//将子fragment存入fragment数组中

        Shop_equipment fm4 = new Shop_equipment();
        newsList_shop.add(fm4);//将子fragment存入fragment数组中



        //设置viewpager适配器
        adapter_shop = new ShopAdapter(getActivity().getSupportFragmentManager(),newsList_shop);//不懂？？？？
        vp_shop.setAdapter(adapter_shop);
        //两个viewpager切换不重新加载
        vp_shop.setOffscreenPageLimit(4);
        //设置默认
        vp_shop.setCurrentItem(0);
        //设置viewpager监听事件
        vp_shop.setOnPageChangeListener(this);
    }
    /***
     * 初始化头部导航栏
     * @param inflater
     */
    private void initTab(LayoutInflater inflater) {
        //获得屏幕的宽高
        get_index index1 = new get_index();
        int width = index1.getWidth1();


        //初始化头部布局1
        RadioButton rbButton1  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton1.setId(0);
        rbButton1.setText("关注");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams((int)(width/2.65),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_shop.addView(rbButton1,params);

        //初始化头部布局2
        RadioButton rbButton2  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton2.setId((int)1);
        rbButton2.setText("健身房");
        RadioGroup.LayoutParams params2 = new RadioGroup.LayoutParams((int)(width/2.65),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_shop.addView(rbButton2,params2);

        //初始化头部布局3
        RadioButton rbButton3  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton3.setId((int)2);
        rbButton3.setText("达人");
        RadioGroup.LayoutParams params3 = new RadioGroup.LayoutParams((int)(width/2.65),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_shop.addView(rbButton3,params3);

        //初始化头部布局4
        RadioButton rbButton4  = (RadioButton) inflater.inflate(R.layout.shop_rb, null);
        rbButton4.setId((int)3);
        rbButton4.setText("器材");
        RadioGroup.LayoutParams params4 = new RadioGroup.LayoutParams((int)(width/2.65),
                RadioGroup.LayoutParams.WRAP_CONTENT);
        //加入RadioGroup
        rg_shop.addView(rbButton4,params4);

        //默认点击
        rg_shop.check(0);
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
        RadioButton rbButton = (RadioButton) rg_shop.getChildAt(id);
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
        hv_shop.smoothScrollTo(len, 0);
    }
}

