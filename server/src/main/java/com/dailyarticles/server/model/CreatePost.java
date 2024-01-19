package com.dailyarticles.server.model;

public class CreatePost {
    private AuthenticationInfo Auth = new AuthenticationInfo();
    private Post Post = new Post();

    public AuthenticationInfo getAuth() {
        return Auth;
    }

    public void setAuth(AuthenticationInfo auth) {
        Auth = auth;
    }
    public com.dailyarticles.server.model.Post getPost() {
        return Post;
    }

    public void setPost(com.dailyarticles.server.model.Post post) {
        Post = post;
    }
}
