package com.bridgelabz.employeepayrollservice.models;

/*
 * @desc This class represents a Company with its unique identifier and name.
 */
public class Company {
    private String companyId; // @desc Unique identifier for the company.
    private String companyName; // @desc Name of the company.

    /*
     * Default constructor.
     */
    public Company() {
    }

    /*
     * @desc : Parameterized constructor to initialize Company with companyId and companyName.
     * @param : companyId The unique identifier for the company.
     * @param : companyName The name of the company.
     * @return : no return
     */
    public Company(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    /*
     * @desc : Getter method for retrieving the companyId.
     * @params :  no params
     * @return : The unique identifier of the company.
     */
    public String getCompanyId() {
        return companyId;
    }

    /*
     * @desc : Setter method for updating the companyId.
     * @param : companyId The new unique identifier for the company.
     * @return : no return
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /*
     * @desc : Getter method for retrieving the companyName.
     * @param : no param
     * @return : The name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /*
     * @desc : Setter method for updating the companyName.
     * @param :  companyName The new name for the company.
     * @return :  no return
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /*
     * @desc :  Method to set the company details using another Company object.
     * @param : providedCompany The Company object whose details will be copied.
     * @return :  no return
     */
    public void setCompany(Company providedCompany) {
        setCompanyId(providedCompany.getCompanyId());
        setCompanyName(providedCompany.getCompanyName());
    }
}
