package com.example.jensenandjensen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateProjectController {

    @FXML
    private TextField txt_project;
    String query;
    public Connection conn=null;
    public PreparedStatement ps;
    public ResultSet rs;
    DBconnection DB=new DBconnection();


    public  void insertNewProject(){
        try{

            conn=DB.getConnection();
            query="INSERT INTO project  VALUES ( ?, ?);";
            ps = conn.prepareStatement(query);
            ps.setInt(1,912677);
            ps.setString(2, txt_project.getText());
            System.out.println("Inserted records into the table...");

            ps.executeUpdate();


        }catch (SQLException e){
            e.getMessage();
        }

    }
}
