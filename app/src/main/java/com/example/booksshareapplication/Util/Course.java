package com.example.booksshareapplication.Util;

import java.io.Serializable;

public class Course implements Serializable {
    public String BookName;     //书名
    public String Press;        //出版社
    public String PressingYear; //出版时间
    public String html;//用于对图书详情页的检索
    public String url; //图片的url地址
    public String tags; //图书的标签
}
