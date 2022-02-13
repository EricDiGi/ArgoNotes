package com.example.UserApp;

import com.example.UserApp.Objects.Credentials;
import com.example.UserApp.Objects.UnHashableException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryForms {
    @FXML
    private Label warnUser;
    @FXML
    private TextField u_name;
    @FXML
    private TextField p_word;

    @FXML
    private VBox fence;

    @FXML
    protected void doLogin(){
        Credentials cred = new Credentials();
        try {
            cred.setUsername(u_name.getText());
            cred.setPassword(p_word.getText());
        }catch(UnHashableException e){
            warnUser.setText("Could not hash your password!");
        }
    }

    @FXML
    protected void signUpRedirect() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage)u_name.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
