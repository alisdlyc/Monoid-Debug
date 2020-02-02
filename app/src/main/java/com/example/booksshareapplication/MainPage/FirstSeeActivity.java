package com.example.booksshareapplication.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.example.booksshareapplication.MainPage.MyFragment.MyRegisterFragment;
import com.example.booksshareapplication.MainPage.MyFragment.MySubmitFragment;
import com.example.booksshareapplication.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FirstSeeActivity extends AppCompatActivity {

    public static String mBaseUrl="http://39.107.77.0:8888/";
    private FrameLayout mFramLayout;
    private MyRegisterFragment mRsFragment;
    private MySubmitFragment mSbFragment;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_see);
        mSharedPreferences=getSharedPreferences("BooksData",MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();
        mFramLayout=findViewById(R.id.fl_first_see);

        //若没有登录，则根据注册状态设置跳转到登录页面，还是注册页
        if(!mSharedPreferences.getBoolean("LoginSuccess",false)){
            if(!mSharedPreferences.getBoolean("IsRegister",false)){
                //首次打开时设置注册页为启动页
                mRsFragment=new MyRegisterFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_first_see,mRsFragment).commitAllowingStateLoss();
            }else {
                mSbFragment=new MySubmitFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_first_see,mSbFragment).commitAllowingStateLoss();
            }
        }else{
            //该账号已经成功登录,跳转到搜索页面
            Intent intent=new Intent(FirstSeeActivity.this, MainSearchActivity.class);
            startActivity(intent);
        }

    }

    //计算String的md5数值
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
