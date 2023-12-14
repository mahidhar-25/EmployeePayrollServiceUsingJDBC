package com.bridgelabz.employeepayrollservice;

import com.bridgelabz.employeepayrollservice.dao.implementations.EmployeeDaoImplementation;
import com.bridgelabz.employeepayrollservice.dao.implementations.ExecuteQueries;
import com.bridgelabz.employeepayrollservice.dao.implementations.PayrollDaoImplementation;
import com.bridgelabz.employeepayrollservice.models.Payroll;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("!!! welcome to employee payroll service using jdbc !!!");
         //After refactor usecase 2
        EmployeeDaoImplementation employeeDaoImplementation = new EmployeeDaoImplementation();
        System.out.println(" Employees details are : " +employeeDaoImplementation.getAllEmployees());
         //usecase 3
        Payroll payroll = new Payroll("emp1" , 400000.0 , 25000.0 , 375000.0 , 5000.0 , 370000.0);
        PayrollDaoImplementation payrollDaoImplementation = new PayrollDaoImplementation();
        payrollDaoImplementation.updatePayrollByEmployeeId("emp1" , payroll);
        System.out.println("updated employee : " + payrollDaoImplementation.findEmployeePayrollByEmployeeId("emp1"));

        //usecase 6
        String sqlQuery = "SELECT employee.employeeGender , SUM(payroll.netPay)  , company.*\n" +
                "FROM employee\n" +
                "JOIN payroll ON employee.employeeId = payroll.employeeId\n" +
                "JOIN company ON employee.companyId = company.companyId\n" +
                "JOIN employee_department ON employee.employeeId = employee_department.employeeId\n" +
                "WHERE company.companyId LIKE 'GE%' GROUP BY companyId , employeeGender;";

        new ExecuteQueries().executeQueryToGetSumOfSalaryGroupByGender(sqlQuery);
    }
}
