package com.example.booksshareapplication.BooksSearch_First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;

import java.util.ArrayList;

public class BooksShowActivity extends Activity {
    private RecyclerView mRv_newbooks;
    public Intent intent;
    public ArrayList<Course> mBooksData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ViewPump.init(ViewPump.builder()
            .addInterceptor(new CalligraphyInterceptor(
                    new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/PingFang.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()))
            .build());
        setContentView(R.layout.activity_new_books);

        mRv_newbooks=findViewById(R.id.newbooks_center_rv);
        mRv_newbooks.setLayoutManager(new LinearLayoutManager(BooksShowActivity.this));
        //创建intent对象用于接收数据
        intent=getIntent();
        //接受intent传入的书籍
        mBooksData=(ArrayList<Course>)getIntent().getSerializableExtra("mBooksData");
        mRv_newbooks.setAdapter(new mBooksShowAdapter(BooksShowActivity.this,mBooksData));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
