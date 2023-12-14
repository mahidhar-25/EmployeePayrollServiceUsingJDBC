package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.database.DatabaseConnect;
import com.bridgelabz.employeepayrollservice.dao.interfaces.CompanyDaoInterface;
import com.bridgelabz.employeepayrollservice.models.Company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * @desc Implementation of the CompanyDaoInterface providing data access operations for the Company entity.
 */
public class CompanyDaoImplementation implements CompanyDaoInterface {
    // Collection to store all Company objects locally
    public ArrayList<Company> allCompanies = new ArrayList<>();
    public CompanyDaoImplementation() throws SQLException, IOException {
        allCompanies = new ArrayList<>();
        this.allCompanies = getAllCompanies();
    }


    /*
     * @desc Inserts a new Company entry into the data store.
     * @param company The Company object to be inserted.
     * @return : void
     */
    @Override
    public void insertNewCompany(Company company) {
        String companyId = company.getCompanyId();
        String companyName = company.getCompanyName();
        // SQL query to insert a new Company
        String sqlQuery = String.format("INSERT INTO company VALUES ('%s' , '%s');", companyId, companyName);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            allCompanies.add(company);
        }
    }

    /*
     * @desc :  Helper method to get Company by ID from the local collection
     * @param String - companyId
     * @return : Company
     */
    public Company getCompanyByIdFromLocal(String companyId) {
        return allCompanies.stream().filter(company -> company.getCompanyId().equals(companyId)).findFirst().orElse(null);
    }

    /*
     * @desc : Helper method to update Company details locally
     * @param company The updated Company object.
     * @return : void
     */
    //
    public void updateCompanyDetailsLocally(Company providedCompany) {
        allCompanies.stream()
                .filter(company -> company.getCompanyId().equals(providedCompany.getCompanyId()))
                .forEach(company -> company.setCompany(providedCompany));
    }

    /*
     * @desc Updates the Company information identified by the Company ID in the data store.
     * @param companyId The unique identifier of the Company for the update.
     * @param company The updated Company object.
     * @return : void
     */
    @Override
    public void updateCompanyById(String companyId, Company company) {
        Company oldCompany = getCompanyByIdFromLocal(companyId);
        if (oldCompany == null) {
            System.out.println("Company is not there!!");
            insertNewCompany(company);
        } else {
            // SQL query to update Company information
            String sqlQuery = String.format("UPDATE company " +
                    "SET companyName='%s'" +
                    "WHERE companyId='%s'", company.getCompanyName(), company.getCompanyId());
            int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
            if (databaseUpdated > 0) {
                updateCompanyDetailsLocally(company);
            }
        }
    }

    /*
     * @desc :  Helper method to delete Company by ID from the local collection
     * @param companyId The unique identifier of the Company to be deleted.
     * @return : void
     */
    public void deleteCompanyByIdLocally(String companyId) {
        allCompanies.removeIf(company -> company.getCompanyId().equals(companyId));
    }

    /*
     * @desc :  Deletes the Company entry identified by the Company ID from the data store.
     * @param companyId The unique identifier of the Company to be deleted.
     * @return : void
     */
    @Override
    public void deleteCompanyById(String companyId) {
        // SQL query to delete Company entry
        String sqlQuery = String.format("DELETE FROM company WHERE companyId='%s'", companyId);
        int databaseUpdated = ExecuteQueries.executeQuery(sqlQuery);
        if (databaseUpdated > 0) {
            deleteCompanyByIdLocally(companyId);
        }
    }
/*
  @desc :  Helper method to process ResultSet and return Company models
  @params : ResultSet - resultSet to loop through the rows
  @return : ArrayList<Company>

 */
    public ArrayList<Company> processResultSetAndReturnCompanyModels(ResultSet resultSet) {
        ArrayList<Company> resultData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String retrievedCompanyId = resultSet.getString("companyId");
                String retrievedCompanyName = resultSet.getString("companyName");
                Company retrievedCompany = new Company(retrievedCompanyId, retrievedCompanyName);
                resultData.add(retrievedCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    /*
     * @desc Finds and retrieves the Company information by Company ID from the data store.
     * @param companyId The unique identifier of the Company.
     * @return The Company object associated with the Company ID.
     */
    @Override
    public Company findCompanyById(String companyId) {
        ArrayList<Company> allFetchedCompanies = new ArrayList<>();
        // SQL query to select Company by ID
        String sqlQuery = String.format("SELECT * FROM company WHERE companyId='%s';", companyId);
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
             allFetchedCompanies = processResultSetAndReturnCompanyModels(resultSet);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return allFetchedCompanies.stream().findFirst().orElse(null);
    }

    /*
     * @desc :  Retrieves all Company entries from the data store.
     * @params : no params
     * @return An ArrayList containing all Company objects.
     */
    @Override
    public ArrayList<Company> getAllCompanies()  {
        // SQL query to select all Companies
        String sqlQuery = "SELECT * FROM company;";
        try(Connection connection = DatabaseConnect.getMysqlConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            allCompanies = processResultSetAndReturnCompanyModels(resultSet);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return allCompanies;
    }
}
