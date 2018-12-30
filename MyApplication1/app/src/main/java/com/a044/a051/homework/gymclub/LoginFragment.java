package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a044.a051.homework.gymclub.httprequest.LoginRequest;
import com.a044.a051.homework.gymclub.httprequest.LoginResult;

import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final Button login=(Button) getActivity().findViewById(R.id.loginfra);
        final EditText username=(EditText) getActivity().findViewById(R.id.username_login);
        final EditText password=(EditText) getActivity().findViewById(R.id.password_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logging(username.getText().toString(),password.getText().toString());
//                MainActivity.actionStart(getActivity(),username.getText().toString());
            }
        });

    }

    public void logging(final String username, String password) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.172:8080/GymServer/android/") // 设置 网络请求 Url
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
                String [] trainer=new String[courses.size()];
                String [] sports=new String[courses.size()];
                int number=0;
                //把map的值赋到string数组中
                for (Map.Entry<String, String> entry : courses.entrySet()) {
                    trainer[number]=entry.getKey();
                    sports[number]=entry.getValue();
                    number++;
                }
                //1为成功 2是没有该用户 3是账号密码错误
                switch(loginStatus) {
                    case 1:
                        Toast.makeText(getActivity(),"Login successfully!",Toast.LENGTH_SHORT).show();
                        //handle 延时函数
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("data",username);
                        intent.putExtra("data1",trainer);
                        intent.putExtra("data2",sports);
                        startActivity(intent);
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
}
