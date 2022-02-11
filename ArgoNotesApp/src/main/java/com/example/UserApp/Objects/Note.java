package com.example.UserApp.Objects;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    private String title;
    private UUID colab_id;
    private UUID cluster_id;
    private LocalDateTime updated_at;
    private String content;
    //private ArrayList<Comment> comments;
    private Boolean is_colab;
    private UUID note_id;


    public Note(){
        this.note_id = UUID.randomUUID();
        this.is_colab = Boolean.FALSE;
        this.title = "Untitled";
        this.updated_at = LocalDateTime.now();
        //this.comments = new ArrayList<Comment>();
    }
}
