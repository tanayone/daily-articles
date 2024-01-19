package com.dailyarticles.server.model;

public class LongPost {
    private String Author;
    private String Title;
    private String Content;
    private String Date;

    private boolean post;

    public boolean isPost() {
        return post;
    }

    public void setPost(boolean post) {
        this.post = post;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public LongPost(String author, String title, String content, String date, boolean post) {
        Author = author;
        Title = title;
        Content = content;
        Date = date;
        this.post = post;
    }
}
