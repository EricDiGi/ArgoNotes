package com.example.UserApp;

import com.example.UserApp.Objects.Note;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class NoteCard extends VBox {

    private Label title_;
    private Text preview_;
    private Note parent_note;

    private final Separator sep = new Separator();
    private final Integer TITLE_LENGTH = 20;
    private final Integer PREVIEW_LENGTH = 70;

    private final Integer NOTE_WIDTH = 175;
    private final Integer NOTE_HEIGHT = 100;


    public NoteCard(String title, String preview){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if(ev.getButton().equals(MouseButton.PRIMARY)){
                    try {
                        editNote();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.setId("ntCrd");
        this.setBackground( new Background( new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY) ));
        this.setWidth(NOTE_WIDTH);
        this.setHeight(NOTE_HEIGHT);
        this.setPadding(new Insets(5,5,5,5));
        this.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 3, 0, 0, 0);");

        this.title_ = new Label(  this.limitTitle(title)  );
        this.title_.setId("title");

        this.preview_ = new Text(  this.limitPreview(preview)  );
        this.preview_.setId("preview");

        this.preview_.setWrappingWidth(NOTE_WIDTH);

        this.getChildren().addAll(this.title_, this.sep, this.preview_);
    }

    public NoteCard(Note n){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if(ev.getButton().equals(MouseButton.PRIMARY)){
                    try {
                        editNote();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.parent_note = n;

        this.setId("ntCrd");
        this.setBackground( new Background( new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY) ));
        this.setWidth(NOTE_WIDTH);
        this.setHeight(NOTE_HEIGHT);
        this.setPadding(new Insets(5,5,5,5));
        this.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 3, 0, 0, 0);");

        this.title_ = new Label(  this.limitTitle(n.getTitle())  );
        this.title_.setId("title");

        this.preview_ = new Text(  this.limitPreview(n.getContent())  );
        this.preview_.setId("preview");

        this.preview_.setWrappingWidth(NOTE_WIDTH);

        this.getChildren().addAll(this.title_, this.sep, this.preview_);
    }

    public void setTitle(String s){
        this.title_.setText(s);
        this.parent_note.setTitle(s);
    }

    public void setContent(String s){
        this.preview_.setText(this.limitPreview(s));
        this.parent_note.setContent(s);
    }

    public String getTitle(){
        return this.parent_note.getTitle();
    }

    public String getContent(){
        return this.parent_note.getContent();
    }

    private String limitTitle(String title) {
        if(title.length() > TITLE_LENGTH)
            return title.substring(0,TITLE_LENGTH);
        return title;
    }

    private String limitPreview(String preview){
        if(preview.length() > PREVIEW_LENGTH)
            return preview.substring(0,PREVIEW_LENGTH-3) + "...";
        return preview;
    }

    public void editNote() throws IOException {
        FXMLLoader page = new FXMLLoader(getClass().getClassLoader().getResource("note-edit.fxml"));
        Parent p_page = page.load();
        NoteEdit ne = page.getController();
        ne.stitch(this);

        ((StackPane) this.getParent().getParent().getParent()).getChildren().add(p_page);
    }

    public void remove(){
        ((TilePane)this.getParent()).getChildren().remove(this);
    }
}
