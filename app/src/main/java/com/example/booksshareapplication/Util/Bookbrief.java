package com.example.booksshareapplication.Util;

import java.io.Serializable;

public class Bookbrief implements Serializable {
    @Override
    public String toString() {
        return "Bookbrief{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pressingYear=" + pressingYear +
                ", press='" + press + '\'' +
                ", html='" + html + '\'' +
                ", borrowingTimes=" + borrowingTimes +
                ", tags='" + tags + '\'' +
                ", url='" + url + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
    public Bookbrief(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPressingYear() {
        return pressingYear;
    }

    public void setPressingYear(long pressingYear) {
        this.pressingYear = pressingYear;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public long getBorrowingTimes() {
        return borrowingTimes;
    }

    public void setBorrowingTimes(long borrowingTimes) {
        this.borrowingTimes = borrowingTimes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Bookbrief(long id, String name, long pressingYear, String press, String html, long borrowingTimes, String tags, String url, String sort) {
        this.id = id;
        this.name = name;
        this.pressingYear = pressingYear;
        this.press = press;
        this.html = html;
        this.borrowingTimes = borrowingTimes;
        this.tags = tags;
        this.url = url;
        this.sort = sort;
    }

    private long id;
    private String name;
    private long pressingYear;
    private String press;
    private String html;
    private long borrowingTimes;
    private String tags;
    private String url;
    private String sort;
}
