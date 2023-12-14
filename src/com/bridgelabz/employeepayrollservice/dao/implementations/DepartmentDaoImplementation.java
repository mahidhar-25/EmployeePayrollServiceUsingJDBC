package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.DepartmentDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Department;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * @desc Implementation of the DepartmentDaoInterface providing data access operations for the Department entity.
 */
public class DepartmentDaoImplementation implements DepartmentDaoInterface {

    // Collection to store all Department objects locally
    public ArrayList<Department> allDepartments;

    public DepartmentDaoImplementation() throws SQLException, IOException {
        allDepartments = new ArrayList<>();
        this.allDepartments = getAllDepartments();
    }

    /*
     * @desc Inserts a new Department entry into the data store.
     * @param department The Department object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewDepartment(Department department) {
        String departmentId = department.getDepartmentId();
        String departmentName = department.getDepartmentName();
        // SQL query to insert a new Department
        String sqlQuery = String.format("INSERT INTO department VALUES ('%s' , '%s');", departmentId, departmentName);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allDepartments.add(department);
        }
    }

    /*
     * @desc Updates the Department information identified by the Department ID in the data store.
     * @param departmentId The unique identifier of the Department for the update.
     * @param department   The updated Department object.
     * @return : void
     */
    @Override
    public void updateDepartmentById(String departmentId, Department department) {
        Department oldDepartment = getDepartmentByIdFromLocal(departmentId);
        if (oldDepartment == null) {
            System.out.println("Department is not there!!");
            insertNewDepartment(department);
        } else {
            // SQL query to update Department information
            String sqlQuery = String.format("UPDATE department " +
                    "SET departmentName='%s'" +
                    "WHERE departmentId='%s'", department.getDepartmentName(), department.getDepartmentId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updateDepartmentDetailsLocally(department);
            }
        }
    }

    /*
     * @desc Retrieves a Department from the local collection by Department ID.
     * @param departmentId The unique identifier of the Department.
     * @return The Department object matching the given ID.
     */
    public Department getDepartmentByIdFromLocal(String departmentId) {
        return allDepartments.stream().filter(department -> department.getDepartmentId().equals(departmentId)).findFirst().orElse(null);
    }

    /*
     * @desc Updates Department details locally.
     * @param providedDepartment The Department object with updated details.
     * @return : void
     */
    public void updateDepartmentDetailsLocally(Department providedDepartment) {
        allDepartments.stream()
                .filter(department -> department.getDepartmentId().equals(providedDepartment.getDepartmentId()))
                .forEach(department -> department.setDepartment(providedDepartment));
    }

    /*
     * @desc Deletes a Department locally by ID.
     * @param departmentId The unique identifier of the Department to be deleted.
     * @return : void
     */
    public void deleteDepartmentByIdLocally(String departmentId) {
        allDepartments.removeIf(department -> department.getDepartmentId().equals(departmentId));
    }

    /*
     * @desc Deletes the Department entry identified by the Department ID from the data store.
     * @param departmentId The unique identifier of the Department to be deleted.
     * @return : void
     */
    @Override
    public void deleteDepartmentById(String departmentId) {
        // SQL query to delete Department entry
        String sqlQuery = String.format("DELETE FROM department WHERE departmentId='%s'", departmentId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteDepartmentByIdLocally(departmentId);
        }
    }

    /*
     * @desc Finds and retrieves a Department by its ID from the data store.
     * @param departmentId The unique identifier of the Department.
     * @return The Department object matching the given ID.
     */
    @Override
    public Department findDepartmentById(String departmentId)  {
        ArrayList<Department> allFetchedCompanies = new ArrayList<>();
        // SQL query to retrieve Department by ID
        String sqlQuery = String.format("SELECT * FROM department WHERE departmentId='%s';", departmentId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allFetchedCompanies = processResultSetAndReturnDepartmentModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return allFetchedCompanies.stream().findFirst().orElse(null);
    }

    /*
     * @desc Processes the ResultSet and returns a list of Department models.
     * @param resultSet The ResultSet obtained from a database query.
     * @return An ArrayList containing Department objects.
     */
    public ArrayList<Department> processResultSetAndReturnDepartmentModels(ResultSet resultSet) {
        ArrayList<Department> resultData = new ArrayList<>();
        try {
            System.out.println(resultSet.toString());
            while (resultSet.next()) {
                System.out.println("processing result set");
                // Extracting attributes from ResultSet
                String departmentId = resultSet.getString("departmentId");
                String departmentName = resultSet.getString("departmentName");
                Department department = new Department(departmentId, departmentName);
                resultData.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc Retrieves all Departments from the data store.
     * @params : no params
     * @return An ArrayList containing all Department objects.
     */
    @Override
    public ArrayList<Department> getAllDepartments() {
        // SQL query to retrieve all Departments
        String sqlQuery = "SELECT * FROM department;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allDepartments = processResultSetAndReturnDepartmentModels(resultSet);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return allDepartments;
    }

    public ArrayList<Department> getAllTheDepartmentByQuery(String sqlQuery){

        ArrayList<Department> allDepartmentsFetchedById = new ArrayList<>();
        // SQL query to retrieve all Departments by Employee ID
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allDepartmentsFetchedById = processResultSetAndReturnDepartmentModels(resultSet);
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return allDepartmentsFetchedById;
    }
}
