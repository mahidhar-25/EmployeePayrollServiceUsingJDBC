package com.bridgelabz.employeepayrollservice.models;
/*
 * @desc : This class represents a Department with unique identifiers and a name.
 */
public class Department {
    private String departmentId; // @desc Unique identifier for the department.
    private String departmentName; // @desc Name of the department.

    /*
     * Default constructor.
     */
    public Department() {
    }

    /*
     * @desc : Parameterized constructor to initialize Department with details.
     * @param departmentId Unique identifier for the department.
     * @param departmentName Name of the department.
     * @return : no return
     */
    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
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
     * @desc : Getter method for retrieving the departmentName.
     * @params :  no params
     * @return Name of the department.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /*
     * @desc : Setter method for updating the departmentName.
     * @param departmentName New name for the department.
     * @return : no return
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /*
     * @desc : Method to set the department details using another Department object.
     * @param providedDepartment The Department object whose details will be copied.
     * @return : no return
     */
    public void setDepartment(Department providedDepartment) {
        this.departmentId = providedDepartment.getDepartmentId();
        this.departmentName = providedDepartment.getDepartmentName();
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
