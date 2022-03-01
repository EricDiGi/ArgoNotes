package com.example.UserApp.Objects;

import com.service.JSON.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class Credentials {
    private String u_name;
    private String hashed;
    private LoginResponse LR;

    public Credentials(){}

    public Credentials(String u_name, String p_word) throws UnHashableException {
        this.hash(p_word);
        this.u_name = u_name;
    }

    private void hash(String word) throws UnHashableException {
        this.hashed = word;
        return;

        //Do salting

        /*try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] h = md.digest(word.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1,h);
            String hex = no.toString(16);
            while(hex.length() < 32){
                hex = "0" + hex;
            }
            this.hashed = hex;
        }
        catch(NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            throw new UnHashableException();
        }*/
    }

    public void setUsername(String u_name){
        this.u_name = u_name;
    }
    public void setPassword(String p_word) throws UnHashableException{
        this.hash(p_word);
    }

    public Boolean auth() throws Exception{
        HttpPost post = new HttpPost("http://localhost:8080/login");
        List<NameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("alias",this.u_name));
        urlParams.add(new BasicNameValuePair("pword", this.hashed));
        post.setEntity(new UrlEncodedFormEntity(urlParams));

        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);

        String entity = EntityUtils.toString(response.getEntity());
        try {
            JSON<LoginResponse> json = new JSON<>(entity, LoginResponse.class);
            LoginResponse lr = json.fromJSON();
            if(!lr.getUser().isEmpty()){
                return true;
            }
        } catch(Exception e){
            return false;
        }
        return false;
    }

    public LoginResponse getPacket(){
        return this.LR;
    }

    public String makeUser(Account u) throws Exception {
        HttpPost post = new HttpPost("http://localhost:8080/signup");
        List<NameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("alias",this.u_name));
        urlParams.add(new BasicNameValuePair("first_name", u.getFirst_name()));
        urlParams.add(new BasicNameValuePair("last_name",u.getLast_name()));
        urlParams.add(new BasicNameValuePair("dob", u.getDob()));
        urlParams.add(new BasicNameValuePair("email", u.getEmail()));
        urlParams.add(new BasicNameValuePair("role", u.getRole()+""));
        urlParams.add(new BasicNameValuePair("pword",this.hashed));
        post.setEntity(new UrlEncodedFormEntity(urlParams));

        CloseableHttpClient httpCli = HttpClients.createDefault();
        CloseableHttpResponse response = httpCli.execute(post);

        String entity = EntityUtils.toString(response.getEntity());
        try {
            JSON<SignupResponse> json = new JSON<SignupResponse>(entity, SignupResponse.class);
            SignupResponse sr = json.fromJSON();
            if(!sr.getAuth_id().isEmpty()){
                return sr.getAuth_id();
            }
        } catch(Exception e){
            return null;
        }
        return null;
    }
}