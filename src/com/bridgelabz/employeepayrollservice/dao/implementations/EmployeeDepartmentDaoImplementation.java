
package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.EmployeeDepartmentDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Department;
import com.bridgelabz.employeepayrollservice.models.Employee;
import com.bridgelabz.employeepayrollservice.models.EmployeeDepartment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * @desc Implementation of the EmployeeDepartmentDaoInterface providing data access operations for the EmployeeDepartment entity.
 */
public class EmployeeDepartmentDaoImplementation implements EmployeeDepartmentDaoInterface {

    // Collection to store all EmployeeDepartment objects locally
    public  ArrayList<EmployeeDepartment> allEmployeeDepartments;

    public EmployeeDepartmentDaoImplementation() throws SQLException, IOException {
        allEmployeeDepartments = new ArrayList<>();
        this.allEmployeeDepartments = getAllEmployeeDepartments();
    }

    /*
     * @desc :  Retrieves an EmployeeDepartment from the local collection by Employee ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The EmployeeDepartment object matching the given ID.
     */
    public EmployeeDepartment getEmployeeDepartmentByEmployeeIdFromLocal(String employeeId) {
        return allEmployeeDepartments.stream().filter(EmployeeDepartment -> EmployeeDepartment.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
    }

    /*
     * @desc :  Updates EmployeeDepartment details locally.
     * @param providedEmployeeDepartment The EmployeeDepartment object with updated details.
     * @return : void
     */
    public void updateEmployeeDepartmentDetailsLocally(EmployeeDepartment providedEmployeeDepartment) {
        allEmployeeDepartments.stream()
                .filter(EmployeeDepartment -> EmployeeDepartment.getEmployeeId().equals(providedEmployeeDepartment.getEmployeeId()))
                .forEach(EmployeeDepartment -> EmployeeDepartment.setEmployeeDepartment(providedEmployeeDepartment));
    }

    /*
     * @desc : Inserts a new EmployeeDepartment entry into the data store.
     * @param employeeDepartment The EmployeeDepartment object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewEmployeeInDepartment(EmployeeDepartment employeeDepartment) {
        String employeeId = employeeDepartment.getEmployeeId();
        String departmentId = employeeDepartment.getDepartmentId();
        // SQL query to insert a new EmployeeDepartment
        String sqlQuery = String.format("INSERT INTO employee_department VALUES ('%s' , '%s');", employeeId, departmentId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allEmployeeDepartments.add(employeeDepartment);
        }
    }

    /*
     * @desc :  Updates the EmployeeDepartment information identified by the Employee ID in the data store.
     * @param employeeId         The unique identifier of the Employee for the update.
     * @param employeeDepartment The updated EmployeeDepartment object.
     * @return : void
     */
    @Override
    public void updateEmployeeDepartmentByEmployeeId(String employeeId, EmployeeDepartment employeeDepartment) {
        EmployeeDepartment oldEmployeeDepartment = getEmployeeDepartmentByEmployeeIdFromLocal(employeeId);
        if (oldEmployeeDepartment == null) {
            System.out.println("EmployeeDepartment is not there!!");
            insertNewEmployeeInDepartment(employeeDepartment);
        } else {
            // SQL query to update EmployeeDepartment information
            String sqlQuery = String.format("UPDATE employee_department " +
                    "SET departmentId='%s'" +
                    "WHERE employeeId='%s'", employeeDepartment.getDepartmentId(), employeeDepartment.getEmployeeId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updateEmployeeDepartmentDetailsLocally(employeeDepartment);
            }
        }
    }

    /*
     * @desc :  Deletes a Department locally by ID.
     * @param departmentId The unique identifier of the Department to be deleted.
     * @return : void
     */
    public void deleteDepartmentByIdLocally(String employeeId) {
        allEmployeeDepartments.removeIf(employeeDepartment -> employeeDepartment.getEmployeeId().equals(employeeId));
    }

    /*
     * @desc :  Deletes the EmployeeDepartment entry identified by the Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee to be deleted.
     * @return : void
     */
    @Override
    public void deleteEmployeeDepartmentByEmployeeId(String employeeId) {
        // SQL query to delete EmployeeDepartment entry
        String sqlQuery = String.format("DELETE FROM employee_department WHERE employeeId='%s'", employeeId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteDepartmentByIdLocally(employeeId);
        }
    }

    /*
     * @desc Deletes the EmployeeDepartment entry identified by the Employee ID and Department ID from the data store.
     * @param employeeId   The unique identifier of the Employee.
     * @param departmentId The unique identifier of the Department.
     * @return : void
     */
    @Override
    public void deleteEmployeeDepartmentByEmployeeIdAndDepartmentId(String employeeId, String departmentId) {
        // SQL query to delete EmployeeDepartment entry
        String sqlQuery = String.format("DELETE FROM employee_department WHERE employeeId='%s' AND departmentId='%s'", employeeId, departmentId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteDepartmentByIdLocally(employeeId);
        }
    }

    /*
     * @desc Processes the ResultSet and returns a list of EmployeeDepartment models.
     * @param resultSet The ResultSet obtained from a database query.
     * @return An ArrayList containing EmployeeDepartment objects.
     */
    public ArrayList<EmployeeDepartment> processResultSetAndReturnEmployeeDepartmentModels(ResultSet resultSet) {
        ArrayList<EmployeeDepartment> resultData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                // Extracting attributes from ResultSet
                String retrievedDepartmentId = resultSet.getString("departmentId");
                String retrievedEmployeeId = resultSet.getString("employeeId");
                EmployeeDepartment employeeDepartment = new EmployeeDepartment(retrievedEmployeeId, retrievedDepartmentId);
                resultData.add(employeeDepartment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc Finds and retrieves all Departments associated with the given Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee.
     * @return An ArrayList containing Department objects associated with the given Employee ID.
     */
    @Override
    public ArrayList<Department> findAllDepartmentsByEmployeeId(String employeeId) throws SQLException, IOException {
        String sqlQuery = String.format("SELECT * FROM department WHERE departmentId IN(SELECT departmentId FROM employee_department WHERE employeeId='%s');" ,employeeId);
       return new DepartmentDaoImplementation().getAllTheDepartmentByQuery(sqlQuery);
    }

    /*
     * @desc Finds and retrieves all Employees associated with the given Department ID from the data store.
     * @param departmentId The unique identifier of the Department.
     * @return An ArrayList containing Employee objects associated with the given Department ID.
     */
    @Override
    public ArrayList<Employee> findAllEmployeesByDepartmentId(String departmentId) throws SQLException, IOException {
        String sqlQuery = String.format("SELECT * FROM employee WHERE employeeId IN(SELECT employeeId FROM employee_department WHERE departmentId='%s');" , departmentId);
        return new EmployeeDaoImplementation().getAllTheEmployeesByQuery(sqlQuery);
    }

    /*
     * @desc Retrieves all EmployeeDepartments from the data store.
     * @params : no params
     * @return An ArrayList containing all EmployeeDepartment objects.
     */
    @Override
    public ArrayList<EmployeeDepartment> getAllEmployeeDepartments()  {
        // SQL query to retrieve all EmployeeDepartments
        String sqlQuery = "SELECT * FROM employee_department;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allEmployeeDepartments = processResultSetAndReturnEmployeeDepartmentModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployeeDepartments;
    }
}
