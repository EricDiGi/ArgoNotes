package com.example.UserApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        EditBox.setPadding(new Insets(10,10,10,10));
    }
}
