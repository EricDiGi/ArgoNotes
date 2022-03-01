package com.example.UserApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class NotesViewCon implements Initializable {
    private String u_id;
    @FXML
    private Label someid;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    public void setU(String u){
        this.u_id = u;
    }
}
