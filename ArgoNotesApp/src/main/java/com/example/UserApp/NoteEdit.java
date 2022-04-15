package com.example.UserApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    @FXML private TextField title;
    @FXML private TextArea content;
    private NoteCard N;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        title.prefWidthProperty().bind( this.EditBox.widthProperty().subtract(20));
        content.prefWidthProperty().bind( this.EditBox.widthProperty().subtract(20));
        content.setWrapText(true);
        EditBox.setPadding(new Insets(10,10,10,10));
        title.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                N.setTitle(title.getText());
            }
        });
        content.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                N.setContent(content.getText());
            }
        });
    }

    public void stitch(NoteCard n){
        this.N = n;
        title.setText(n.getTitle());
        content.setText(n.getContent());
    }
    public void closeEdit(){
        if(content.getText().length() == 0 && (title.getText().equals("Untitled") || title.getText().length() == 0)){
            this.N.remove();
        }
        else{
            this.N.persist();
            //Do Save ops here
        }
        ((StackPane) NoteStackParent.getParent()).getChildren().remove(NoteStackParent);
    }
}
