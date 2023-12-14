package com.bridgelabz.employeepayrollservice.dao.interfaces;

import com.bridgelabz.employeepayrollservice.models.Company;

import java.util.ArrayList;

/*
 * @desc : This interface defines the data access operations for the Company entity.
 */
public interface CompanyDaoInterface {

    /*
     * @desc :  Inserts a new Company into the data store.
     * @param company The Company object to be inserted.
     * @return : void
     */
    void insertNewCompany(Company company);

    /*
     * @desc :  Updates a Company identified by its unique ID in the data store.
     * @param companyId The unique identifier of the Company to be updated.
     * @param company The updated Company object.
     * @return : void
     */
    void updateCompanyById(String companyId, Company company);

    /*
     * @desc :  Deletes a Company from the data store based on its unique ID.
     * @param companyId The unique identifier of the Company to be deleted.
     * @return : void
     */
    void deleteCompanyById(String companyId);

    /*
     * @desc :  Finds and retrieves a Company from the data store based on its unique ID.
     * @param companyId The unique identifier of the Company to be retrieved.
     * @return The Company object found, or null if not found.
     */
    Company findCompanyById(String companyId) ;

    /*
     * @desc :  Retrieves all Company objects from the data store.
     * @params : no params
     * @return An ArrayList containing all Company objects.
     */
    ArrayList<Company> getAllCompanies() ;
}
