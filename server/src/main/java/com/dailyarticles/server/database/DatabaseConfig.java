package com.dailyarticles.server.database;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static final DataSource dataSource = DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/dailyarticlesdb")
            .username("root")
            .password("root")
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();

    public static JdbcTemplate createJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}
