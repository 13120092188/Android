package Tiny.capsule.location;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import Tiny.capsule.CapsuleSendActivity;
import Tiny.capsule.R;

public class LocationActivity extends AppCompatActivity {

    private MapView mMapView = null;            //百度地图缩略
    BaiduMap mBaiduMap;                         //定义地图实例
    boolean ifFrist = true;                     //判断是否是第一次进去
    private ImageView back;                     //返回上一层
    private ImageView confirm;                  //确认地点位置
    private SearchView searchView;              //搜索地点框
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    LocationClientOption option = new LocationClientOption();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);

        mMapView = findViewById(R.id.bmapView);
        back = findViewById(R.id.toolbar5_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm = findViewById(R.id.toolbar5_set);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchView = findViewById(R.id.searchView5);

        mBaiduMap = mMapView.getMap();//获取地图实例对象
        mBaiduMap.setMyLocationEnabled(true);
        //设置定位图标是否有箭头
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true,null));

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        option.setIsNeedAddress(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        //调用LocationClient的start()方法，便可发起定位请求
        mLocationClient.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息

            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            Log.i("---------", location.getCityCode() + "---" + latitude + "--" + longitude + "----" + coorType + "--" + location.getCountry() + "--" + location.getCity() + "--" + location.getAddrStr());
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

          /*  // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);*/

            if (ifFrist) {
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
                // 移动到某经纬度
                mBaiduMap.animateMapStatus(update);
                update = MapStatusUpdateFactory.zoomBy(5f);
                // 放大
                mBaiduMap.animateMapStatus(update);

                ifFrist = false;
            }

            // 显示个人位置图标
            MyLocationData.Builder builder = new MyLocationData.Builder();
            builder.latitude(location.getLatitude());
            builder.longitude(location.getLongitude());
            MyLocationData data = builder.build();
            mBaiduMap.setMyLocationData(data);
        }
    }
}

