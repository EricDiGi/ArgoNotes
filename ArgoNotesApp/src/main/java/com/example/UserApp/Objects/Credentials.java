package com.example.UserApp.Objects;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Credentials {
    private String u_name;
    private String hashed;

    private void hash(String word) throws UnHashableException {
        try {
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
        }
    }

    public void setUsername(String u_name){
        this.u_name = u_name;
    }
    public void setPassword(String p_word) throws UnHashableException{
        this.hash(p_word);
    }
}