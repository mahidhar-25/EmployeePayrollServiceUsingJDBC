package com.bridgelabz.employeepayrollservice;

import com.bridgelabz.employeepayrollservice.dao.implementations.EmployeeDaoImplementation;
import com.bridgelabz.employeepayrollservice.dao.implementations.PayrollDaoImplementation;
import com.bridgelabz.employeepayrollservice.models.Payroll;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("!!! welcome to employee payroll service using jdbc !!!");
        // After refactor usecase 2
        EmployeeDaoImplementation employeeDaoImplementation = new EmployeeDaoImplementation();
        System.out.println(" Employees details are : " +employeeDaoImplementation.getAllEmployees());
         //usecase 3
        Payroll payroll = new Payroll("emp1" , 400000.0 , 25000.0 , 375000.0 , 5000.0 , 370000.0);
        PayrollDaoImplementation payrollDaoImplementation = new PayrollDaoImplementation();
        payrollDaoImplementation.updatePayrollByEmployeeId("emp1" , payroll);
        System.out.println("updated employee : " + payrollDaoImplementation.findEmployeePayrollByEmployeeId("emp1"));
    }
}
