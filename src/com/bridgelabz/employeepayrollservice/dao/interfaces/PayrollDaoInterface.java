package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Payroll;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @desc :  This interface defines the data access operations for the Payroll entity.
 */
public interface PayrollDaoInterface {

    /*
     * @desc :  Inserts a new Payroll entry for an Employee into the data store.
     * @param payroll The Payroll object to be inserted.
     * @return : void
     */
    void insertNewPayrollOfEmployee(Payroll payroll);

    /*
     * @desc :  Updates the Payroll information for an Employee identified by their unique ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param payroll The updated Payroll object.
     * @return : void
     */
    void updatePayrollByEmployeeId(String employeeId, Payroll payroll);

    /*
     * @desc :  Deletes the Payroll entry for an Employee based on their unique ID.
     * @param employeeId The unique identifier of the Employee whose Payroll entry is to be deleted.
     * @return : void
     */
    void deletePayrollByEmployeeId(String employeeId);

    /*
     * @desc :  Finds and retrieves the Payroll information for an Employee based on their unique ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The Payroll object associated with the Employee.
     */
    Payroll findEmployeePayrollByEmployeeId(String employeeId) throws SQLException;

    /*
     * @desc :  Retrieves all Payroll entries for all Employees from the data store.
     * @params : no params
     * @return An ArrayList containing all Payroll objects for all Employees.
     */
    ArrayList<Payroll> getAllEmployeePayrolls() throws SQLException;
}
