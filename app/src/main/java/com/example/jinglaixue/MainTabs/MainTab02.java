package com.example.jinglaixue.MainTabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jinglaixue.R;


public class MainTab02 extends Fragment implements View.OnClickListener {

    private View thisView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.activity_j_xmode,container,false);
        unitUI();
        return thisView;
    }




    private TextView tv_time;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            tv_time.setText(msg.obj.toString());
        }
    };
    Button btn_study;
    Runnable timerun;

    boolean isStudy = false;


    private void unitUI() {
        thisView.findViewById(R.id.btn_add).setOnClickListener(this);
        thisView.findViewById(R.id.btn_minus).setOnClickListener(this);
        thisView.findViewById(R.id.btn_beginstudy).setOnClickListener(this);


        tv_time = (TextView) thisView.findViewById(R.id.tv_time);
        btn_study =  (Button)thisView.findViewById(R.id.btn_beginstudy);

        timerun = new Runnable() {
            @Override
            public void run() {
                timing();
                handler.postDelayed(this,1000);
            }

        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            //加号剪号
            case R.id.btn_add:

                String timeStr = tv_time.getText().toString().trim();

                String[] splits = timeStr.split(":");

                int h = Integer.parseInt(splits[0]);
                int m = Integer.parseInt(splits[1]);
                int s = Integer.parseInt(splits[2]);

                if (h >= 2)
                    break;

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

                splits = timeStr.split(":");

                h = Integer.parseInt(splits[0]);
                m = Integer.parseInt(splits[1]);
                s = Integer.parseInt(splits[2]);

                m = m-30;
                if(m<0){
                    h = h - 1;
                    if(h<2){
                        thisView.findViewById(R.id.btn_add).setVisibility(View.VISIBLE);
                    }
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

                if(!isStudy){
                    //开始学习
                    isStudy = true;
                    handler.postDelayed(timerun, 0);
                    btn_study.setText("停止静学");
                }else {
                    //结束学习
                    isStudy = false;
                    handler.removeCallbacks(timerun);
                    btn_study.setText("开始静学");
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


        timeStr = String.format("%02d:%02d:%02d",h,m,s);
        Message msg = new Message();
        msg.obj = timeStr;

        handler.sendMessage(msg);
    }
}
