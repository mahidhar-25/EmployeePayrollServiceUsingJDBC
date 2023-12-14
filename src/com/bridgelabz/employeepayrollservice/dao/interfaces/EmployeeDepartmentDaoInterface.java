package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Department;
import com.bridgelabz.employeepayrollservice.models.Employee;
import com.bridgelabz.employeepayrollservice.models.EmployeeDepartment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * @desc :  This interface defines the data access operations for the EmployeeDepartment entity.
 */
public interface EmployeeDepartmentDaoInterface {

    /*
     * @desc :  Inserts a new EmployeeDepartment into the data store.
     * @param employeeDepartment The EmployeeDepartment object to be inserted.
     * @return : void
     */
    void insertNewEmployeeInDepartment(EmployeeDepartment employeeDepartment);

    /*
     * @desc :  Updates an EmployeeDepartment identified by the employee's unique ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param employeeDepartment The updated EmployeeDepartment object.
     * @return : void
     */
    void updateEmployeeDepartmentByEmployeeId(String employeeId, EmployeeDepartment employeeDepartment);

    /*
     * @desc :  Deletes an EmployeeDepartment from the data store based on the employee's unique ID.
     * @param employeeId The unique identifier of the EmployeeDepartment to be deleted.
     * @return : void
     */
    void deleteEmployeeDepartmentByEmployeeId(String employeeId);

    /*
     * @desc :  Deletes an EmployeeDepartment from the data store based on both the employee's and department's unique IDs.
     * @param employeeId The unique identifier of the Employee.
     * @param departmentId The unique identifier of the Department.
     * @return : void
     */
    void deleteEmployeeDepartmentByEmployeeIdAndDepartmentId(String employeeId, String departmentId);

    /*
     * @desc :  Finds and retrieves all Departments associated with a specific Employee.
     * @param employeeId The unique identifier of the Employee.
     * @return An ArrayList containing all Departments associated with the Employee.
     */
    ArrayList<Department> findAllDepartmentsByEmployeeId(String employeeId) throws SQLException, IOException;

    /*
     * @desc :  Finds and retrieves all Employees associated with a specific Department.
     * @param departmentId The unique identifier of the Department.
     * @return An ArrayList containing all Employees associated with the Department.
     */
    ArrayList<Employee> findAllEmployeesByDepartmentId(String departmentId) throws SQLException, IOException;

    /*
     * @desc :  Retrieves all EmployeeDepartment objects from the data store.
     * @params : no params
     * @return An ArrayList containing all EmployeeDepartment objects.
     */
    ArrayList<EmployeeDepartment> getAllEmployeeDepartments() throws SQLException;
}

