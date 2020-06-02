package com.example.jinglaixue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        unitUI();
    }


    private void unitUI() {
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent1=new Intent();
                intent1.setClass(getApplicationContext(),daohang.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2=new Intent();
                intent2.setClass(getApplicationContext(),RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
