package com.example.translatetest1;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnect {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydictionary";
    private static String USER_NAME = "root";
    private static String PASSWORD = "12345678";
    public static void main(String args[]) {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // create statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from dictionary");

            // show data

            while (rs.next()) {
                System.out.print(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3));

            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
PreparedStatement
    }

