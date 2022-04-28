package com.example.UserApp.Objects;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Note{

    private String title;
    private UUID colab_id;
    private UUID cluster_id;
    private LocalDateTime updated_at;
    private String content;
    //private ArrayList<Comment> comments;
    private Boolean is_collab;
    private UUID note_id;
    private UUID uid;


    public Note(String uid){
        this.uid = UUID.fromString(uid);
        this.note_id = UUID.randomUUID();
        this.is_collab = Boolean.FALSE;
        this.title = "Untitled";
        this.updated_at = LocalDateTime.now();
        this.content = "";
        //this.comments = new ArrayList<Comment>();
    }

    public Note(String uid, String nid) throws IOException {
        this.note_id = UUID.fromString(nid);
        this.uid = UUID.fromString(uid);

        HttpPost post = new HttpPost("http://localhost:8080/collectNote");
        List<NameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("uid",this.uid.toString()));
        urlParams.add(new BasicNameValuePair("nid",this.note_id.toString()));
        post.setEntity(new UrlEncodedFormEntity(urlParams));

        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);
        String note_raw = EntityUtils.toString(response.getEntity());
        JSONObject note = new JSONObject(note_raw);

        this.is_collab = (Integer)note.get("is_collab") == 1;
        this.title = this.unescape((String)note.get("title"));
        String t = ((String)note.get("updated_at")).replace("T", " ").replace("Z","");
        this.updated_at = Timestamp.valueOf(t).toLocalDateTime();
        this.content = this.unescape((String)note.get("contents"));
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

    private String escape(String in){
        in = in.replaceAll("'", "\\\\'");
        in = in.replaceAll("\"","\\\\\"");
        return in;
    }

    private String unescape(String in){
        in = in.replaceAll("\\\\'", "'");
        in = in.replaceAll("\\\\\"","\"");
        return in;
    }

    public void persist() throws IOException{
        System.out.println("Gets to here 1");
        HttpPost post = new HttpPost("http://localhost:8080/saveNote");
        List<NameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("uid",this.uid.toString()));
        urlParams.add(new BasicNameValuePair("nid",this.note_id.toString()));
        UUID clust = this.cluster_id == null ? UUID.randomUUID() : this.cluster_id;
        urlParams.add(new BasicNameValuePair("cid", clust.toString()));
        urlParams.add(new BasicNameValuePair("title", this.escape(this.title)));
        urlParams.add(new BasicNameValuePair("content",this.escape(this.content)));
        urlParams.add(new BasicNameValuePair("is_collab", Integer.toString(this.is_collab?1:0)));
        post.setEntity(new UrlEncodedFormEntity(urlParams));

        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);

        EntityUtils.toString(response.getEntity());
        //httpCli.close();
        System.out.println("Should not be hanging");
    }
}
