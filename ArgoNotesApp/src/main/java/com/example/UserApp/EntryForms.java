package com.example.UserApp;

import com.example.UserApp.Objects.Account;
import com.example.UserApp.Objects.Credentials;
import com.example.UserApp.Objects.UnHashableException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.UUID;

public class EntryForms implements Initializable {
    @FXML
    private Label warnUser;
    @FXML
    private TextField u_name;
    @FXML
    private TextField p_word;
    //Sign up only
    @FXML private TextField cp_word;
    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private DatePicker dob;
    @FXML private TextField email;
    @FXML private ComboBox<String> role;
    private ObservableList<String> roles = FXCollections.observableArrayList("Teacher", "Student");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!url.toString().contains("hello-view"))
            role.setItems(roles);
    }

    @FXML
    private VBox fence;

    @FXML
    public void doLogin(){
        Credentials cred = new Credentials();
        try {
            cred.setUsername(u_name.getText());
            cred.setPassword(p_word.getText());
            if(cred.auth()){
                System.out.println("This is an authorized user!");
                //homeRedirect(cred.getPacket());
            }else {
                warnUser.setText("Invalid Password");
            }
        }catch(UnHashableException | Exception e){
            warnUser.setText("Invalid Username or Password!");
        }
    }

    @FXML
    public void doSignUp(){
        String auth_uuid;

        ArrayList<String> roles = new ArrayList<String>(Arrays.asList("Teacher", "Student"));
        int r = roles.indexOf(this.role.getValue());
        //needs to be passed frame 2 frame
        Account u = new Account(null,this.u_name.getText(),this.fname.getText(),this.lname.getText(),this.dob.getValue().toString(), this.email.getText(), r );


        try {
            Credentials credit = new Credentials(this.u_name.getText(), this.p_word.getText());
            auth_uuid = credit.makeUser(u);
            u.setUid(UUID.fromString(auth_uuid));

            System.out.println("You have now signed up");
            //homeRedirect(u);
        } catch (UnHashableException | Exception e){
            this.warnUser.setText("That's a funky password, try again...");
            return;
        }
        if(auth_uuid == null) {
            this.warnUser.setText("Something went wonky, sorry!");
            return;
        }
    }

    @FXML
    public void signUpRedirect() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage)u_name.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loginRedirect() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage)u_name.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("hello-view.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
