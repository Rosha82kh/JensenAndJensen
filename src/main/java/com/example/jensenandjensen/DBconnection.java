package com.example.jensenandjensen;

import java.sql.*;
//class with connection method to get connection to database
public class DBconnection {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;


    //use DBConnection as singleton to all project
    public Connection getConnection(){
        String dataBaseName=" ";
        String dataBaseUser="sa";
        String dataBasePassword="1234";
        String url="jdbc:sqlserver://localhost:1433;databaseName=JensenAndJensen0";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection(url,dataBaseUser,dataBasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return conn;
    }
}

