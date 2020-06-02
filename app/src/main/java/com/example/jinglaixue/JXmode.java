package com.example.jinglaixue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JXmode extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_time;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            tv_time.setText(msg.obj.toString());
        }
    };
    Button btn_study;

    Thread timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_xmode);
        unitUI();
    }

    private void unitUI() {
        findViewById(R.id.rb_map).setOnClickListener(this);
        findViewById(R.id.rb_car).setOnClickListener(this);
        findViewById(R.id.rb_find).setOnClickListener(this);
        findViewById(R.id.rb_me).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_beginstudy).setOnClickListener(this);


        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_study =  (Button)findViewById(R.id.btn_beginstudy);

        Runnable timerun = new Runnable() {
            @Override
            public void run() {
                while(true){
                    timing();
                }

            }

        };
        timer = new Thread(timerun);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_map:
                Intent intent1=new Intent();
                intent1.setClass(getApplicationContext(),daohang.class);
                startActivity(intent1);
                break;
            case R.id.rb_me:
                Intent intent3=new Intent();
                intent3.setClass(getApplicationContext(),Wechat.class);
                startActivity(intent3);
                break;
            case R.id.rb_car:
                Intent intent4=new Intent();
                intent4.setClass(getApplicationContext(),Setting.class);
                startActivity(intent4);
                break;


                //加号剪号
            case R.id.btn_add:



                String timeStr = tv_time.getText().toString().trim();
                //Toast.makeText(this,timeStr,Toast.LENGTH_SHORT).show();

                String[] splits = timeStr.split(":");

                int h = Integer.parseInt(splits[0]);
                int m = Integer.parseInt(splits[1]);
                int s = Integer.parseInt(splits[2]);

                if(h>=2){
                    findViewById(R.id.btn_add).setVisibility(View.INVISIBLE);
                    break;
                }


                m = m+30;
                if(m>=60){
                    h = h+1;
                    m = m % 60;
                }

                timeStr = String.format("%02d:%02d:%02d",h,m,s);
                tv_time.setText(timeStr);


                break;
            case R.id.btn_minus:
                timeStr = tv_time.getText().toString().trim();
                //Toast.makeText(this,timeStr,Toast.LENGTH_SHORT).show();

                splits = timeStr.split(":");

                h = Integer.parseInt(splits[0]);
                m = Integer.parseInt(splits[1]);
                s = Integer.parseInt(splits[2]);




                if(h<2){
                    findViewById(R.id.btn_add).setVisibility(View.VISIBLE);
                }


                m = m-30;
                if(m<0){
                    h = h - 1;
                    m = 60 + m;
                    if(h<0){
                        h = 0;
                        m = 0;
                    }

                }

                timeStr = String.format("%02d:%02d:%02d",h,m,s);
                tv_time.setText(timeStr);
                break;
                //开始静学
            case R.id.btn_beginstudy:

                if (timer.isAlive()){

                    timer.interrupt();

                    btn_study.setText("开始静学");
                }else {
                    timer.start();

                    btn_study.setText("停止静学");
                }
                break;
        }
    }

    //计时线程
    private void timing(){

        String timeStr = tv_time.getText().toString().trim();

        String[] splits = timeStr.split(":");

        int h = Integer.parseInt(splits[0]);
        int m = Integer.parseInt(splits[1]);
        int s = Integer.parseInt(splits[2]);

        s = s - 1;
        if (s < 0){
            s = 59;
            m = m - 1;
            if(m<0){
                m = 59 ;
                h = h - 1;
                if(h < 0){
                    h = 0;
                }
            }
        }
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeStr = String.format("%02d:%02d:%02d",h,m,s);
        Message msg = new Message();
        msg.obj = timeStr;

        handler.sendMessage(msg);
    }


}
