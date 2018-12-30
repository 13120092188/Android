package com.example.lenovo.yourgym1;

import java.util.ArrayList;
import java.util.List;

public class HTabDb {
    private static final List<HTab> Selected = new ArrayList<HTab>();
    static{
        Selected.add(new HTab("健身"));
        Selected.add(new HTab("跑步"));
        Selected.add(new HTab("KIT"));
        Selected.add(new HTab("瑜伽"));
        Selected.add(new HTab("行走"));
        Selected.add(new HTab("骑行"));
        Selected.add(new HTab("游泳"));
        Selected.add(new HTab("翻跟头"));
    }
    /***
     * 获得头部tab的所有项
     */
    public static List<HTab> getSelected() {
        return Selected;
    }

}

