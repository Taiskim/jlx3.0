package com.example.jinglaixue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        unitUI();
}

    private void unitUI() {
        findViewById(R.id.rb_map).setOnClickListener(this);
        findViewById(R.id.rb_car).setOnClickListener(this);
        findViewById(R.id.rb_find).setOnClickListener(this);
        findViewById(R.id.rb_me).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_map:
                Intent intent1=new Intent();
                intent1.setClass(getApplicationContext(),daohang.class);
                startActivity(intent1);
                break;
            case R.id.rb_find:
                Intent intent2=new Intent();
                intent2.setClass(getApplicationContext(),JXmode.class);
                startActivity(intent2);
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
        }
    }
}
