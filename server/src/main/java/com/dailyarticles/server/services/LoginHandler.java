package com.dailyarticles.server.services;

import com.dailyarticles.server.objects.DataForAuth;
import com.dailyarticles.server.objects.LoginResponse;
import com.dailyarticles.server.database.UserDataMethods;

import static com.dailyarticles.server.database.UserDataMethods.emailPresent;

public class LoginHandler {
    public static LoginResponse logUser(DataForAuth dataForAuth){
        LoginResponse loginResponse = new LoginResponse();
        String email = dataForAuth.getEmail();
        boolean emailPrs = UserDataMethods.emailPresent(email);
        if(emailPrs) {
            boolean auth = UserDataMethods.userAuth(dataForAuth);
            loginResponse.setLogin(auth);
            return loginResponse;
        }
        else{
            loginResponse.setLogin(false);
            return loginResponse;
        }
    }
}
