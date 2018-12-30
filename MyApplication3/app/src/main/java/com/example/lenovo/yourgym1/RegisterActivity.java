package com.example.lenovo.yourgym1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mPassword_repeat;
    private Button mRegisterButton;
    private TextView mBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterButton = findViewById(R.id.register_button);
        mUsername = findViewById(R.id.username_register);
        mPassword = findViewById(R.id.password_register);
        mPassword_repeat = findViewById(R.id.password_register1);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码，判断格式是否正确
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String password1 = mPassword_repeat.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()|| password1.isEmpty()) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else if (password.length() < 6 || password1.length() < 6) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "密码不能少于六位数", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else if (!password.equals(password1)) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                }else{
                    //设置提醒弹窗
                    Toast toast = Toast.makeText(RegisterActivity.this,"注册成功！欢迎加入MOVING!",Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, -50);
//                TextView view1 = (TextView) toast.getView().findViewById(android.R.id.toast_message); //toast显示的文本内容
//                view1.setTextColor(Color.GREEN);   //设置toast的字体颜色
//                view1.setTextSize(20);
                    toast.show();
                }

            }
        });

        //注册完成，返回登录界面
        mBackLogin = findViewById(R.id.back_login);
        mBackLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线

        mBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });


    }
}
