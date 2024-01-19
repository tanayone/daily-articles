package com.dailyarticles.server.controller;

import com.dailyarticles.server.database.PostRepository;
import com.dailyarticles.server.database.UserRepository;
import com.dailyarticles.server.model.*;
import com.dailyarticles.server.service.LoginService;
import com.dailyarticles.server.service.PostService;
import com.dailyarticles.server.service.RegistrationService;
import com.dailyarticles.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dailyarticles.server.database.UserRepository.userAuth;

@RestController
@CrossOrigin(origins = "*")
public class RequestController {
    @PostMapping("/reg")
    public RegistrationResponse registrationRequest(@RequestBody User user){
        return RegistrationService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginRequest(@RequestBody AuthenticationInfo dataForAuth){
        return LoginService.logUser(dataForAuth);
    }

    @PostMapping("/name")
    public String nameRequest(@RequestBody AuthenticationInfo dataForAuth) {
        if (userAuth(dataForAuth)) {
            return UserRepository.getName(dataForAuth.getEmail());
        }
        else{
            return "User";
        }
    }

    @PostMapping("/upload")
    public PostingResponse uploadRequest(@RequestBody CreatePost dataForPosting){
        return PostService.uploadPost(dataForPosting);
    }

    @GetMapping("/posts")
    public List<ShortPost> getAllPosts(){
        return PostRepository.sendAllPosts();
    }

    @GetMapping("/post")
    public Object getPostWithID(@RequestParam(name = "id") int id){
        return PostService.GetPost(id);
    }
    // ----------------------- Work Remains -----------------------
    @PostMapping("/user/posts")
    public List<ShortPost> getUserAllPost(@RequestBody AuthenticationInfo dataForAuth){
        return PostService.GetUserPost(dataForAuth);
    }

    @PostMapping("/post/delete")
    public PostingResponse deleteRequest(@RequestBody DeletePost dataToDelete){
        return PostService.DeletePost(dataToDelete);
    }

    @PostMapping("/profile")
    public UserProfile userProfile(@RequestBody AuthenticationInfo dataForAuth){
        return UserService.GetProfileInfo(dataForAuth);
    }

    @GetMapping("/test")
    public String test(){
        return "Tanay Sarkar";
    }
}
