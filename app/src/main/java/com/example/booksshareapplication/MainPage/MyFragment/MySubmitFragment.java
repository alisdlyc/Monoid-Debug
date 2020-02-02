package com.example.booksshareapplication.MainPage.MyFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksshareapplication.MainPage.MainSearchActivity;
import com.example.booksshareapplication.R;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.booksshareapplication.MainPage.FirstSeeActivity.mBaseUrl;
import static com.example.booksshareapplication.MainPage.FirstSeeActivity.md5;

public class MySubmitFragment extends Fragment {
    private EditText mEtStudentId_sm,mEtPassword_sm;
    private ImageView mIvSubmit;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public int IsLogin=0;
    private TextView mTvHaveNoAccount;
    private MyRegisterFragment mRgfragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_submit,container,false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIvSubmit=view.findViewById(R.id.submit_send);
        mEtStudentId_sm=view.findViewById(R.id.PostId_submit);
        mEtPassword_sm=view.findViewById(R.id.password_submit);
        //如果以及成功注册则从本地读取数据
        sharedPreferences= Objects.requireNonNull(getActivity()).getSharedPreferences("BooksData",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        mTvHaveNoAccount=view.findViewById(R.id.HaveNoAccount);
        mTvHaveNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前暂无账号，跳转到注册页面
                mRgfragment=new MyRegisterFragment();
                Objects.requireNonNull(getFragmentManager()).beginTransaction().replace(R.id.fl_first_see,mRgfragment).commitAllowingStateLoss();
            }
        });

        if(sharedPreferences.getBoolean("IsRegister",false)){
            mEtStudentId_sm.setText(sharedPreferences.getString("studentId",""));
            mEtPassword_sm.setText(sharedPreferences.getString("password",""));
            mIvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //对用户名和密码进行检测
                    final OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("studentId", sharedPreferences.getString("studentId",""))
                            .add("password", md5(sharedPreferences.getString("password","")))
                            .build();

                    final Request request = new Request.Builder()
                            .url(mBaseUrl+"login")
                            .post(requestBody)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build();

                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response response=client.newCall(request).execute();
                                IsLogin=Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(response.body()).string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //假设为1时用户名与密码匹配，则登录成功
                    switch (IsLogin){
                        case 0:
                            Toast.makeText(getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            //成功登录之后记录登录信息，在下次打开App的时候直接跳转到搜索界面
                            editor.putBoolean("LoginSuccess",true).apply();

                            //如果当前账号不是在注册之后直接登录的，则记录当前账号的账号与密码，下次直接登录
                            if(sharedPreferences.getBoolean("IsRegister",false)){
                                editor.putString("studentId",mEtStudentId_sm.getText().toString())
                                        .putString("password",mEtPassword_sm.getText().toString())
                                        .putBoolean("IsRegister",true)
                                        .apply();
                            }
                            Intent intent=new Intent(getActivity(), MainSearchActivity.class);
                            startActivity(intent);
                            Toast.makeText(getContext(),"成功登录",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }
}
