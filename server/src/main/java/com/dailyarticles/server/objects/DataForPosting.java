package com.dailyarticles.server.objects;

public class DataForPosting {
    private DataForAuth Auth = new DataForAuth();
    private Post Post = new Post();

    public DataForAuth getAuth() {
        return Auth;
    }

    public void setAuth(DataForAuth auth) {
        Auth = auth;
    }
    public com.dailyarticles.server.objects.Post getPost() {
        return Post;
    }

    public void setPost(com.dailyarticles.server.objects.Post post) {
        Post = post;
    }
}
