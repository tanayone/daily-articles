package com.dailyarticles.server.model;

public class ShortPost {

    private long Pid;
    private String Author;
    private String Title;
    private String Description;
    private String Date;

    public ShortPost(long pid, String author, String title, String description, String date) {
        Pid = pid;
        Author = author;
        Title = title;
        Description = description;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public long getPid() {
        return Pid;
    }

    public void setPid(long pid) {
        Pid = pid;
    }
}
