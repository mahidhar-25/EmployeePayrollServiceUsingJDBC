package com.bridgelabz.employeepayrollservice;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class DatabaseConnectTest {

    /*
    @desc : test case is checking whenever we call get mysql connection method
     it should not return , it should return connection
     */
    @Test
    public void getMysqlConnectionItShouldNotReturnNull() {
        assertNotNull(DatabaseConnect.getMysqlConnection());
    }
}