package com.dailyarticles.server.objects;

public class RegistrationResponse {
    private  boolean Email;
    private  boolean Registration;

    public boolean isEmail() {
        return Email;
    }

    public void setEmail(boolean email) {
        Email = email;
    }

    public boolean isRegistration() {
        return Registration;
    }

    public void setRegistration(boolean registration) {
        Registration = registration;
    }
}
