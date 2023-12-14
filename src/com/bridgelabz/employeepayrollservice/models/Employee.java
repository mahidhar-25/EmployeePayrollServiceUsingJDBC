package com.bridgelabz.employeepayrollservice.models;

/*
 * @desc :  This class represents an Employee with unique identifiers, personal details, and company association.
 */
public class Employee {
    private String employeeId; // @desc Unique identifier for the employee.
    private String employeeName; // @desc Name of the employee.
    private String employeePhoneNo; // @desc Phone number of the employee.
    private String employeeGender; // @desc Gender of the employee.
    private String companyId; // @desc Unique identifier of the associated company.

    /*
     * Default constructor.
     */
    public Employee() {
    }

    /*
     * @desc : Parameterized constructor to initialize Employee with details.
     * @param employeeId Unique identifier for the employee.
     * @param employeeName Name of the employee.
     * @param employeePhoneNo Phone number of the employee.
     * @param employeeGender Gender of the employee.
     * @param companyId Unique identifier of the associated company.
     */
    public Employee(String employeeId, String employeeName, String employeePhoneNo, String employeeGender, String companyId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhoneNo = employeePhoneNo;
        this.employeeGender = employeeGender;
        this.companyId = companyId;
    }

    /*
     * @desc : Getter method for retrieving the employeeId.
     * @params :  no params
     * @return Unique identifier of the employee.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /*
     * @desc : Setter method for updating the employeeId.
     * @param employeeId New unique identifier for the employee.
     * @return : no return
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /*
     * @desc : Getter method for retrieving the employeeName.
     * @params :  no params
     * @return Name of the employee.
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /*
     * @desc : Setter method for updating the employeeName.
     * @param employeeName New name for the employee.
     * @return : no return
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /*
     * @desc : Getter method for retrieving the employeePhoneNo.
     * @params :  no params
     * @return Phone number of the employee.
     */
    public String getEmployeePhoneNo() {
        return employeePhoneNo;
    }

    /*
     * @desc : Setter method for updating the employeePhoneNo.
     * @param employeePhoneNo New phone number for the employee.
     * @return : no return
     */
    public void setEmployeePhoneNo(String employeePhoneNo) {
        this.employeePhoneNo = employeePhoneNo;
    }

    /*
     * @desc : Getter method for retrieving the employeeGender.
     * @params :  no params
     * @return Gender of the employee.
     */
    public String getEmployeeGender() {
        return employeeGender;
    }

    /*
     * @desc : Setter method for updating the employeeGender.
     * @param employeeGender New gender for the employee.
     * @return : no return
     */
    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    /*
     * @desc : Getter method for retrieving the companyId.
     * @params :  no params
     * @return Unique identifier of the associated company.
     */
    public String getCompanyId() {
        return companyId;
    }

    /*
     * @desc : Setter method for updating the companyId.
     * @param companyId New unique identifier for the associated company.
     * @return : no return
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /*
     * @desc : Method to set the employee details using another Employee object.
     * @param providedEmployee The Employee object whose details will be copied.
     * @return : no return
     */
    public void setEmployee(Employee providedEmployee) {
        this.setEmployeeId(providedEmployee.getEmployeeId());
        this.setEmployeeGender(providedEmployee.getEmployeeGender());
        this.setCompanyId(providedEmployee.getCompanyId());
        this.setEmployeeName(providedEmployee.getEmployeeName());
        this.setEmployeePhoneNo(providedEmployee.getEmployeePhoneNo());
    }
}
