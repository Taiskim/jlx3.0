package com.example.jinglaixue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_username;
    private EditText ed_email;
    private EditText ed_password;
    private EditText ed_password2;
    private Button btn_register;
    private TextView tv_login;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();
    }

    private void initUI() {
        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_password2 = findViewById(R.id.ed_password2);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_loginbtn);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登陆
            case R.id.tv_loginbtn:
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_register:
                //获取编辑框中的账户密码信息
                String email = ed_email.getText().toString().trim();
                String username = ed_username.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                String password2 = ed_password2.getText().toString().trim();
                //检测密码是否一致
                if(!password.equals(password2))
                {
                    Toast.makeText(this,"两次密码不一致,请重新输入。",Toast.LENGTH_SHORT).show();
                    break;
                }

                //发起request请求


                        //获取response 信息（是否注册成功）

                break;
        }
    }


}
