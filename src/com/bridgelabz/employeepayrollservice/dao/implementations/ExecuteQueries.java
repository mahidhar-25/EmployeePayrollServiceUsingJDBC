package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;

import java.io.IOException;
import java.sql.Connection;
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


}
