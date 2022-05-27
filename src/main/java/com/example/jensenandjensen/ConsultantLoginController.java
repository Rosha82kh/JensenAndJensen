package com.example.jensenandjensen;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConsultantLoginController {
    @FXML
    private PasswordField con_password_txt;
    @FXML
    private TextField con_username_txt;
    JensenAdminController j=new JensenAdminController();




    //method for consultant login
    public void consultantLogin() throws IOException {
        if(con_username_txt.getText().equals("con")&& con_password_txt.getText().equals("1234")){
            j.moveToPage("pomodoro.fxml");
        }}

    public void consultantCancel(){
        System.exit(0);

    }
}
