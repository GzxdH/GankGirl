package com.example.gankgirl.bean;

public class NetBook {
    private String bPre;//上一章
    private String bPreUrl;
    private String bNext;//下一章
    private String bNextUrl;
    private String bAll;//目录
    private String[] bContent;//内容
    private String bAuthor;//作者
    private String bName;//书名
    private String bChapter;//章节名称

    public String getbPreUrl() {
        return bPreUrl;
    }

    public void setbPreUrl(String bPreUrl) {
        this.bPreUrl = bPreUrl;
    }

    public String getbNextUrl() {
        return bNextUrl;
    }

    public void setbNextUrl(String bNextUrl) {
        this.bNextUrl = bNextUrl;
    }

    public String getbChapter() {
        return bChapter;
    }

    public void setbChapter(String bChapter) {
        this.bChapter = bChapter;
    }

    public String getbPre() {
        return bPre;
    }

    public void setbPre(String bPre) {
        this.bPre = bPre;
    }

    public String getbNext() {
        return bNext;
    }

    public void setbNext(String bNext) {
        this.bNext = bNext;
    }

    public String getbAll() {
        return bAll;
    }

    public void setbAll(String bAll) {
        this.bAll = bAll;
    }

    public String[] getbContent() {
        return bContent;
    }

    public void setbContent(String[] bContent) {
        this.bContent = bContent;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
}
