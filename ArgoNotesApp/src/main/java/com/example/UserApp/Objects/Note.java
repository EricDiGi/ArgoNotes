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
    private Boolean is_collab;
    private UUID note_id;


    public Note(){
        this.note_id = UUID.randomUUID();
        this.is_collab = Boolean.FALSE;
        this.title = "Untitled";
        this.updated_at = LocalDateTime.now();
        //this.comments = new ArrayList<Comment>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getColab_id() {
        return colab_id;
    }

    public void setColab_id(UUID colab_id) {
        this.colab_id = colab_id;
    }

    public UUID getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(UUID cluster_id) {
        this.cluster_id = cluster_id;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIs_collab() {
        return is_collab;
    }

    public void setIs_collab(Boolean is_collab) {
        this.is_collab = is_collab;
    }

    public UUID getNote_id() {
        return note_id;
    }

    public void setNote_id(UUID note_id) {
        this.note_id = note_id;
    }
}
