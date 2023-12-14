package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.EmployeeDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/*
 * @desc Implementation of the EmployeeDaoInterface providing data access operations for the Employee entity.
 */
public class EmployeeDaoImplementation implements EmployeeDaoInterface {
    // Collection to store all Employee objects locally
    public  ArrayList<Employee> allEmployees;

    public EmployeeDaoImplementation() throws SQLException, IOException {
        allEmployees = new ArrayList<>();
        this.allEmployees = getAllEmployees();
    }


    /*
     * @desc :  Inserts a new Employee entry into the data store.
     * @param employee The Employee object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewEmployee(Employee employee) {
        // Extracting attributes from the Employee object
        String employeeId = employee.getEmployeeId();
        String employeeName = employee.getEmployeeName();
        String employeeGender = employee.getEmployeeGender();
        String employeePhoneNo = employee.getEmployeePhoneNo();
        String companyId = employee.getCompanyId();

        // SQL query to insert a new Employee
        String sqlQuery = String.format("INSERT INTO employee VALUES ('%s' , '%s','%s' , '%s','%s');",
                employeeId, employeeName, employeePhoneNo, employeeGender, companyId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allEmployees.add(employee);
        }
    }

    /*
     * @desc :  Retrieves an Employee from the local collection by ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The Employee object matching the given ID.
     */
    public Employee getEmployeeByIdFromLocal(String employeeId) {
        return allEmployees.stream().filter(employee -> employee.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
    }

    /*
     * @desc :  Updates Employee details locally.
     * @param providedEmployee The Employee object with updated details.
     * @return : void
     */
    public void updateEmployeeDetailsLocally(Employee providedEmployee) {
        allEmployees.stream()
                .filter(employee -> employee.getEmployeeId().equals(providedEmployee.getEmployeeId()))
                .forEach(company -> company.setEmployee(providedEmployee));
    }

    /*
     * @desc :  Updates the Employee information identified by the Employee ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param employee The updated Employee object.
     * @return : void
     */
    @Override
    public void updateEmployeeById(String employeeId, Employee employee) {
        Employee oldEmployee = getEmployeeByIdFromLocal(employeeId);
        if (oldEmployee == null) {
            System.out.println("Employee is not there!!");
            insertNewEmployee(employee);
        } else {
            // SQL query to update Employee information
            String sqlQuery = String.format("UPDATE employee " +
                            "SET " +
                            "employeeName='%s',employeePhoneNo='%s',employeeGender='%s',companyId='%s' WHERE employeeId='%s'",
                    employee.getEmployeeName(), employee.getEmployeePhoneNo(), employee.getEmployeeGender(), employee.getCompanyId(), employee.getEmployeeId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updateEmployeeDetailsLocally(employee);
            }
        }
    }

    /*
     * @desc :  Deletes an Employee locally by ID.
     * @param employeeId The unique identifier of the Employee to be deleted.
     * @return : void
     */
    public void deleteCompanyByIdLocally(String employeeId) {
        allEmployees.removeIf(employee -> employee.getEmployeeId().equals(employeeId));
    }

    /*
     * @desc :  Deletes the Employee entry identified by the Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee to be deleted.
     * @return : void
     */
    @Override
    public void deleteEmployeeById(String employeeId) {
        // SQL query to delete Employee entry
        String sqlQuery = String.format("DELETE FROM employee WHERE employeeId='%s'", employeeId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteCompanyByIdLocally(employeeId);
        }
    }

    /*
     * @desc :  Processes the ResultSet and returns a list of Employee models.
     * @param resultSet The ResultSet obtained from a database query.
     * @return An ArrayList containing Employee objects.
     */
    public ArrayList<Employee> processResultSetAndReturnEmployeeModels(ResultSet resultSet) {
        ArrayList<Employee> resultData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                // Extracting attributes from ResultSet
                String retrievedEmployeeId = resultSet.getString("employeeId");
                String retrievedEmployeeName = resultSet.getString("employeeName");
                String retrievedEmployeePhoneNo = resultSet.getString("employeePhoneNo");
                String retrievedEmployeeGender = resultSet.getString("employeeGender");
                String retrievedCompanyId = resultSet.getString("companyId");
                Employee retrievedEmployee = new Employee(retrievedEmployeeId, retrievedEmployeeName, retrievedEmployeePhoneNo, retrievedEmployeeGender, retrievedCompanyId);
                resultData.add(retrievedEmployee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc :  Finds and retrieves the Employee information by Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee.
     * @return The Employee object matching the given ID.
     */
    @Override
    public Employee findEmployeeById(String employeeId)  {
        ArrayList<Employee> allFetchedEmployees = new ArrayList<>();
        // SQL query to retrieve Employee by ID
        String sqlQuery = String.format("SELECT * FROM employee WHERE employeeId='%s';", employeeId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allFetchedEmployees = processResultSetAndReturnEmployeeModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return allFetchedEmployees.stream().findFirst().orElse(null);
    }

    /*
     * @desc :  Retrieves all Employees from a specific company by Company ID.
     * @param companyId The unique identifier of the Company.
     * @return An ArrayList containing Employee objects associated with the given Company ID.
     */
    @Override
    public ArrayList<Employee> getAllEmployeesFromACompanyByCompanyId(String companyId) {
        ArrayList<Employee> listOfEmployees = new ArrayList<>();
        // SQL query to retrieve all Employees from a specific company
        String sqlQuery = String.format("SELECT * FROM employee WHERE companyId='%s';", companyId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            listOfEmployees = processResultSetAndReturnEmployeeModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfEmployees;
    }

    /*
     * @desc :  Maps all Employees by Company ID.
     * @params : no params
     * @return A Map where Company IDs are keys and corresponding lists of Employees are values.
     */
    public Map<String, List<Employee>> mapAllEmployeesByCompanyId() {
        return allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyId, Collectors.toList()));
    }

    /*
     * @desc Retrieves all Employees from the data store.
     * @params : no params
     * @return An ArrayList containing all Employee objects.
     */
    @Override
    public ArrayList<Employee> getAllEmployees() {
        // SQL query to retrieve all Employees
        String sqlQuery = "SELECT * FROM employee;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allEmployees = processResultSetAndReturnEmployeeModels(resultSet);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }

    public ArrayList<Employee> getAllTheEmployeesByQuery(String sqlQuery){

        ArrayList<Employee> allDepartmentsFetchedById = new ArrayList<>();
        // SQL query to retrieve all Departments by Employee ID
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allDepartmentsFetchedById = processResultSetAndReturnEmployeeModels(resultSet);
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return allDepartmentsFetchedById;
    }
}
