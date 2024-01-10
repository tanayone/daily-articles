package com.dailyarticles.server.services;

import com.dailyarticles.server.database.PostingDataMethods;
import com.dailyarticles.server.database.UserDataMethods;
import com.dailyarticles.server.objects.*;

import java.util.List;
import java.util.Objects;

import static com.dailyarticles.server.database.UserDataMethods.userAuth;

public class PostingHandler {
    public static PostingResponse uploadPost(DataForPosting dataForPosting){
        PostingResponse postingResponse = new PostingResponse();
        DataForAuth dataForAuth = dataForPosting.getAuth();
        Post post = dataForPosting.getPost();
        if (userAuth(dataForAuth)){
            String Email = dataForAuth.getEmail();
            String Name = UserDataMethods.getName(Email);
            boolean posted = PostingDataMethods.insertPost(post, Email, Name);
            postingResponse.setPost(posted);
        }
        else{
            postingResponse.setPost(false);
        }
        return postingResponse;
    }
    public static Object GetPost(int id){
        boolean PostAvailable = PostingDataMethods.isPostAvailable(id);
        if (PostAvailable){
            return PostingDataMethods.sendPostWithId(id).get(0);
        }
        else{
            PostingResponse postingResponse = new PostingResponse();
            postingResponse.setPost(false);
            return postingResponse;
        }
    }

    public static List<ShortPost> GetUserPost(DataForAuth dataForAuth){
        String Mail = dataForAuth.getEmail();
        return PostingDataMethods.sendAllPostsForUser(Mail);
    }

    public static PostingResponse DeletePost(DataToDelete dataToDelete){
        PostingResponse postingResponse = new PostingResponse();
        DataForAuth dataForAuth = dataToDelete.getAuth();
        int Pid = dataToDelete.getId();
        boolean PostAvailable = PostingDataMethods.isPostAvailable(Pid);
        if(PostAvailable){
            if (userAuth(dataForAuth)){
                String Mail = dataForAuth.getEmail();
                String AuthorId = PostingDataMethods.getPostAuthorId(Pid);
                if(Objects.equals(AuthorId, Mail)){
                    boolean Deleted = PostingDataMethods.deletePostById(Pid);
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
