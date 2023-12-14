package com.bridgelabz.employeepayrollservice.employeepayroll;

import java.time.LocalDate;
import java.util.Scanner;

/*
@desc :
The EmployeePayrollService class is designed to manage and interact with employee payroll data.
Here's a description of the key features and methods:

Attributes:
        employeeId: String - Represents the unique identifier for an employee.
        employeeName: String - Stores the name of the employee.
        employeeSalary: long - Holds the salary information for the employee.
        CURRENT_DIRECTORY, EMPLOYEE_PAYROLL_DB_FOLDER_NAME, EMPLOYEE_PAYROLL_DB_NAME: Constants defining the directory and file names
                            for storing employee data.

Constructor:
    Parametrized constructor that initializes the class attributes (employeeId, employeeName, employeeSalary) and
    appends the employee data to a file (presumably a database file).

Getters and Setters:
    Accessor methods (getEmployeeId, getEmployeeName, getEmployeeSalary) to retrieve attribute values.
    Mutator methods (setEmployeeId, setEmployeeName, setEmployeeSalary) to modify attribute values.

File Operations:
    writeEmployeePayrollToDataBase: Writes the employee data to a database file, creating the necessary directory and file
    if they don't exist.
    readEmployeePayrollData: Takes user input to create a new EmployeePayrollService object.

File Reading Operations:
    printAndReturnCountOfEntriesInFile: Prints each line of the employee database file and returns the total number of entries.
    countEntriesInFile: Returns the total number of entries in the employee database file.

String Representation:
    saveEmployeePayrollToDataBase: Generates a string representation of the employee data.
    toString: Overrides the toString method to provide a formatted string representation of the EmployeePayrollService object.
 */
public class EmployeePayrollData {

    private int employeeId;
    private String employeeName;
    private double employeeSalary;
    private LocalDate startDate;

    /*
    @desc : EmployeePayrollService it is a parametrized constructor
    @params : String - employeeId
              String - employeeName
              String - employeeSalary
    @return : no return;
     */
    public EmployeePayrollData(int employeeId, String employeeName, double employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }


    public EmployeePayrollData(int employeeId, String employeeName, double employeeSalary , LocalDate startDate) {
        this(employeeId, employeeName, employeeSalary);
        this.startDate = startDate;
    }

    /*
    @desc : getEmployeeId is a getter to get employeeId
    @params : no params
    @return : String - employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /*
    @desc : setEmployeeId is a setter to set employeeId
    @param : String - employeeId
    @return : no return
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    /*
    @desc : getEmployeeName is a getter to get employeeName
    @params : no params
    @return : String - employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /*
    @desc : setEmployeeName is a setter to set employeeName
    @param : String - employeeName
    @return : no return
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    /*
    @desc : getEmployeeName is a getter to get employeeSalary
    @params : no params
    @return : Long - employeeSalary
     */
    public double getEmployeeSalary() {
        return employeeSalary;
    }
    /*
    @desc : setEmployeeSalary is a setter to set employeeSalary
    @param : Long - employeeSalary
    @return : no return
     */
    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }


    /*
    @desc : it takes all the required inputs for employee payroll
    @params : no params
    @return : EmployeePayrollService
     */
    public static EmployeePayrollData  readEmployeePayrollData()  {
        Scanner input = new Scanner(System.in);
        String  employeeName;
        int employeeId ;
        long employeeSalary;
        System.out.println("Enter employee id : ");
        employeeId = input.nextInt();
        System.out.println("Enter employee name in a single line : ");
        input.nextLine();
        employeeName = input.nextLine();
        System.out.println("Enter employee salary : ");
        employeeSalary = input.nextLong();

        return new EmployeePayrollData(employeeId , employeeName , employeeSalary);
    }


    /*
@desc : saveEmployeePayrollToDataBase is a method to print all the details of the employee payroll service
@params : no params
@return : string
 */

    public String saveEmployeePayrollToDataBase() {
        return "{" + "employeeId=" + employeeId + "," + "employeeName=" + employeeName + "," + "employeeSalary=" + employeeSalary + "," +'}';
    }

    /*
    @desc : toString is a method to print all the details of the employee payroll service
    @params : no params
    @return : string
     */
    @Override
    public String toString() {
        return "EmployeePayrollService = {" +
                "employeeId=" + employeeId + ",\n" +
                "employeeName=" + employeeName + ",\n" +
                "employeeSalary=" + employeeSalary + ",\n" +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;

        return employeeId == that.employeeId &&
                Double.compare(that.employeeSalary, employeeSalary) == 0 &&
                employeeName.equals(that.employeeName);

    }
}
