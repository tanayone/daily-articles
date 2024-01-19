package com.dailyarticles.server.model;

public class DeletePost {
    private AuthenticationInfo Auth;
    private int Id;

    public AuthenticationInfo getAuth() {
        return Auth;
    }

    public void setAuth(AuthenticationInfo auth) {
        Auth = auth;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}