package com.example.booksshareapplication.Util;

import java.io.Serializable;

public class BookDetail implements Serializable {

    public BookDetail(){
        super();
    }
    @Override
    public String toString() {
        return "BookDetail{" +
                "id=" + id +
                ", barcode=" + barcode +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", writer='" + writer + '\'' +
                ", press='" + press + '\'' +
                ", pressingYear='" + pressingYear + '\'' +
                ", borrowingTimes=" + borrowingTimes +
                ", status='" + status + '\'' +
                ", area='" + area + '\'' +
                ", defaultComment='" + defaultComment + '\'' +
                ", department='" + department + '\'' +
                ", writerInfo='" + writerInfo + '\'' +
                ", html='" + html + '\'' +
                ", star=" + star +
                ", imgLink='" + imgLink + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPressingYear() {
        return pressingYear;
    }

    public void setPressingYear(String pressingYear) {
        this.pressingYear = pressingYear;
    }

    public long getBorrowingTimes() {
        return borrowingTimes;
    }

    public void setBorrowingTimes(long borrowingTimes) {
        this.borrowingTimes = borrowingTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDefaultComment() {
        return defaultComment;
    }

    public void setDefaultComment(String defaultComment) {
        this.defaultComment = defaultComment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWriterInfo() {
        return writerInfo;
    }

    public void setWriterInfo(String writerInfo) {
        this.writerInfo = writerInfo;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public BookDetail(long id, long barcode, String name, String isbn, String classNumber, String indexNumber, String writer, String press, String pressingYear, long borrowingTimes, String status, String area, String defaultComment, String department, String writerInfo, String html, long star, String imgLink) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.isbn = isbn;
        this.classNumber = classNumber;
        this.indexNumber = indexNumber;
        this.writer = writer;
        this.press = press;
        this.pressingYear = pressingYear;
        this.borrowingTimes = borrowingTimes;
        this.status = status;
        this.area = area;
        this.defaultComment = defaultComment;
        this.department = department;
        this.writerInfo = writerInfo;
        this.html = html;
        this.star = star;
        this.imgLink = imgLink;
    }

    private long id;
    private long barcode;
    private String name;
    private String isbn;
    private String classNumber;
    private String indexNumber;
    private String writer;
    private String press;
    private String pressingYear;
    private long borrowingTimes;
    private String status;
    private String area;
    private String defaultComment;
    private String department;
    private String writerInfo;
    private String html;
    private long star;
    private String imgLink;
}
