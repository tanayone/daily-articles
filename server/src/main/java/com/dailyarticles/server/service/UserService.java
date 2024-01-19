package com.dailyarticles.server.service;

import com.dailyarticles.server.database.UserRepository;
import com.dailyarticles.server.model.AuthenticationInfo;
import com.dailyarticles.server.model.UserProfile;

import static com.dailyarticles.server.database.UserRepository.userAuth;

public class UserService {
    public static UserProfile GetProfileInfo(AuthenticationInfo dataForAuth){
        if (userAuth(dataForAuth)){
            String Mail = dataForAuth.getEmail();
            return UserRepository.getProfileInfo(Mail).get(0);
        }
        else{
            return new UserProfile("ERROR 404", "ERROR 404");
        }
    }
}
