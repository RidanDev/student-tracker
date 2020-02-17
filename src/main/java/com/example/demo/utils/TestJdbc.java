package com.example.demo.utils;

import java.sql.DriverManager;

/**
 * TestJdbc
 */
public class TestJdbc {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/hb-01-one-to-one-uni?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}