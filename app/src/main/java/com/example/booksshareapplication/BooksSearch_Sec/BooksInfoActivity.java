package com.example.booksshareapplication.BooksSearch_Sec;

import android.content.Intent;
import android.os.Bundle;

import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BooksInfoCourse;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BooksInfoActivity extends AppCompatActivity {
    private RecyclerView mRv_newbooks;
    public Intent intent;
    public ArrayList<BooksInfoCourse> mBooksInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);

        mRv_newbooks=findViewById(R.id.newbooks_center_rv);
        mRv_newbooks.setLayoutManager(new LinearLayoutManager(BooksInfoActivity.this));
        //创建intent对象用于接收数据
        intent=getIntent();
        //接受intent传入的书籍
        mBooksInfo=(ArrayList<BooksInfoCourse>)getIntent().getSerializableExtra("mBooksInfo");
        mRv_newbooks.setAdapter(new mBooksInfoAdapter(BooksInfoActivity.this,mBooksInfo));
    }
}
