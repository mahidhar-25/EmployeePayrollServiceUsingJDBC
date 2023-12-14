package com.bridgelabz.employeepayrollservice.database;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;


public class DatabaseConnectTest {

    /*
    @desc : test case is checking whenever we call get mysql connection method
     it should not return , it should return connection
     */
    @Test
    public void getMysqlConnectionItShouldNotReturnNull() throws IOException, SQLException {
        assertNotNull(DatabaseConnect.getMysqlConnection());
    }
}