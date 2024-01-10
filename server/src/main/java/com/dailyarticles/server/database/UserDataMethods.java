package com.dailyarticles.server.database;

import com.dailyarticles.server.objects.DataForAuth;
import com.dailyarticles.server.objects.LongPost;
import com.dailyarticles.server.objects.User;
import com.dailyarticles.server.objects.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;

public class UserDataMethods {
    private static final JdbcTemplate jdbcTemplate = DatabaseConfig.createJdbcTemplate();

    //Check if Email is present
    public static boolean emailPresent(String email){
        String queryCheckEmail = "SELECT COUNT(*) FROM USERS WHERE EMAIL_ID = ?";
        int emailCount = jdbcTemplate.queryForObject(queryCheckEmail, Integer.class, email);
        if(emailCount == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean insertNewUser(User user){
        String fullname = user.getFullname();
        String email = user.getEmail().toLowerCase();
        String password = user.getPassword();
        String queryRegisterUser = "INSERT INTO USERS (FULL_NAME, EMAIL_ID, PASSWORD) VALUES(?, ?, ?)";
        int updateResponse = jdbcTemplate.update(queryRegisterUser, fullname, email, password);
        if(updateResponse == 0){
            return false;
        }
        else{
            return true;
        }
    }

    private static String getPasswordFromDB(String email){
        String queryForPassword = "SELECT PASSWORD FROM USERS WHERE EMAIL_ID = ?";
        return jdbcTemplate.queryForObject(queryForPassword, String.class, email);
    }

    public static boolean userAuth(DataForAuth dataForAuth){
        String email = dataForAuth.getEmail();
        String password = dataForAuth.getPassword();
        if(emailPresent(email)){
            String dbPassword = getPasswordFromDB(email);
            if(Objects.equals(dbPassword, password)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static String getName(String email){
        String queryForName = "SELECT FULL_NAME FROM USERS WHERE EMAIL_ID = ?";
        return jdbcTemplate.queryForObject(queryForName, String.class, email);
    }
    public static List<UserProfile> getProfileInfo(String Mail){

        String queryForProfileData = "SELECT FULL_NAME, EMAIL_ID FROM USERS WHERE EMAIL_ID = ?";
        return jdbcTemplate.query(queryForProfileData, (resultSet,rowNum) -> new UserProfile(
                resultSet.getString("full_name"),
                resultSet.getString("email_id")
        ), Mail);
    }
}
