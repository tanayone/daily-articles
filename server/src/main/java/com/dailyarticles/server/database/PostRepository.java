package com.dailyarticles.server.database;

import com.dailyarticles.server.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class PostRepository {

    private static final JdbcTemplate jdbcTemplate = DatabaseConfig.createJdbcTemplate();

    public static boolean insertPost(Post post, String author_id, String author_name){
        String title = post.getTitle();
        String description = post.getDescription();
        String content = post.getContent();
        String date = post.getDate();

        String queryForPosting = "INSERT INTO POST(AUTHOR_ID, AUTHOR, TITLE, DESCRIPTION, CONTENT, DATE) VALUES(?, ?, ?, ?, ?, ?)";
        int updateResponse = jdbcTemplate.update(queryForPosting, author_id, author_name, title, description, content, date);

        return updateResponse != 0;
    }

    public static List<ShortPost> sendAllPosts(){
        String queryForAllPosts = "SELECT PID, AUTHOR, TITLE, DESCRIPTION, DATE FROM POST";
        return jdbcTemplate.query(queryForAllPosts, (resultSet, rowNum) -> new ShortPost(
                resultSet.getLong("pid"),
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("date")
        ));
    }

    public static List<ShortPost> sendAllPostsForUser(String id){
        String queryForAllPosts = "SELECT PID, AUTHOR, TITLE, DESCRIPTION, DATE FROM POST WHERE Author_ID = ?";
        return jdbcTemplate.query(queryForAllPosts, (resultSet, rowNum) -> new ShortPost(
                resultSet.getLong("pid"),
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("date")
        ), id);
    }

    public static List<LongPost> sendPostWithId(int id){
        String queryForPost = "SELECT AUTHOR, TITLE, CONTENT, DATE FROM POST WHERE PID = ?";
        return jdbcTemplate.query(queryForPost, (resultSet,rowNum) -> new LongPost(
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getString("date"),
                true
        ), id);
    }
    public static boolean isPostAvailable(int id){
        String queryForPost = "SELECT COUNT(*) FROM POST WHERE PID = ?";
        int Response = jdbcTemplate.queryForObject(queryForPost, Integer.class, id);
        if (Response == 0){
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean deletePostById(int pid){
        String queryToDeletePost = "DELETE FROM POST WHERE PID = ?";
        int DeleteResponse = jdbcTemplate.update(queryToDeletePost, pid);
        return DeleteResponse > 0;
    }
    public static String getPostAuthorId(int id){
        String queryForAuthorId = "SELECT AUTHOR_ID FROM POST WHERE PID = ?";
        return jdbcTemplate.queryForObject(queryForAuthorId, String.class, id);
    }
}
