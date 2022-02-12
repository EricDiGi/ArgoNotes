package com.service.T2A;

import com.example.UserApp.Objects.Account;
import com.example.UserApp.Objects.Cluster;
import com.example.UserApp.Objects.Credentials;
import com.example.UserApp.Objects.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.JSON.JSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Class.forName;

public class DAO {
    private Connection conn;
    private Statement stmt;


    public DAO() throws SQLException, ClassNotFoundException {
        forName("com.mysql.cj.jdbc.Driver");
        String MySQLURL="jdbc:mysql://localhost:3306/argonotes?user=root&password=APIadmin";
        this.conn = DriverManager.getConnection(MySQLURL);
        this.stmt = this.conn.createStatement();
    }

    public void writeServer(String Req, String json_) throws JsonProcessingException {
        Object obj; JSON js;
        switch(Req){
            case "/Creds":
                js = new JSON(json_,Credentials.class);
                obj = js.fromJSON();
                break;
            case "/Account":
                js = new JSON(json_, Account.class);
                obj = js.fromJSON();
                break;
            case "/Cluster":
                js = new JSON(json_, Cluster.class);
                obj = js.fromJSON();
            case "/Note":
                js = new JSON(json_, Note.class);
                obj = js.fromJSON();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Req);
        }


    }


}
