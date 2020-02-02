package com.example.booksshareapplication.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.booksshareapplication.R;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPreferences = getSharedPreferences("BooksData", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //若没有成功登录，则跳转到登录注册页面
        if (!mSharedPreferences.getBoolean("LoginSuccess", false)) {
            intent = new Intent(SplashActivity.this, FirstSeeActivity.class);
        } else {//已经成功登录，跳转到图书查询界面
            intent = new Intent(SplashActivity.this, MainSearchActivity.class);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        }).start();
    }
}
