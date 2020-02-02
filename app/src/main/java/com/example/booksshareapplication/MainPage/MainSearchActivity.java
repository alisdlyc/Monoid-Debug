package com.example.booksshareapplication.MainPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.booksshareapplication.BooksSearch_First.BooksShowActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Bookbrief;
import com.example.booksshareapplication.Util.Course;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.booksshareapplication.MainPage.FirstSeeActivity.mBaseUrl;


public class MainSearchActivity extends AppCompatActivity implements View.OnTouchListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    public View view;
    private float PosX, curPosX;
    private EditText mEditText;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public ArrayList<Bookbrief> mBooksData;
    private ImageView mSearchIcon;
    private ImageView mBooksOrMagazine;

    public String mSearchContext;
    private Toast mToast;
    private DrawerLayout mDrawlayout;
    public boolean books_search = true;

    private TextView mBlueText;
    private String TAG="MainSearchActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences("BooksData", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mNavigationView = findViewById(R.id.home_left_Ngv);
        mDrawerLayout = findViewById(R.id.draw_main);
        mNavigationView.setItemIconTintList(null);

        mEditText = findViewById(R.id.books_search_text);
        mSearchIcon = findViewById(R.id.books_search_icon);

        mDrawerLayout = findViewById(R.id.draw_main);
        mDrawerLayout.setOnTouchListener(this);


        //可根据books_search的值来判断查询的数据库
        mBooksOrMagazine = findViewById(R.id.mIv_IsSearchBooks);
        mBooksOrMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //之前为true，当点击图标时，搜索的为期刊，更换图片，改变books_search的值
                if (books_search == true) {
                    mBooksOrMagazine.setImageResource(R.mipmap.search_for_magazine);
                    books_search = false;
                } else {
                    mBooksOrMagazine.setImageResource(R.mipmap.search_for_books);
                    books_search = true;
                }

            }
        });

//        if(onTouchEvent(mEvent))
//            mDrawerLayout.openDrawer(Gravity.LEFT);
        //点击搜索图标时，同样执行搜索操作
        mSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //点击键盘上的回车按钮时，收起键盘，执行search方法
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MainSearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行search
                    try {
                        search();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

    }


    private void search() throws IOException {

        mSearchContext = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(mSearchContext)) {
            //输入为空
        } else {

            if (mToast == null) {
                mToast = Toast.makeText(MainSearchActivity.this, "查询中...", Toast.LENGTH_LONG);
            } else {
                //用户频繁点击查询按钮
                mToast.setText("查询中");
            }
            mToast.show();

            new GetBooksInfo().start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            PosX = event.getX();
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            curPosX = event.getX();
        }

        if (curPosX - PosX > 350) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class GetBooksInfo extends Thread {
        @Override
        public void run() {
            super.run();

            final OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("name", mSearchContext)
                    .add("studentName",mSharedPreferences.getString("studentId","qwq"))
                    .build();

            final Request request = new Request.Builder()
                    .url(mBaseUrl+"getBrief")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                String mJson = Objects.requireNonNull(response.body()).string()
//                        .replace("\\", "")
//                        .replace("\"[", "[")
//                        .replace("]\"", "]");
//
                Bookbrief mJson= JSON.parseObject(response.body().string(),Bookbrief.class);
                Log.i(TAG,mJson.toString());


                //返回的response数据标准json格式化
//                mBooksData = function(mJson);

                Intent intent = new Intent(MainSearchActivity.this, BooksShowActivity.class);
                intent.putExtra("mBooksData", (Serializable) mBooksData);
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

//    // 将Bookbrief转换为ArrayList
//    private ArrayList<Bookbrief> function(Bookbrief json) {
////        JsonObject object=new JsonObject(json)
//
//    }


//    public ArrayList<Course> function(String json) throws JSONException {
//        JSONObject obj = new JSONObject(json);
//        JSONArray Books = obj.getJSONArray("Books");
//
//        ArrayList<Course> data = new ArrayList<>();
//
//        for (int i = 0; i < Books.length(); i++) {
//            Course temp = new Course();
//            JSONObject jsonObject = Books.getJSONObject(i);
//            temp.BookName = jsonObject.getString("BookName");
//            temp.Press = jsonObject.getString("Press");
//            temp.PressingYear = jsonObject.getString("PressingYear");
//            temp.html = jsonObject.getString("html");
//            temp.url=jsonObject.getString("url");
//            temp.tags=jsonObject.getString("tags");
//            data.add(temp);
//        }
//
//        return data;
//    }
}
