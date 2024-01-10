package com.dailyarticles.server.objects;

public class DataToDelete {
    private DataForAuth Auth;
    private int Id;

    public DataForAuth getAuth() {
        return Auth;
    }

    public void setAuth(DataForAuth auth) {
        Auth = auth;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}