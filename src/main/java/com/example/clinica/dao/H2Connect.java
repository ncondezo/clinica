package com.example.clinica.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class H2Connect {

    private final static String DB_URL="jdbc:h2:~/clinica;INIT=RUNSCRIPT FROM 'create.sql'";

    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(DB_URL);
    }
}
