package com.service.T2A;

import junit.framework.TestCase;

import java.sql.SQLException;

public class testService extends TestCase {
    public void testDAO() throws DatabaseDisconnect, ClassNotFoundException {
        try {
            DAO d = new DAO();
        }catch(SQLException e){
            throw new DatabaseDisconnect("Could not establish connection");
        }
    }
}
