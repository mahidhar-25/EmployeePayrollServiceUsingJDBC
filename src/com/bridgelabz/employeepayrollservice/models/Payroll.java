package com.bridgelabz.employeepayrollservice.models;
/*
 * @desc :  Represents the payroll details of an employee, including basic pay, deductions, taxable pay, tax, and net pay.
 */
public class Payroll {
    private String employeeId; // @desc Unique identifier for the employee.
    private Double basicPay; // @desc The basic salary of the employee.
    private Double deductions; // @desc Deductions from the salary.
    private Double taxablePay; // @desc The portion of the salary that is taxable.
    private Double tax; // @desc The amount of tax deducted.
    private Double netPay; // @desc The net salary after deductions.

    /*
     * @desc : Parameterized constructor to initialize Payroll with employeeId and basicPay.
     * @param employeeId Unique identifier for the employee.
     * @param basicPay The basic salary of the employee.
     * @return : no return
     */
    public Payroll(String employeeId, Double basicPay) {
        this.employeeId = employeeId;
        this.basicPay = basicPay;
    }

    /*
     * Default constructor.
     */
    public Payroll() {
    }

    /*
     * @desc : Parameterized constructor to initialize Payroll with all details.
     * @param employeeId Unique identifier for the employee.
     * @param basicPay The basic salary of the employee.
     * @param deductions Deductions from the salary.
     * @param taxablePay The portion of the salary that is taxable.
     * @param tax The amount of tax deducted.
     * @param netPay The net salary after deductions.
     * @return : no return
     */
    public Payroll(String employeeId, Double basicPay, Double deductions, Double taxablePay, Double tax, Double netPay) {
        this.employeeId = employeeId;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.tax = tax;
        this.netPay = netPay;
    }

    // Getter and Setter methods for each attribute with corresponding comments.

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
     * @desc : Getter method for retrieving the basicPay.
     * @params :  no params
     * @return The basic salary of the employee.
     */
    public Double getBasicPay() {
        return basicPay;
    }

    /*
     * @desc : Setter method for updating the basicPay.
     * @param basicPay New basic salary of the employee.
     * @return : no return
     */
    public void setBasicPay(Double basicPay) {
        this.basicPay = basicPay;
    }

    /*
     * @desc : Getter method for retrieving the deductions.
     * @params :  no params
     * @return Deductions from the salary.
     */
    public Double getDeductions() {
        return deductions;
    }

    /*
     * @desc : Setter method for updating the deductions.
     * @param deductions New deductions from the salary.
     * @return : no return
     */
    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    /*
     * @desc : Getter method for retrieving the taxablePay.
     * @params :  no params
     * @return The portion of the salary that is taxable.
     */
    public Double getTaxablePay() {
        return taxablePay;
    }

    /*
     * @desc : Setter method for updating the taxablePay.
     * @param taxablePay New taxable portion of the salary.
     * @return : no return
     */
    public void setTaxablePay(Double taxablePay) {
        this.taxablePay = taxablePay;
    }

    /*
     * @desc : Getter method for retrieving the tax.
     * @params :  no params
     * @return The amount of tax deducted.
     */
    public Double getTax() {
        return tax;
    }

    /*
     * @desc : Setter method for updating the tax.
     * @param tax New amount of tax deducted.
     * @return : no return
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }

    /*
     * @desc : Getter method for retrieving the netPay.
     * @params :  no params
     * @return The net salary after deductions.
     */
    public Double getNetPay() {
        return netPay;
    }

    /*
     * @desc : Setter method for updating the netPay.
     * @param netPay New net salary after deductions.
     * @return : no return
     */
    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    /*
     * @desc : Method to set the Payroll details using another Payroll object.
     * @param providedPayroll The Payroll object whose details will be copied.
     * @return : no return
     */
    public void setPayroll(Payroll providedPayroll) {
        setBasicPay(providedPayroll.getBasicPay());
        setDeductions(providedPayroll.getDeductions());
        setTax(providedPayroll.getTax());
        setNetPay(providedPayroll.getNetPay());
        setEmployeeId(providedPayroll.getEmployeeId());
        setTaxablePay(providedPayroll.getTaxablePay());
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "employeeId='" + employeeId + '\'' +
                ", basicPay=" + basicPay +
                ", deductions=" + deductions +
                ", taxablePay=" + taxablePay +
                ", tax=" + tax +
                ", netPay=" + netPay +
                '}';
    }
}
