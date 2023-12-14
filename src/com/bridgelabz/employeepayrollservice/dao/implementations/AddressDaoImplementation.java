package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.AddressDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * @desc Implementation of the AddressDaoInterface providing data access operations for the Address entity.
 */
public class AddressDaoImplementation implements AddressDaoInterface {
    // Collection to store all Address objects locally
    public static ArrayList<Address> allAddress;

    /*
     * @desc Inserts a new Address entry into the data store.
     * @param address The Address object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewAddressOfEmployee(Address address) {
        String employeeId = address.getEmployeeId();
        String street = address.getStreet();
        String village = address.getVillage();
        String city = address.getCity();
        String state = address.getState();
        String zip = address.getZip();
        String country = address.getCountry();

        // SQL query to insert a new Address
        String sqlQuery = String.format("INSERT INTO employee_address VALUES ('%s', '%s' , '%s','%s', '%s' , '%s');",
                employeeId, street, village, city, state, zip , country);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allAddress.add(address);
        }
    }

    /*
     * @desc Retrieves an Address from the local collection by Employee ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The Address object matching the given Employee ID.
     */
    public Address getAddressByIdFromLocal(String employeeId) {
        return allAddress.stream().filter(address -> address.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
    }

    /*
     * @desc Updates Address details locally.
     * @param providedAddress The Address object with updated details.
     * @return : void
     */
    public void updateAddressDetailsLocally(Address providedAddress) {
        allAddress.stream()
                .filter(address -> address.getEmployeeId().equals(providedAddress.getEmployeeId()))
                .forEach(address -> address.setAddress(providedAddress));
    }

    /*
     * @desc Deletes an Address locally by Employee ID.
     * @param employeeId The unique identifier of the Employee for whom Address should be deleted.
     * @return : void
     */
    public void deleteAddressByEmployeeIdLocally(String employeeId) {
        allAddress.removeIf(address -> address.getEmployeeId().equals(employeeId));
    }

    /*
     * @desc Updates the Address information identified by the Employee ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param address The updated Address object.
     * @return : void
     */
    @Override
    public void updateAddressByEmployeeId(String employeeId, Address address) {
        Address oldAddress = getAddressByIdFromLocal(employeeId);
        if (oldAddress == null) {
            System.out.println("Address is not there!!");
            insertNewAddressOfEmployee(address);
        } else {
            // SQL query to update Address information
            String sqlQuery = String.format("UPDATE employee_address " +
                            "SET " +
                            "street='%s',village='%s',city='%s',state='%s',zip='%s',country='%s' WHERE employeeId='%s'",
                    address.getStreet(), address.getVillage(), address.getCity(), address.getState(), address.getZip(), address.getCountry(), address.getEmployeeId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updateAddressDetailsLocally(address);
            }
        }
    }

    /*
     * @desc Deletes the Address entry identified by the Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee for whom Address should be deleted.
     * @return : void
     */
    @Override
    public void deleteAddressByEmployeeId(String employeeId) {
        // SQL query to delete Address entry
        String sqlQuery = String.format("DELETE FROM employee_address WHERE employeeId='%s'", employeeId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteAddressByEmployeeIdLocally(employeeId);
        }
    }

    /*
     * @desc Processes the ResultSet and returns a list of Address models.
     * @param resultSet The ResultSet obtained from a database query.
     * @return An ArrayList containing Address objects.
     */
    public ArrayList<Address> processResultSetAndReturnAddressModels(ResultSet resultSet) {
        ArrayList<Address> resultData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                // Extracting attributes from ResultSet
                String employeeId = resultSet.getString("employeeId");
                String street = resultSet.getString("street");
                String village = resultSet.getString("village");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String country = resultSet.getString("country");
                Address address = new Address(employeeId, street, village, city, state, zip, country);
                resultData.add(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc Finds and retrieves an Address by Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee.
     * @return The Address object matching the given Employee ID.
     */
    @Override
    public Address findEmployeeAddressByEmployeeId(String employeeId) throws SQLException {
        ArrayList<Address> allFetchedEmployees = new ArrayList<>();
        // SQL query to retrieve Address by Employee ID
        String sqlQuery = String.format("SELECT * FROM employee_address WHERE employeeId='%s';", employeeId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allFetchedEmployees = processResultSetAndReturnAddressModels(resultSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allFetchedEmployees.stream().findFirst().orElse(null);
    }

    /*
     * @desc Retrieves all Address entries from the data store.
     * @params : no params
     * @return An ArrayList containing all Address objects.
     */
    @Override
    public ArrayList<Address> getAllEmployeeAddress()  {
        // SQL query to retrieve all Address entries
        String sqlQuery = "SELECT * FROM employee_address;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allAddress = processResultSetAndReturnAddressModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return allAddress;
    }
}
