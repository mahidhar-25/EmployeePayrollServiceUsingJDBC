package com.bridgelabz.employeepayrollservice;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

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
    public DatabaseConnect() {
    }

    /*
    @desc : it will give mysql connection by checking if already connection exist
    @params : no params
    @return : Connection - mysql connection
     */
    public static Connection getMysqlConnection(){
        if(mysqlConnection == null){
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
    public static Connection connectToNewMysqlDatabase(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "mahidhar25";
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(jdbcUrl , username , password);
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

    public static void main(String[] args) {

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
