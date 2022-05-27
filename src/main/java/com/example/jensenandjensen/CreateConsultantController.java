package com.example.jensenandjensen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateConsultantController {
    @FXML
    private Label lbl_message;

    @FXML
    private TextField txt_lastname;

    @FXML
    private TextField txt_name;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_status;

    @FXML
    private TextField txt_username;

    String query;
    public Connection conn=null;
    public PreparedStatement ps;
    public ResultSet rs;
    DBconnection DB=new DBconnection();
    public void insertConsultantToDataBase(){
        try{

            conn=DB.getConnection();
            query="INSERT INTO consultant  VALUES (?,?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setInt(1,8912);
            ps.setString(4,txt_name.getText());
            ps.setString(5,txt_lastname.getText());
            ps.setString(2,txt_username.getText());
            ps.setString(3,txt_password.getText());
            ps.setString(6,txt_status.getText());
            System.out.println("Inserted records into the table...");
            lbl_message.setText("Inserted records into the table.");
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e){
            e.getMessage();
        }

    }
}
