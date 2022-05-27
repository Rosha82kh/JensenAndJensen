package com.example.jensenandjensen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;


public class JensenAdminController implements Initializable {


    @FXML
    private TableView<Consultants> tblViewConsult;
    @FXML
    private TableView<Projects> project_tblView;



    @FXML
    private TableColumn<Consultants, Integer> consultant_col;
    @FXML
    private TableColumn<Consultants, String> firstname_col;
    @FXML
    private TableColumn<Consultants, String> lastname_col;
    @FXML
    private TableColumn<Consultants, String> password_col;
    @FXML
    private TableColumn<Consultants, String> username_col;
    @FXML
    private TableColumn<Consultants, String> status_col;


    @FXML
    private TableColumn<Projects, Integer> projectID_col;
    @FXML
    private TableColumn<Projects, String> projectName_col;


    Stage stage=new Stage();
    String query;
    public Connection conn=null;
    public PreparedStatement ps;
    public ResultSet rs;
    DBconnection DB=new DBconnection();



     public void loadProjectFromDataBase(){
         projectList.clear();
         conn= DB.getConnection();

         try {

             query = "SELECT * FROM project";
             ps = conn.prepareStatement(query);
             rs = ps.executeQuery();

             while (rs.next()){
                 projectList.add(new Projects(
                         rs.getInt("projectID"),
                         rs.getString("projectName")
                 ));

                 project_tblView.setItems(projectList);
                 project_tblView.getItems().addAll();

             } } catch (SQLException e){
             e.getMessage();
         } }



     //method to insert data from create consultant page
    private void loadConsultantFromDataBase() {
      //  consultantList.clear();
      conn= DB.getConnection();

        try {

            query = "SELECT * FROM consultant";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                consultantList.add(new Consultants(

                        rs.getInt("employeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("Status")
                 ));

                tblViewConsult.setItems(consultantList);
                tblViewConsult.getItems().addAll();

            }


        } catch (SQLException ex) {
            ex.getMessage();
         }  }


    public void iniConsultantTableCol(){
        consultant_col.setCellValueFactory(new PropertyValueFactory<Consultants,Integer>("ID"));
        firstname_col.setCellValueFactory(new PropertyValueFactory<Consultants,String>("FirstName"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<Consultants,String>("LastName"));
        username_col.setCellValueFactory(new PropertyValueFactory<Consultants,String>("UserName"));
        password_col.setCellValueFactory(new PropertyValueFactory<Consultants,String>("Password"));
        status_col.setCellValueFactory(new PropertyValueFactory<Consultants,String>("Status"));

        editConsultantTable();

    }

    public void iniProjectTableCol(){
        projectID_col.setCellValueFactory(new PropertyValueFactory<Projects,Integer>("ProjectID"));
        projectName_col.setCellValueFactory(new PropertyValueFactory<Projects,String>("ProjectName"));

    }

     //method to move the page from current page to target page with change the url of fxml
     public void moveToPage(String url) throws IOException {
         //"JensenAdmin.fxml"
         FXMLLoader fxmlLoader1 = new FXMLLoader(JensenApplication.class.getResource(url));
         Scene scene = new Scene(fxmlLoader1.load(), 900, 600);
         stage.setTitle("Jensen & Jensen for Consultant!");
         stage.setScene(scene);
         stage.show();

     }

    public void createConsultant() throws IOException {
        moveToPage("CreateConsultant.fxml");

    }
    public void createProject() throws IOException {
        moveToPage("createProject.fxml");

    }
    public void getReport() throws IOException {
         moveToPage("reportPage.fxml");
    }


     public void editConsultantTable(){
        /* consultant_col.setCellFactory(TextFieldTableCell.forTableColumn());
         consultant_col.setOnEditCommit(e->{
             e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
         });*/
         firstname_col.setCellFactory(TextFieldTableCell.forTableColumn());
         firstname_col.setOnEditCommit(e->{
                 e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstName(e.getNewValue());
         });
         lastname_col.setCellFactory(TextFieldTableCell.forTableColumn());
         lastname_col.setOnEditCommit(e->{
                 e.getTableView().getItems().get(e.getTablePosition().getRow()).setLastName(e.getNewValue());
         });
         username_col.setCellFactory(TextFieldTableCell.forTableColumn());
         username_col.setOnEditCommit(e->{
                 e.getTableView().getItems().get(e.getTablePosition().getRow()).setUserName(e.getNewValue());
         });
         password_col.setCellFactory(TextFieldTableCell.forTableColumn());
         password_col.setOnEditCommit(e->{
                 e.getTableView().getItems().get(e.getTablePosition().getRow()).setPassword(e.getNewValue());
         });
         status_col.setCellFactory(TextFieldTableCell.forTableColumn());
         status_col.setOnEditCommit(e->{
                 e.getTableView().getItems().get(e.getTablePosition().getRow()).setStatus(e.getNewValue());
         });
         tblViewConsult.setEditable(true);
     }

    ObservableList<Consultants> consultantList= FXCollections.observableArrayList();
    ObservableList<Projects> projectList= FXCollections.observableArrayList();


    //method to delete selected row in consultant table
     public void deleteConsultant(){
        int selectedConsultant=tblViewConsult.getSelectionModel().getSelectedIndex();
        tblViewConsult.getItems().remove(selectedConsultant);
        //method to remove from database
    }


     public void deleteProject(){
        int selectedProject=project_tblView.getSelectionModel().getSelectedIndex();
        project_tblView.getItems().remove(selectedProject);
         //method to remove from database

     }

   /* public void Adapter(){
        int selected = 0;
        if(selected == selectedConsultant){
            deleteConsultant();
        }else if(selected == selectedProject){
            deleteProject();
        }
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniConsultantTableCol();
        iniProjectTableCol();
        loadConsultantFromDataBase();
        loadProjectFromDataBase();


    }
         }