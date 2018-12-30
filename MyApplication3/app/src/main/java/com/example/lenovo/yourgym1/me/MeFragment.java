package com.example.lenovo.yourgym1.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.yourgym1.R;
import com.leon.lib.settingview.LSettingItem;

public class MeFragment extends Fragment {

    View meLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        meLayout = inflater.inflate(R.layout.me_layout, container, false);
        clickItem();
        return meLayout;
    }

    public void clickItem(){
        final LSettingItem item_one = (LSettingItem) meLayout.findViewById(R.id.item_one);
        LSettingItem item_two = (LSettingItem) meLayout.findViewById(R.id.item_two);
        LSettingItem item_three = (LSettingItem) meLayout.findViewById(R.id.item_three);
        LSettingItem item_four = (LSettingItem) meLayout.findViewById(R.id.item_four);
        LSettingItem item_five = (LSettingItem) meLayout.findViewById(R.id.item_five);

        item_one.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {

            @Override
            public void click(boolean isChecked) {
                Intent intent1 = new Intent(getActivity(), item_one.class);
                startActivity(intent1);
            }
        });


    }


}
