package com.example.booksshareapplication.Util;

import java.io.Serializable;

public class BooksInfoCourse implements Serializable {

    public String BookName;     //书名
    public String IndexNumber;  //索书号
    public String Writer;       //作者
    public String WriterInfo;   //作者信息
    public String Press;        //出版社
    public String PressingYear; //出版时间
    public String BorringTimes;    //借阅次数
    public String Department;   //沙河or本部
    public String Status;       //当前借阅状态
    public String Area;
    public String DefaultComment;//书籍简介
    public String Star;
    public String html;         //用于对图书详情页的检索
    public String url;

}
