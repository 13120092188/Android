package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener,DashboardFragment.OnFragmentInteractionListener,MeFragment.OnFragmentInteractionListener,BookingFragment.OnFragmentInteractionListener{




    LinearLayout fragmentContainer;
    NewsFragment newsFragment;
    DashboardFragment dashboardFragment;
    MeFragment meFragment;
    BookingFragment bookingFragment;
    String data;
    String[] tranier,sports;

    public static void actionStart(Context context,String username) {
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("param",username);
        context.startActivity(intent);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    newsFragment = NewsFragment.newInstance("param1","param2");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newsFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    dashboardFragment = DashboardFragment.newInstance("param1","param2");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,dashboardFragment).commit();
                    return true;
                case R.id.navigation_booking:
                    bookingFragment = BookingFragment.newInstance("param1","param2");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,bookingFragment).commit();
                    return true;
                case R.id.navigation_me:
                    meFragment = MeFragment.newInstance("param1","param2");
                    Bundle bundle = new Bundle();
                    bundle.putString("username",data);
                    bundle.putStringArray("tranier",tranier);
                    bundle.putStringArray("sports",sports);
                    meFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,meFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        data=intent.getStringExtra("data");
        tranier=intent.getStringArrayExtra("data1");
        sports=intent.getStringArrayExtra("data2");
        //Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();

        fragmentContainer = (LinearLayout) findViewById(R.id.fragment_container);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        newsFragment = NewsFragment.newInstance("param1","param2");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newsFragment).commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
