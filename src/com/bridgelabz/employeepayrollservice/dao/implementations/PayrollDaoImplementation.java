package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.PayrollDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Payroll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * @desc Implementation of the PayrollDaoInterface providing data access operations for the Payroll entity.
 */
public class PayrollDaoImplementation implements PayrollDaoInterface {
    // Collection to store all Payroll objects locally
    public  ArrayList<Payroll> allPayrolls;
    public PayrollDaoImplementation() throws SQLException, IOException {
        allPayrolls = new ArrayList<>();
        this.allPayrolls = getAllEmployeePayrolls();
    }

    /*
     * @desc Inserts a new Payroll entry into the data store.
     * @param payroll The Payroll object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewPayrollOfEmployee(Payroll payroll) {
        String employeeId = payroll.getEmployeeId();
        Double deductions = payroll.getDeductions();
        Double basicPay = payroll.getBasicPay();
        Double taxablePay = payroll.getTaxablePay();
        Double tax = payroll.getTax();
        Double netPay = payroll.getNetPay();

        // SQL query to insert a new Payroll
        String sqlQuery = String.format("INSERT INTO payroll VALUES ('%s' , %.2f, %.2f, %.2f, %.2f, %.2f');",
                employeeId, deductions, basicPay, taxablePay, tax, netPay);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allPayrolls.add(payroll);
        }
    }

    /*
     * @desc Retrieves a Payroll from the local collection by Employee ID.
     * @param employeeId The unique identifier of the Employee.
     * @return The Payroll object matching the given Employee ID.
     */
    public Payroll getPayrollsByIdFromLocal(String employeeId) {
        return allPayrolls.stream().filter(payroll -> payroll.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
    }

    /*
     * @desc Updates Payroll details locally.
     * @param providedPayroll The Payroll object with updated details.
     * @return : void
     */
    public void updatePayrollDetailsLocally(Payroll providedPayroll) {
        allPayrolls.stream()
                .filter(payroll -> payroll.getEmployeeId().equals(providedPayroll.getEmployeeId()))
                .forEach(company -> company.setPayroll(providedPayroll));
    }

    /*
     * @desc Deletes a Payroll locally by Employee ID.
     * @param employeeId The unique identifier of the Employee for whom Payroll should be deleted.
     * @return : void
     */
    public void deletePayrollByEmployeeIdLocally(String employeeId) {
        allPayrolls.removeIf(payroll -> payroll.getEmployeeId().equals(employeeId));
    }

    /*
     * @desc Updates the Payroll information identified by the Employee ID in the data store.
     * @param employeeId The unique identifier of the Employee for the update.
     * @param payroll     The updated Payroll object.
     * @return : void
     */
    @Override
    public void updatePayrollByEmployeeId(String employeeId, Payroll payroll) {
        Payroll oldPayroll = getPayrollsByIdFromLocal(employeeId);
        if (oldPayroll == null) {
            System.out.println("Payroll is not there!!");
            insertNewPayrollOfEmployee(payroll);
        } else {
            // SQL query to update Payroll information
            String sqlQuery = String.format("UPDATE payroll " +
                            "SET " +
                            "basicPay='%.2f',deductions='%.2f',taxablePay='%.2f',tax='%.2f',netPay='%.2f' WHERE employeeId='%s'",
                    payroll.getBasicPay(), payroll.getDeductions(), payroll.getTaxablePay(), payroll.getTax(), payroll.getNetPay(), payroll.getEmployeeId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updatePayrollDetailsLocally(payroll);
            }
        }
    }

    /*
     * @desc Deletes the Payroll entry identified by the Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee for whom Payroll should be deleted.
     * @return : void
     */
    @Override
    public void deletePayrollByEmployeeId(String employeeId) {
        // SQL query to delete Payroll entry
        String sqlQuery = String.format("DELETE FROM payroll WHERE employeeId='%s'", employeeId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deletePayrollByEmployeeIdLocally(employeeId);
        }
    }

    /*
     * @desc Processes the ResultSet and returns a list of Payroll models.
     * @param resultSet The ResultSet obtained from a database query.
     * @return An ArrayList containing Payroll objects.
     */
    public ArrayList<Payroll> processResultSetAndReturnPayrollModels(ResultSet resultSet) {
        ArrayList<Payroll> resultData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                // Extracting attributes from ResultSet
                String employeeId = resultSet.getString("employeeId");
                Double deductions = resultSet.getDouble("deductions");
                Double basicPay = resultSet.getDouble("basicPay");
                Double taxablePay = resultSet.getDouble("taxablePay");
                Double tax = resultSet.getDouble("tax");
                Double netPay = resultSet.getDouble("netPay");
                Payroll payroll = new Payroll(employeeId, basicPay, deductions, taxablePay, tax, netPay);
                resultData.add(payroll);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc Finds and retrieves a Payroll by Employee ID from the data store.
     * @param employeeId The unique identifier of the Employee.
     * @return The Payroll object matching the given Employee ID.
     */
    @Override
    public Payroll findEmployeePayrollByEmployeeId(String employeeId) throws SQLException {
        ArrayList<Payroll> allFetchedEmployees = new ArrayList<>();
        // SQL query to retrieve Payroll by Employee ID
        String sqlQuery = String.format("SELECT * FROM payroll WHERE employeeId='%s';", employeeId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allFetchedEmployees = processResultSetAndReturnPayrollModels(resultSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allFetchedEmployees.stream().findFirst().orElse(null);
    }

    /*
     * @desc Retrieves all Payrolls from the data store.
     * @params : no params
     * @return An ArrayList containing all Payroll objects.
     */
    @Override
    public ArrayList<Payroll> getAllEmployeePayrolls() throws SQLException {
        // SQL query to retrieve all Payrolls
        String sqlQuery = "SELECT * FROM payroll;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allPayrolls = processResultSetAndReturnPayrollModels(resultSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allPayrolls;
    }
}
