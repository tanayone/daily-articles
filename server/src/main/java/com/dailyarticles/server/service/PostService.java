package com.dailyarticles.server.service;

import com.dailyarticles.server.database.PostRepository;
import com.dailyarticles.server.database.UserRepository;
import com.dailyarticles.server.model.*;

import java.util.List;
import java.util.Objects;

import static com.dailyarticles.server.database.UserRepository.userAuth;

public class PostService {
    public static PostingResponse uploadPost(CreatePost dataForPosting){
        PostingResponse postingResponse = new PostingResponse();
        AuthenticationInfo dataForAuth = dataForPosting.getAuth();
        Post post = dataForPosting.getPost();
        if (userAuth(dataForAuth)){
            String Email = dataForAuth.getEmail();
            String Name = UserRepository.getName(Email);
            boolean posted = PostRepository.insertPost(post, Email, Name);
            postingResponse.setPost(posted);
        }
        else{
            postingResponse.setPost(false);
        }
        return postingResponse;
    }
    public static Object GetPost(int id){
        boolean PostAvailable = PostRepository.isPostAvailable(id);
        if (PostAvailable){
            return PostRepository.sendPostWithId(id).get(0);
        }
        else{
            PostingResponse postingResponse = new PostingResponse();
            postingResponse.setPost(false);
            return postingResponse;
        }
    }

    public static List<ShortPost> GetUserPost(AuthenticationInfo dataForAuth){
        String Mail = dataForAuth.getEmail();
        return PostRepository.sendAllPostsForUser(Mail);
    }

    public static PostingResponse DeletePost(DeletePost dataToDelete){
        PostingResponse postingResponse = new PostingResponse();
        AuthenticationInfo dataForAuth = dataToDelete.getAuth();
        int Pid = dataToDelete.getId();
        boolean PostAvailable = PostRepository.isPostAvailable(Pid);
        if(PostAvailable){
            if (userAuth(dataForAuth)){
                String Mail = dataForAuth.getEmail();
                String AuthorId = PostRepository.getPostAuthorId(Pid);
                if(Objects.equals(AuthorId, Mail)){
                    boolean Deleted = PostRepository.deletePostById(Pid);
                    postingResponse.setPost(Deleted);
                }
                else {
                    postingResponse.setPost(false);
                }
            }
            else{
                postingResponse.setPost(false);
            }
        }
        else{
            postingResponse.setPost(false);
        }
        return postingResponse;
    }

}
