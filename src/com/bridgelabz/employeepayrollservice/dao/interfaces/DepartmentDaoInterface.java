package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Department;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * @desc :  This interface defines the data access operations for the Department entity.
 */
public interface DepartmentDaoInterface {

    /*
     * @desc :  Inserts a new Department into the data store.
     * @param department The Department object to be inserted.
     * @return : void
     */
    void insertNewDepartment(Department department);

    /*
     * @desc :  Updates a Department identified by its unique ID in the data store.
     * @param departmentId The unique identifier of the Department to be updated.
     * @param department The updated Department object.
     * @return : void
     */
    void updateDepartmentById(String departmentId, Department department);

    /*
     * @desc :  Deletes a Department from the data store based on its unique ID.
     * @param departmentId The unique identifier of the Department to be deleted.
     * @return : void
     */
    void deleteDepartmentById(String departmentId);

    /*
     * @desc :  Finds and retrieves a Department from the data store based on its unique ID.
     * @param departmentId The unique identifier of the Department to be retrieved.
     * @return The Department object found, or null if not found.
     */
    Department findDepartmentById(String departmentId) throws SQLException;

    /*
     * @desc :  Retrieves all Department objects from the data store.
     * @params : no params
     * @return An ArrayList containing all Department objects.
     */
    ArrayList<Department> getAllDepartments() throws SQLException, IOException;
}

