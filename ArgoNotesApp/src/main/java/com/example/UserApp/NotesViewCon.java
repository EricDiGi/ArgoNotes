package com.example.UserApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotesViewCon implements Initializable {
    private String u_id;
    @FXML
    private VBox anchor;
    @FXML
    private TilePane tiles;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        tiles.getChildren().add(new NoteCard("Untitled", "This is some preview text so awesome! Please look at me"));
    }

    public void setU(String u) throws IOException {
        this.u_id = u;
        HttpGet post = new HttpGet("http://localhost:8080/mynotes");
        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);
        String resp = EntityUtils.toString(response.getEntity());
    }

    public void logoutUser() throws IOException {
        HttpPost post = new HttpPost("http://localhost:8080/logout");
        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);

        loginRedirect();
    }

    @FXML
    public void loginRedirect() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage)anchor.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("hello-view.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void makeNote() throws IOException {
        ((StackPane) anchor.getParent()).getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("note-edit.fxml")));
    }
}
