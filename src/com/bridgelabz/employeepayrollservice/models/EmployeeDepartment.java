package com.bridgelabz.employeepayrollservice.models;
/*
 * @desc :  This class represents the association between an Employee and a Department.
 * It contains information about the employee's ID and the department's ID.
 */
public class EmployeeDepartment {
    private String employeeId; // @desc Unique identifier for the employee.
    private String departmentId; // @desc Unique identifier for the department.

    /*
     * Default constructor.
     */
    public EmployeeDepartment() {
    }

    /*
     * @desc : Parameterized constructor to initialize EmployeeDepartment with details.
     * @param employeeId Unique identifier for the employee.
     * @param departmentId Unique identifier for the department.
     * @return : no return
     */
    public EmployeeDepartment(String employeeId, String departmentId) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }

    /*
     * @desc : Getter method for retrieving the employeeId.
     * @params :  no params
     * @return Unique identifier for the employee.
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
     * @desc : Getter method for retrieving the departmentId.
     * @params :  no params
     * @return Unique identifier for the department.
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /*
     * @desc : Setter method for updating the departmentId.
     * @param departmentId New unique identifier for the department.
     * @return : no return
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /*
     * @desc : Method to set the EmployeeDepartment details using another EmployeeDepartment object.
     * @param providedEmployeeDepartment The EmployeeDepartment object whose details will be copied.
     * @return : no return
     */
    public void setEmployeeDepartment(EmployeeDepartment providedEmployeeDepartment) {
        setDepartmentId(providedEmployeeDepartment.getDepartmentId());
        setEmployeeId(providedEmployeeDepartment.getEmployeeId());
    }
}
