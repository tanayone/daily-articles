package com.dailyarticles.server.service;

import com.dailyarticles.server.database.UserRepository;
import com.dailyarticles.server.model.RegistrationResponse;
import com.dailyarticles.server.model.User;

public class RegistrationService {

    public static RegistrationResponse registerUser(User user){
        String Email = user.getEmail();
        boolean emailPresent = UserRepository.emailPresent(Email);
        RegistrationResponse registrationResponse = new RegistrationResponse();

        if(emailPresent){
            registrationResponse.setEmail(true);
            registrationResponse.setRegistration(false);
        }
        else {
            boolean isRegistered = UserRepository.insertNewUser(user);
            if(isRegistered){
                registrationResponse.setEmail(false);
                registrationResponse.setRegistration(true);
            }
        }
        return registrationResponse;
    }
}
