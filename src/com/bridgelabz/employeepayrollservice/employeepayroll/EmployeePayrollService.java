package com.bridgelabz.employeepayrollservice.employeepayroll;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/*
@desc : employee payroll service is to read details from employee_payroll table
 */

public class EmployeePayrollService {

    private PreparedStatement employeePayrollDataStatementByName;
    public static ArrayList<EmployeePayrollData> employeePayrollDataArrayList;


    public EmployeePayrollService(){
    }
/*
@desc : it will read all the details from employee payroll table and returns the count
@params : no params
@return : int - count of employee
 */
    public ArrayList<EmployeePayrollData> readEmployeeDetails(){
        String sqlQuery = "SELECT * FROM employee_payroll";
        employeePayrollDataArrayList = this.runTheSqlQueryAndGiveTheResult(sqlQuery);

        return employeePayrollDataArrayList;
    }

    /*
    @desc : updateEmployeeSalaryInDatabase it is a prepared statement for updating the salary by name
    @params : String - employeeName
              double - employeeSalary
    @return : void
     */

    public void updateEmployeeSalaryInDatabase(String employeeName , double employeeSalary){
        try(Connection connection = DatabaseConnect.getMysqlConnection()){
            String sqlQuery = String.format("UPDATE employee_payroll SET employeeSalary=%.2f WHERE employeeName='%s' ;" , employeeSalary , employeeName);
            Statement statement = connection.createStatement();
            int result =   statement.executeUpdate(sqlQuery);
            if(result > 0){
                EmployeePayrollData employeePayrollData = getEmployeePayrollDataByName(employeeName);
                if(employeePayrollData != null){
                    employeePayrollData.setEmployeeSalary(employeeSalary);
                }
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
 /*
    @desc : updateEmployeeSalaryInDatabase it is a prepared statement for updating the salary by name
    @params : String - employeeName
              double - employeeSalary
    @return : void
     */

    public void updateEmployeeSalaryInDatabaseUsingPreparedStatement(String employeeName , double employeeSalary){
        try(Connection connection = DatabaseConnect.getMysqlConnection()){
            String sqlQuery = "UPDATE employee_payroll SET employeeSalary=? WHERE employeeName=? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDouble(1 , employeeSalary);
            statement.setString(2 , employeeName);
              int result =      statement.executeUpdate();
            if(result > 0){
                EmployeePayrollData employeePayrollData = getEmployeePayrollDataByName(employeeName);
                if(employeePayrollData != null){
                    employeePayrollData.setEmployeeSalary(employeeSalary);
                }
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
    /*
        @desc : getEmployeePayrollDataFromDatabaseByName it has all the data from database side , to check sync we will store here
        @params : String - name
        @return : ArrayList<EmployeePayrollData> - stack of EmployeePayrollData

         */
    public ArrayList<EmployeePayrollData> getEmployeePayrollDataFromDatabaseByName(String name) {
        ArrayList<EmployeePayrollData> employeePayrollData = null;
        try (Connection connection = DatabaseConnect.getMysqlConnection()) {
            if (employeePayrollDataStatementByName == null) {
                this.preparedStatementToGetEmployeeByName(connection);
            }

            employeePayrollDataStatementByName.setString(1, name);

            try (ResultSet resultSet = employeePayrollDataStatementByName.executeQuery()) {
                employeePayrollData = this.getEmployeePayrollData(resultSet);
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return employeePayrollData;
    }

    /*
    @desc : preparedStatementToGetEmployeeByName it is a prepared statement for getting all the details by name
    @params : Connection - connection
    @return : void
     */
    public void preparedStatementToGetEmployeeByName(Connection connection) throws IOException {
        try {
            String sqlQuery = "SELECT * FROM employee_payroll WHERE employeeName = ?";
            employeePayrollDataStatementByName = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @desc : preparedStatementToGetEmployeeByName it is a prepared statement for getting all the details by name
    @params : Connection - connection
    @return : void
     */
    public void preparedStatementToGetEmployeesByDate(Connection connection) throws IOException {
        try {
            String sqlQuery = "SELECT * FROM employee_payroll WHERE startDate BETWEEN CAST(? AS DATE) AND ?";
            employeePayrollDataStatementByName = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @desc : just iterate through all the columns of the table once we got the result set
    @params : Resultset - resultset
    @return : ArrayList<EmployeePayrollData> - array of rows
     */
    private ArrayList<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) throws SQLException {
        ArrayList<EmployeePayrollData> resultData = new ArrayList<>();
        while(resultSet.next()){
            int employeeId = Integer.parseInt(resultSet.getString("employeeId"));
            String employeeName = resultSet.getString("employeeName");
            double employeeSalary = resultSet.getDouble("employeeSalary");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            EmployeePayrollData newEmployeeEntry = new EmployeePayrollData(employeeId , employeeName , employeeSalary , startDate);

            resultData.add(newEmployeeEntry);
        }
        return resultData;
    }

    /*
    @desc : getEmployeePayrollDataByName it has all the data from local side , to check sync we will store here
    @params : String - name
    @return : EmployeePayrollData

     */
    public EmployeePayrollData getEmployeePayrollDataByName(String name){
        if(employeePayrollDataArrayList == null){
            readEmployeeDetails();
        }
        return employeePayrollDataArrayList.stream()
                .filter(employee -> employee.getEmployeeName().equals(name))
                .findFirst().orElse(null);
    }

    /*
    @desc : checkEmployeePayrollIsInSyncWithDatabase it checks the whether our data is sync with the database or not
    @params : String - name
    @return : boolean
     */
    public boolean checkEmployeePayrollIsInSyncWithDatabase(String name){
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataByName(name);
        return employeePayrollData.equals(getEmployeePayrollDataFromDatabaseByName(name).getFirst());

    }

    /*
    @desc : getEmployeeDetailsWhoJoinedBetweenDates it runs the queryu to findout who joined between dates
    @params : LocalDate - startDate
              LocalDate - endDate
    @return : ArrayList<EmployeePayrollData>
     */

    public ArrayList<EmployeePayrollData> getEmployeeDetailsWhoJoinedBetweenDates(LocalDate startDate, LocalDate endDate) {
        String sqlQuery  = String.format("SELECT * FROM employee_payroll WHERE startDate BETWEEN '%s' AND '%s';" ,
                 Date.valueOf(startDate) , Date.valueOf(endDate));

        return this.runTheSqlQueryAndGiveTheResult(sqlQuery);
    }

    /*
    @desc : it runs the query given and return the result
    @params : String - sqlQuery
    @return : ArrayList<EmployeePayrollData>
     */
    private ArrayList<EmployeePayrollData> runTheSqlQueryAndGiveTheResult(String sqlQuery) {
        ArrayList<EmployeePayrollData> employeePayrollDataArrayList1 = null;
        try(Connection connection = DatabaseConnect.getMysqlConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            employeePayrollDataArrayList1 = this.getEmployeePayrollData(resultSet);
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return employeePayrollDataArrayList1;
    }

    /*
    @desc : this method will calculate the avg , min , max salries and group them by gender
    @param : no param
    @return : int - no of rows returned
     */
    public int getEmployeePayrollDetailsGroupByGender() {
        int count=0;
        String sqlQuery = "SELECT \n" +
                "gender ,\n" +
                "COUNT(employeeId) AS count, \n" +
                "SUM(employeeSalary) AS totalSum , \n" +
                "AVG(employeeSalary) AS average , \n" +
                "MAX(employeeSalary) AS maxSalary,\n" +
                "MIN(employeeSalary) AS minSalary \n" +
                "FROM employee_payroll GROUP BY gender;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                count++;
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return count;
    }
}
