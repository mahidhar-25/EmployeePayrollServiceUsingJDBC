package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Address;

import java.sql.SQLException;
import java.util.ArrayList;
/*
 * @desc :  This interface defines the data access operations for the Address entity related to employees.
 */
public interface AddressDaoInterface {

    /*
     * @desc :  Inserts a new Address entry for an Employee into the data store.
     * @param address The Address object to be inserted.
     * @return : void
     */
    void insertNewAddressOfEmployee(Address address);

    /*
     * @desc :  Updates the Address information for an Employee identified by their unique ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param address The updated Address object.
     * @return : void
     */
    void updateAddressByEmployeeId(String employeeId, Address address);

    /*
     * @desc :  Deletes the Address entry for an Employee based on their unique ID.
     * @param employeeId The unique identifier of the Employee whose Address entry is to be deleted.
     * @return : void
     */
    void deleteAddressByEmployeeId(String employeeId);

    /*
     * @desc :  Finds and retrieves the Address information for an Employee based on their unique ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The Address object associated with the Employee.
     */
    Address findEmployeeAddressByEmployeeId(String employeeId) throws SQLException;

    /*
     * @desc :  Retrieves all Address entries for all Employees from the data store.
     * @params : no params
     * @return An ArrayList containing all Address objects for all Employees.
     */
    ArrayList<Address> getAllEmployeeAddress() throws SQLException;
}

