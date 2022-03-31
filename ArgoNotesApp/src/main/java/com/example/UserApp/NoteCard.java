package com.example.UserApp;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NoteCard extends VBox {

    private Label title_;
    private Text preview_;

    private final Separator sep = new Separator();
    private final Integer TITLE_LENGTH = 20;
    private final Integer PREVIEW_LENGTH = 70;

    private final Integer NOTE_WIDTH = 175;
    private final Integer NOTE_HEIGHT = 100;


    NoteCard(String title, String preview){
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


}
