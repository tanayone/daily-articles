package com.dailyarticles.server.services;

import com.dailyarticles.server.database.UserDataMethods;
import com.dailyarticles.server.objects.RegistrationResponse;
import com.dailyarticles.server.objects.User;
import com.dailyarticles.server.database.UserDataMethods;

public class RegistrationHandler {

    public static RegistrationResponse registerUser(User user){
        String Email = user.getEmail();
        boolean emailPresent = UserDataMethods.emailPresent(Email);
        RegistrationResponse registrationResponse = new RegistrationResponse();

        if(emailPresent){
            registrationResponse.setEmail(true);
            registrationResponse.setRegistration(false);
        }
        else {
            boolean isRegistered = UserDataMethods.insertNewUser(user);
            if(isRegistered){
                registrationResponse.setEmail(false);
                registrationResponse.setRegistration(true);
            }
        }
        return registrationResponse;
    }
}
