package com.example.jensenandjensen;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class doTodayList   {

    @FXML
    private ListView<String> list_area;

    @FXML
    private TextField list_txt;

    JensenAdminController j= new JensenAdminController();
    String query;
    public Connection conn=null;
    public PreparedStatement ps;
    public ResultSet rs;
    DBconnection DB=new DBconnection();

    public void addToDoList(){
         String task= list_txt.getText();
        list_area.getItems().add(" - "+task);
        insertNewTask();

    }
    public  void insertNewTask(){
        try{

            conn=DB.getConnection();
            query="INSERT INTO tasks  VALUES (?,?);";
            ps = conn.prepareStatement(query);
            ps.setInt(1,44);
            ps.setString(2,list_txt.getText() );

            System.out.println("Inserted records into the table...");

            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e){
            e.getMessage();
        }

    }
    public void clearToDoList(){
       int selected=list_area.getSelectionModel().getSelectedIndex();
        list_area.getItems().remove(selected);
        list_txt.setText("");
    }
    public String taskToDo(){
        String selected= String.valueOf(list_area.getSelectionModel().getSelectedIndex());
        return selected;
    }

    public void backToMainPage() throws IOException {
        String selected= String.valueOf(list_area.getSelectionModel().getSelectedIndex());
       }
}
