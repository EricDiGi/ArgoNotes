package com.example.UserApp;

import com.example.UserApp.Objects.Note;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotesViewCon implements Initializable {
    private String u_id;
    private String note_ids;

    @FXML private VBox anchor;
    @FXML private TilePane tiles;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle){
        Note n = new Note();
        NoteCard nc = new NoteCard(n);
        tiles.getChildren().add(nc);
    }

    public void setAndCollect(String u) throws IOException{
        this.u_id = u;
        HttpPost post = new HttpPost("http://localhost:8080/mynotes");
        List<NameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("uid",this.u_id));
        post.setEntity(new UrlEncodedFormEntity(urlParams));

        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);
        JSONObject note_ids = new JSONObject(EntityUtils.toString(response.getEntity()));
        JSONArray jarr = note_ids.getJSONArray("notes");
        for(Object id: jarr) {
            Note n = new Note(this.u_id, (String)id);
            tiles.getChildren().add(new NoteCard(n));
        }
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
        Note n = new Note();
        NoteCard nc = new NoteCard(n);
        tiles.getChildren().add(nc);
        nc.editNote();
        //((StackPane) anchor.getParent()).getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("note-edit.fxml")));
    }
}
