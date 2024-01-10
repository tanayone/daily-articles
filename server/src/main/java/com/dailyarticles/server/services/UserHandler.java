package com.dailyarticles.server.services;

import com.dailyarticles.server.database.UserDataMethods;
import com.dailyarticles.server.objects.DataForAuth;
import com.dailyarticles.server.objects.UserProfile;

import static com.dailyarticles.server.database.UserDataMethods.userAuth;

public class UserHandler {
    public static UserProfile GetProfileInfo(DataForAuth dataForAuth){
        if (userAuth(dataForAuth)){
            String Mail = dataForAuth.getEmail();
            return UserDataMethods.getProfileInfo(Mail).get(0);
        }
        else{
            return new UserProfile("ERROR 404", "ERROR 404");
        }
    }
}
