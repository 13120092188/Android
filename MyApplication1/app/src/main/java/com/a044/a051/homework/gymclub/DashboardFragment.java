package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.a044.a051.homework.gymclub.httprequest.LoginRequest;
import com.a044.a051.homework.gymclub.httprequest.LoginResult;
import com.a044.a051.homework.gymclub.httprequest.RegisterRequest;
import com.a044.a051.homework.gymclub.httprequest.RegisterResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart() {
        super.onStart();
        GridView gridview = (GridView) getActivity().findViewById(R.id.GridView);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

        Object[] imageIDs = {R.drawable.sports1,R.drawable.sports2,R.drawable.sports3,R.drawable.sports4,R.drawable.sports5,R.drawable.sports6,R.drawable.sports7,R.drawable.sports8,R.drawable.sports9};
        String[] texts = {"Badminton","Basketball","Tennis","Boxing","Soccer","Ping-pong","Volleyball","Shot","Baseball"};
        //在这里添加图片与文字注释
        for(int i = 0;i < 9;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageIDs[i]);
            map.put("ItemText", texts[i]);
            meumList.add(map);
        }
        SimpleAdapter saMenuItem = new SimpleAdapter(getActivity(),
                meumList,
                R.layout.meunitem,
                new String[]{"ItemImage","ItemText"},
                new int[]{R.id.ItemImage,R.id.ItemText});


        gridview.setAdapter(saMenuItem);

        register("admin","123456");
        login("admin","123456");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    public void login(String username,String password) {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.177:8080/GymServer/android/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        LoginRequest request = retrofit.create(LoginRequest.class);
        //对 发送请求 进行封装
        Call<LoginResult> call = request.login(username,password);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<LoginResult>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                // 步骤7：处理返回的数据结果
                int loginStatus = response.body().getResult();
                TreeMap<String,String> courses = response.body().getCourses();
                //1为成功 2是没有该用户 3是账号密码错误
                switch(loginStatus) {
                    case 1:
                        Toast.makeText(getActivity(),"Login successfully!",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"The user does not exist!",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"The account or password is wrong!",Toast.LENGTH_SHORT).show();
                        break;
                }
                for(String coursename:courses.keySet()){
                    System.out.println(coursename+" "+courses.get(coursename));
                }
                //key是课程名 value是教练名
                System.out.println(response.body().getResult());

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<LoginResult> call, Throwable throwable) {
                System.out.println("连接失败");
                throwable.printStackTrace();
            }
        });
    }

    public void register(String username,String password) {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.177:8080/GymServer/android/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        RegisterRequest request = retrofit.create(RegisterRequest.class);
        //对 发送请求 进行封装
        Call<RegisterResult> call = request.register(username,password);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<RegisterResult>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                // 步骤7：处理返回的数据结果
                int registerStatus = response.body().getResult();
                //1是成功 2是用户名被占用
                switch(registerStatus) {
                    case 1:
                        Toast.makeText(getActivity(),"Register successfully!",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"The user has been used!",Toast.LENGTH_SHORT).show();
                        break;
                }
                System.out.println(registerStatus);
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<RegisterResult> call, Throwable throwable) {
                System.out.println("连接失败");
                throwable.printStackTrace();
            }
        });
    }
}
