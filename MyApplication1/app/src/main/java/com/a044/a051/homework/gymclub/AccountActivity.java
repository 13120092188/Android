package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,RegisterFragment.OnFragmentInteractionListener{
    RegisterFragment registerFragment;
    LoginFragment loginFragment;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent=getIntent();
        String order=intent.getStringExtra("extra_data");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView=(TextView) findViewById(R.id.textview_re);
        if(order.compareTo("register")==0) {
            loginFragment=null;
            registerFragment = RegisterFragment.newInstance("param1","param2");
            getSupportFragmentManager().beginTransaction().add(R.id.account_container,registerFragment).commit();
            textView.setText("Convert to "+"\n"+"Login");
        } else {
            registerFragment = null;
            loginFragment = LoginFragment.newInstance("param1","param2");
            getSupportFragmentManager().beginTransaction().add(R.id.account_container,loginFragment).commit();
            textView.setText("Convert to "+"\n"+"Register");
        }

        ImageButton convert=(ImageButton) findViewById(R.id.accountConvert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Convert();
            }
        });
    }

    void Convert(){
        if(registerFragment == null){
            registerFragment = RegisterFragment.newInstance("param1","param2");
            getSupportFragmentManager().beginTransaction().replace(R.id.account_container, registerFragment).commit();
            loginFragment = null;
            textView.setText("Convert to "+"\n"+"Login");
        }
        else if(loginFragment == null){
            loginFragment = LoginFragment.newInstance("param1","param2");
            getSupportFragmentManager().beginTransaction().replace(R.id.account_container, loginFragment).commit();
            registerFragment= null;
            textView.setText("Convert to "+"\n"+"Register");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
