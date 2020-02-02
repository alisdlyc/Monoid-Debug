package com.example.booksshareapplication.BooksSearch_First;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.example.booksshareapplication.BooksSearch_Sec.BooksInfoActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BookDetail;
import com.example.booksshareapplication.Util.Bookbrief;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.booksshareapplication.MainPage.FirstSeeActivity.mBaseUrl;

public class mBooksShowAdapter extends RecyclerView.Adapter<mBooksShowAdapter.LinearViewHolder> {
    private Context mContext;
    public Drawable mDrawable;
    private ArrayList<Bookbrief> BooksData;
    public BookDetail mBooksInfo;
    private static String TAG="mBooksShowAdapter";

    public mBooksShowAdapter(Context context, ArrayList<Bookbrief> mBooksData) {
        this.mContext = context;
        this.BooksData = mBooksData;
    }

    @NonNull
    @Override
    public mBooksShowAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入需要的布局文件,当前应为layout_rv_mbooksdata
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_rv_mbooksdata, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mBooksShowAdapter.LinearViewHolder holder, final int position) {
        //修改RecycleView布局文件中的控件
        //修改布局文件控件的值来输出所有的书籍
        //根据positon的不同，来填入不同的书籍

        /// 轮换背景颜色
//        if(position%2==0){
//
//            holder.mView.setBackgroundResource(R.drawable.back_gray);
//        }else {
//                ;
//        }


        holder.mIv_BookImage.setImageResource(R.mipmap.bj_4);
        Glide.with(mContext)
                .load(BooksData.get(position).getUrl())
                .fitCenter()
                .error(R.mipmap.image_book_1)
                .into(holder.mIv_BookImage);
        holder.mTv_BookTitle.setText(BooksData.get(position).getName());
        holder.mTv_BookWriter.setText(BooksData.get(position).getPress());
        holder.mTv_BookISBN.setText(String.valueOf(BooksData.get(position).getPressingYear()));

        //设置监听，点击item后跳转到详情页
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "BooksName" + BooksData.get(position).getHtml(), Toast.LENGTH_LONG).show();

                // android 4.0 不能在主线程中请求HTTP
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();

                        RequestBody requestBody = new FormBody.Builder()
                                .add("html", BooksData.get(position).getHtml())
                                .build();

                        Request request = new Request.Builder()
                                .url(mBaseUrl+"getDetail")
                                .post(requestBody)
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .build();

                        try {
                            mBooksInfo=new BookDetail();
                            Response response = client.newCall(request).execute();
                            JSONArray jsonArray=JSON.parseArray(response.body().string());
                            int i=0;
                            mBooksInfo.setId(jsonArray.getJSONObject(i).getLong("id"));
                            mBooksInfo.setBarcode(jsonArray.getJSONObject(i).getLong("barcode"));
                            mBooksInfo.setName(jsonArray.getJSONObject(i).getString("name"));
                            mBooksInfo.setIsbn(jsonArray.getJSONObject(i).getString("isbn"));
                            mBooksInfo.setClassNumber(jsonArray.getJSONObject(i).getString("classNumber"));
                            mBooksInfo.setIndexNumber(jsonArray.getJSONObject(i).getString("indexNumber"));
                            mBooksInfo.setWriter(jsonArray.getJSONObject(i).getString("writer"));
                            mBooksInfo.setPress(jsonArray.getJSONObject(i).getString("press"));
                            mBooksInfo.setPressingYear(jsonArray.getJSONObject(i).getString("pressingYear"));
                            mBooksInfo.setBorrowingTimes(jsonArray.getJSONObject(i).getLong("borrowingTimes"));
                            mBooksInfo.setStatus(jsonArray.getJSONObject(i).getString("status"));
                            mBooksInfo.setArea(jsonArray.getJSONObject(i).getString("area"));
                            mBooksInfo.setDefaultComment(jsonArray.getJSONObject(i).getString("defaultComment"));
                            mBooksInfo.setDepartment(jsonArray.getJSONObject(i).getString("department"));
                            mBooksInfo.setWriterInfo(jsonArray.getJSONObject(i).getString("writerInfo"));
                            mBooksInfo.setHtml(jsonArray.getJSONObject(i).getString("html"));
                            mBooksInfo.setStar(jsonArray.getJSONObject(i).getLong("star"));
                            mBooksInfo.setImgLink(jsonArray.getJSONObject(i).getString("imgLink"));

                            Log.i(TAG,mBooksInfo.toString());
                            Intent intent = new Intent(mContext, BooksInfoActivity.class);
                            intent.putExtra("mBooksInfo", mBooksInfo);
                            mContext.startActivity(intent);
                        } catch (IOException  e) {
                            e.printStackTrace();
                        }

                    }
                }).start();


            }
        });
    }



    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return BooksData.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIv_BookImage;
        private TextView mTv_BookTitle, mTv_BookWriter, mTv_BookISBN;
        private View mView;

        public LinearViewHolder(View itemView) {
            super(itemView);
            mIv_BookImage = itemView.findViewById(R.id.rv_mbooksdata_image);
            //找到控件所在的id位置
            mTv_BookTitle = itemView.findViewById(R.id.rv_mbooksdata_title);
            mTv_BookWriter = itemView.findViewById(R.id.rv_mbooksdata_writer);
            mTv_BookISBN = itemView.findViewById(R.id.rv_mbooksdata_ISBN);
            mView=itemView.findViewById(R.id.Rv_First_view);

        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
