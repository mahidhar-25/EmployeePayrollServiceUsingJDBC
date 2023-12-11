package com.bridgelabz.employeepayrollservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
@desc : employee payroll service is to read details from employee_payroll table
 */

public class EmployeePayrollService {

/*
@desc : it will read all the details from employee payroll table and returns the count
@params : no params
@return : int - count of employees
 */
    public int readEmployeeDetails(){
        int count=0;
        String sqlQuery = "SELECT * FROM employee_payroll";
        try(Connection connection = DatabaseConnect.getMysqlConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                count++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }


}
