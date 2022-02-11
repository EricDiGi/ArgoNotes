package com.service.T2A;

import com.example.UserApp.Objects.Account;
import com.example.UserApp.Objects.Cluster;
import com.example.UserApp.Objects.Credentials;
import com.example.UserApp.Objects.Note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Class.forName;

public class DAO {
    private Connection conn;
    private Statement stmt;

    private Class[] classes;
    private String[] class_names;

    public DAO() throws SQLException, ClassNotFoundException {
        this.classes = new Class[]{Account.class, Cluster.class, Credentials.class, Note.class};
        this.class_names = new String[]{"ACCOUNT","CLUSTER","CREDENTIALS","NOTE"};

        forName("com.mysql.cj.jdbc.Driver");
        String MySQLURL="jdbc:mysql://localhost:3306/argonotes?user=root&password=APIadmin";
        this.conn = DriverManager.getConnection(MySQLURL);
        this.stmt = this.conn.createStatement();
    }

    public void writeServer(String Req, String json_){

    }


}
