package com.example.translatetest1;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnect {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydictionary";
    private static String USER_NAME = "root";
    private static String PASSWORD = "12345678";

    private static Dictionary<String, String> wordsData = new Hashtable<>();
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
                wordsData.put(rs.getString(2), rs.getString(3));
            }

//            String hi = wordsData.get("hi");
//            System.out.println(hi);

            // them tu
            long startPutTime = System.nanoTime();
            String asdxcz = "asdxcz";
            if (wordsData.get("asdxcz") != null) {
                System.out.println("word already exists");
            } else {
                wordsData.put("asdxcz", "khong co nghia");
                System.out.println("put word in dictionary");
            }
            long endPutTime = System.nanoTime();
            System.out.println("word definition: " + wordsData.get("asdxcz"));
            System.out.println("time to put: " + (endPutTime - startPutTime) / 1000 + "us");

            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

