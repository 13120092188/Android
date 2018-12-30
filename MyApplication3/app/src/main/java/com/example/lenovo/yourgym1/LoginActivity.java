package com.example.lenovo.yourgym1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//qq登录
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private TextView mRegisterTV;
    private EditText mUsername;
    private EditText mPassword;
    private ImageView mQQLogin;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        //getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);//创建视图
        setContentView(R.layout.activity_login);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mUsername = findViewById(R.id.username_login);
        mPassword = findViewById(R.id.password_login);
        mQQLogin = (ImageView)findViewById(R.id.QQ_button);

        //登录进入主界面
        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取用户名和密码，判断格式是否正确
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

//                判断输入的内容是否为phone
//                boolean b = isPhoneNumber(mobile);

                if (username.isEmpty() || password.isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else if (password.length() < 6) {
                    Toast toast = Toast.makeText(LoginActivity.this, "密码不能少于六位数", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else {
                    //login(username, password);

                }

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        });

        //跳转注册界面
        mRegisterTV = (TextView) findViewById(R.id.register_tv);
        mRegisterTV.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线
        mRegisterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                
            }
        });

        //qq登录
        mQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent = Tencent.createInstance("101533857",getApplicationContext());
                mTencent.login(LoginActivity.this,"all",new BaseUiListener());
            }
        });


    }


    //qq登录
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());

        if(requestCode == Constants.REQUEST_API) {
            if(resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, new BaseUiListener());
            }
        }

    }

    private class BaseUiListener implements IUiListener {
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
//            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_SHORT).show();
        }


    }

}



