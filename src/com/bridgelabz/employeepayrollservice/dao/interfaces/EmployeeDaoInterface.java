package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
/*
 * @desc :  This interface defines the data access operations for the Employee entity.
 */
public interface EmployeeDaoInterface {

    /*
     * @desc :  Inserts a new Employee into the data store.
     * @param employee The Employee object to be inserted.
     * @return : void
     */
    void insertNewEmployee(Employee employee);

    /*
     * @desc :  Updates an Employee identified by its unique ID in the data store.
     * @param employeeId The unique identifier of the Employee to be updated.
     * @param employee The updated Employee object.
     * @return : void
     */
    void updateEmployeeById(String employeeId, Employee employee);

    /*
     * @desc :  Deletes an Employee from the data store based on its unique ID.
     * @param employeeId The unique identifier of the Employee to be deleted.
     * @return : void
     */
    void deleteEmployeeById(String employeeId);

    /*
     * @desc :  Finds and retrieves an Employee from the data store based on its unique ID.
     * @param employeeId The unique identifier of the Employee to be retrieved.
     * @return The Employee object found, or null if not found.
     */
    Employee findEmployeeById(String employeeId) throws SQLException;

    /*
     * @desc :  Retrieves all Employee objects from the data store belonging to a specific company.
     * @param companyId The unique identifier of the company.
     * @return An ArrayList containing all Employee objects from the specified company.
     */
    ArrayList<Employee> getAllEmployeesFromACompanyByCompanyId(String companyId) throws SQLException;

    /*
     * @desc :  Retrieves all Employee objects from the data store.
     * @params : no params
     * @return An ArrayList containing all Employee objects.
     */
    ArrayList<Employee> getAllEmployees() throws SQLException;
}
