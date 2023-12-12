package com.bridgelabz.employeepayrollservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

/*
This class is used for database connection it will return a new connection for the first or already existing connection
 */
public class DatabaseConnect {
    private static Connection mysqlConnection;

    /*
    @desc : default constructor
    @params : no params
    @return : no return
     */
    private DatabaseConnect() throws IOException, SQLException {
        mysqlConnection = getMysqlConnection();
    }

    /*
    @desc : it will give mysql connection by checking if already connection exist
    @params : no params
    @return : Connection - mysql connection
     */
    public static Connection getMysqlConnection() throws IOException, SQLException {
        if(mysqlConnection == null || mysqlConnection.isClosed()){
            return connectToNewMysqlDatabase();
        }else{
            System.out.println("Already connected");
            return mysqlConnection;
        }
    }

    /*
    @desc : connectToNewMysqlDatabase it will create a new connection
    @params : no params
    @return : Connection - new mysql connection
     */
    public static Connection connectToNewMysqlDatabase() throws IOException {
        FileInputStream propertiesFile = new FileInputStream(new File("DatabaseConfig.properties"));
        Properties prop = new Properties();
        prop.load(propertiesFile);
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(prop.getProperty("DATABASE_URL") , prop.getProperty("USERNAME") , prop.getProperty("PASSWORD"));
            System.out.println("connection : " + connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mysqlConnection = connection;
        return connection;
    }

    /*
    @desc : to list all the drivers
    @params : no params
    @return : no return
     */
    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println("  " + driverClass.getClass().getName());
        }
    }

    public static void main(String[] args) throws IOException, SQLException {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("connection : " + getMysqlConnection());
        listDrivers();
    }
}
