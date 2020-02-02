package com.example.booksshareapplication.BooksSearch_Sec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BooksInfoCourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mBooksInfoAdapter extends RecyclerView.Adapter<mBooksInfoAdapter.LinearViewHolder> {
    private Context mContext;
    public ArrayList<BooksInfoCourse> mBooksInfo;

    public mBooksInfoAdapter(Context context, ArrayList<BooksInfoCourse> mBooksInfo) {
        this.mContext = context;
        this.mBooksInfo = mBooksInfo;
    }

    @NonNull
    @Override
    public mBooksInfoAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入需要的布局文件
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_rv_mbooksinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
        //修改RecycleView布局文件中的控件
        //修改布局文件控件的值来输出所有的书籍
        //根据positon的不同，来填入不同的书籍
//        holder.mTvindexNumber.setText(mBooksInfo.get(position).IndexNumber);
//        holder.mTvBorrowingTimes.setText("当前已被借阅" + mBooksInfo.get(position).BorringTimes + "次");

//        holder.mTvDepartment.setText(mBooksInfo.get(position).Department);
//        holder.mTvFloor.setText(mBooksInfo.get(position).Floor);
//        holder.mTvArea.setText(mBooksInfo.get(position).Area);
//        holder.mTvShelf.setText(mBooksInfo.get(position).Shelf);
//        holder.mTvShelfFloor.setText(mBooksInfo.get(position).ShelfFloor);
//        holder.mTvStar.setText(mBooksInfo.get(position).Star);

        if (position == 0) {
            holder.mTvWriter.setText(mBooksInfo.get(position).Writer);
            holder.mTvBookName.setText(mBooksInfo.get(position).BookName);
            holder.mIv_BookImage.setImageResource(R.mipmap.bj_4);

            Glide.with(mContext)
                    .load(mBooksInfo.get(position).url)
                    .fitCenter()
                    .error(R.mipmap.image_book_1)
                    .into(holder.mIv_BookImage);

            holder.mTvPress.setText(mBooksInfo.get(position).Press);
            holder.mTvPressingYear.setText(mBooksInfo.get(position).PressingYear);
            holder.mTvDefaultComment.setText(mBooksInfo.get(position).DefaultComment);

        } else {
            holder.mTvWriter.setVisibility(View.GONE);
            holder.mTvBookName.setVisibility(View.GONE);
            holder.mIv_BookImage.setVisibility(View.GONE);

            holder.mTvPress.setVisibility(View.GONE);
            holder.mTvPressingYear.setVisibility(View.GONE);
            holder.mTvDefaultComment.setVisibility(View.GONE);
            holder.mFakeComment.setVisibility(View.GONE);
            holder.mFakeLocal.setVisibility(View.GONE);
            holder.mFakestar.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return mBooksInfo.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIv_BookImage;
        private TextView mTvBookName, mTvindexNumber, mTvWriter, mTvWriterInfo, mTvPress, mTvPressingYear, mTvBorrowingTimes, mTvStatus, mTvDepartment, mTvArea, mTvDefaultComment, mTvStar;

        private TextView mFakeComment,mFakeCommentInfo;
        private ImageView mFakestar,mFakeLocal;
        public LinearViewHolder(View itemView) {
            super(itemView);

            mFakeComment=itemView.findViewById(R.id.fake_context);
            mFakeLocal=itemView.findViewById(R.id.fake_shelflocal);
            mFakestar=itemView.findViewById(R.id.fake_star);

            mIv_BookImage = itemView.findViewById(R.id.rv_newbooks_image);

            mTvBookName = itemView.findViewById(R.id.rv_booksinfo_BookName);
//            mTvindexNumber = itemView.findViewById(R.id.rv_booksinfo_indexNumber);
            mTvWriter = itemView.findViewById(R.id.rv_booksinfo_Writer);
//            mTvWriterInfo = itemView.findViewById(R.id.rv_booksinfo_WriterInfo);
            mTvPress = itemView.findViewById(R.id.rv_booksinfo_Press);
            mTvPressingYear = itemView.findViewById(R.id.rv_booksinfo_PressingYear);
//            mTvBorrowingTimes = itemView.findViewById(R.id.rv_booksinfo_BorrowingTimes);
//            mTvStatus = itemView.findViewById(R.id.rv_booksinfo_Status);
//            mTvDepartment = itemView.findViewById(R.id.rv_booksinfo_Department);
//            mTvFloor=itemView.findViewById(R.id.rv_booksinfo_Floor);
//            mTvArea=itemView.findViewById(R.id.rv_booksinfo_Area);
//            mTvShelf=itemView.findViewById(R.id.rv_booksinfo_Shelf);
//            mTvShelfFloor=itemView.findViewById(R.id.rv_booksinfo_ShelfFloor);
            mTvDefaultComment = itemView.findViewById(R.id.rv_info_context);
//            mTvStar = itemView.findViewById(R.id.rv_booksinfo_Star);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
