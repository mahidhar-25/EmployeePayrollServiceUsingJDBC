package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * @desc : The ExecuteQueries class provides methods for executing SQL queries and retrieving result sets.
 * It encapsulates database interaction through JDBC connections.
 */
public class ExecuteQueries {

    /*
     * @desc : Executes a SQL query that modifies the database (e.g., INSERT, UPDATE, DELETE).
     *
     * @param sqlQuery The SQL query to be executed.
     * @return An integer indicating the number of rows affected in the database.
     *         Returns 0 if no changes were made.
     */
    public static int executeQuery(String sqlQuery) {
        int isDatabaseUpdated = 0;
        try (Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            isDatabaseUpdated = statement.executeUpdate(sqlQuery);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return isDatabaseUpdated;
    }


    public void executeQueryToGetSumOfSalaryGroupByGender(String sqlQuery){
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
               String gender = resultSet.getString("employeeGender");
               Double salarySum = resultSet.getDouble("SUM(payroll.netPay)");
               String companyId = resultSet.getString("companyId");
               String companyName = resultSet.getString("companyName");
               System.out.println("Gender : " + gender + ", salarySum : "+ salarySum + ", companyId : "+companyId+", companyName : "+companyName);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
