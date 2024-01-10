package com.dailyarticles.server;

import com.dailyarticles.server.database.PostingDataMethods;
import com.dailyarticles.server.database.UserDataMethods;
import com.dailyarticles.server.objects.*;
import com.dailyarticles.server.services.LoginHandler;
import com.dailyarticles.server.services.PostingHandler;
import com.dailyarticles.server.services.RegistrationHandler;
import com.dailyarticles.server.services.UserHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dailyarticles.server.database.UserDataMethods.userAuth;

@RestController
@CrossOrigin(origins = "*")
public class RequestHandler {
    @PostMapping("/reg")
    public RegistrationResponse registrationRequest(@RequestBody User user){
        return RegistrationHandler.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginRequest(@RequestBody DataForAuth dataForAuth){
        return LoginHandler.logUser(dataForAuth);
    }

    @PostMapping("/name")
    public String nameRequest(@RequestBody DataForAuth dataForAuth) {
        if (userAuth(dataForAuth)) {
            return UserDataMethods.getName(dataForAuth.getEmail());
        }
        else{
            return "User";
        }
    }

    @PostMapping("/upload")
    public PostingResponse uploadRequest(@RequestBody DataForPosting dataForPosting){
        return PostingHandler.uploadPost(dataForPosting);
    }

    @GetMapping("/posts")
    public List<ShortPost> getAllPosts(){
        return PostingDataMethods.sendAllPosts();
    }

    @GetMapping("/post")
    public Object getPostWithID(@RequestParam(name = "id") int id){
        return PostingHandler.GetPost(id);
    }
    // ----------------------- Work Remains -----------------------
    @PostMapping("/user/posts")
    public List<ShortPost> getUserAllPost(@RequestBody DataForAuth dataForAuth){
        return PostingHandler.GetUserPost(dataForAuth);
    }

    @PostMapping("/post/delete")
    public PostingResponse deleteRequest(@RequestBody DataToDelete dataToDelete){
        return PostingHandler.DeletePost(dataToDelete);
    }

    @PostMapping("/profile")
    public UserProfile userProfile(@RequestBody DataForAuth dataForAuth){
        return UserHandler.GetProfileInfo(dataForAuth);
    }

    @GetMapping("/test")
    public String test(){
        return "Tanay Sarkar";
    }
}
