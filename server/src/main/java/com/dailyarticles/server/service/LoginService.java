package com.dailyarticles.server.service;

import com.dailyarticles.server.model.AuthenticationInfo;
import com.dailyarticles.server.model.LoginResponse;
import com.dailyarticles.server.database.UserRepository;

public class LoginService {
    public static LoginResponse logUser(AuthenticationInfo dataForAuth){
        LoginResponse loginResponse = new LoginResponse();
        String email = dataForAuth.getEmail();
        boolean emailPrs = UserRepository.emailPresent(email);
        if(emailPrs) {
            boolean auth = UserRepository.userAuth(dataForAuth);
            loginResponse.setLogin(auth);
            return loginResponse;
        }
        else{
            loginResponse.setLogin(false);
            return loginResponse;
        }
    }
}
