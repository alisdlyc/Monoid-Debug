package com.example.booksshareapplication.BooksSearch_First;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booksshareapplication.BooksSearch_Sec.BooksInfoActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BooksInfoCourse;
import com.example.booksshareapplication.Util.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class mBooksShowAdapter extends RecyclerView.Adapter<mBooksShowAdapter.LinearViewHolder> {
    private Context mContext;
    public Drawable mDrawable;
    private ArrayList<Course> BooksData;
    public ArrayList<BooksInfoCourse> mBooksInfo;

    public mBooksShowAdapter(Context context, ArrayList<Course> mBooksData) {
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
                .load(BooksData.get(position).url)
                .fitCenter()
                .error(R.mipmap.image_book_1)
                .into(holder.mIv_BookImage);
        holder.mTv_BookTitle.setText(BooksData.get(position).BookName);
        holder.mTv_BookWriter.setText(BooksData.get(position).Press);
        holder.mTv_BookISBN.setText(BooksData.get(position).PressingYear);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "BooksName" + BooksData.get(position).html, Toast.LENGTH_LONG).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();

                        RequestBody requestBody = new FormBody.Builder()
                                .add("PostId", "6")
                                .add("Keyword", BooksData.get(position).html.toString())
                                .build();

                        Request request = new Request.Builder()
                                .url("http://39.107.77.0:8080/web_war/api")
                                .post(requestBody)
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .build();

                        try {
                            Response response = client.newCall(request).execute();

                            String mJson = Objects.requireNonNull(response.body()).string()
                                    .replace("\\", "")
                                    .replace("\"[", "[")
                                    .replace("]\"", "]");
                            //将返回的response数据标准json格式化

                            mBooksInfo = function(mJson);

                            //将图书详情发送到BookInfoActivity中
                            Intent intent = new Intent(mContext, BooksInfoActivity.class);
                            intent.putExtra("mBooksInfo", (Serializable) mBooksInfo);
                            mContext.startActivity(intent);

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    private ArrayList<BooksInfoCourse> function(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray Books = obj.getJSONArray("Books");

        ArrayList<BooksInfoCourse> data = new ArrayList<>();


        for (int i = 0; i < Books.length(); i++) {
            BooksInfoCourse temp = new BooksInfoCourse();
            JSONObject jsonObject = Books.getJSONObject(i);
            temp.Area = jsonObject.getString("Area");
            temp.BookName = jsonObject.getString("BookName");
            temp.IndexNumber = jsonObject.getString("indexNumber");
            temp.Writer = jsonObject.getString("Writer");
            temp.WriterInfo = jsonObject.getString("WriterInfo");
            temp.Press = jsonObject.getString("Press");
            temp.PressingYear = jsonObject.getString("PressingYear");
            temp.BorringTimes = jsonObject.getString("BorrowingTimes");
            temp.Department = jsonObject.getString("Department");
            temp.Status = jsonObject.getString("Status");
            temp.DefaultComment = jsonObject.getString("DefaultComment");
            temp.Star = jsonObject.getString("Star");
            temp.url=jsonObject.getString("url");


            data.add(temp);
        }

        return data;
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
