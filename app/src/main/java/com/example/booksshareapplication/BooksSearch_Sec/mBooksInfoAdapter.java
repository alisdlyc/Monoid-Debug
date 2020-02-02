package com.example.booksshareapplication.BooksSearch_Sec;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BookDetail;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mBooksInfoAdapter extends RecyclerView.Adapter<mBooksInfoAdapter.LinearViewHolder> {
    private Context mContext;
    private static String TAG="mBooksInfo";
    public BookDetail mBookInfo;

    public mBooksInfoAdapter(Context context,BookDetail mBooksInfo) {
        this.mContext = context;
        this.mBookInfo = mBooksInfo;
    }

    @NonNull
    @Override
    public mBooksInfoAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入需要的布局文件
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_rv_mbooksinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
            holder.mTvWriter.setText(mBookInfo.getWriter());

            holder.mTvBookName.setText(mBookInfo.getName());
            holder.mIv_BookImage.setImageResource(R.mipmap.bj_4);

            Glide.with(mContext)
                    .load(mBookInfo.getImgLink())
                    .fitCenter()
                    .error(R.mipmap.image_book_1)
                    .into(holder.mIv_BookImage);

            holder.mTvPress.setText(mBookInfo.getPress());
            holder.mTvPressingYear.setText(mBookInfo.getPressingYear());
            holder.mTvDefaultComment.setText(mBookInfo.getDefaultComment());



    }

    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return 1;
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
            mTvWriter = itemView.findViewById(R.id.rv_booksinfo_Writer);
            mTvPress = itemView.findViewById(R.id.rv_booksinfo_Press);
            mTvPressingYear = itemView.findViewById(R.id.rv_booksinfo_PressingYear);
            mTvDefaultComment = itemView.findViewById(R.id.rv_info_context);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
