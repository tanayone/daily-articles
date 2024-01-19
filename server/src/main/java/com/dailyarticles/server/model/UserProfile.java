package com.dailyarticles.server.model;

public class UserProfile {
    private String Fullname;
    private String Email;

    public UserProfile(String fullname, String email) {
        Fullname = fullname;
        Email = email;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email.toLowerCase();
    }
}
