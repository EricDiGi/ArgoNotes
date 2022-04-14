package com.example.UserApp;

import com.example.UserApp.Objects.Note;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;




public class NoteEdit implements Initializable {
    @FXML
    private StackPane NoteStackParent;
    @FXML private Screen screen;
    @FXML private VBox EditBox;
    @FXML private Label title;
    @FXML private Label content;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        EditBox.setPadding(new Insets(10,10,10,10));
    }

    public void stitch(Note n){
        title.setText(n.getTitle());
        content.setText(n.getContent());
    }
    public void closeEdit(){
        ((StackPane) NoteStackParent.getParent()).getChildren().remove(NoteStackParent);
    }
}
