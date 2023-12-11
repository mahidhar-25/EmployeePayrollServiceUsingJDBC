package com.bridgelabz.employeepayrollservice;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeePayrollServiceTest {

    @Test
    public void readEmployeeDetails_FromJavaProgramAnd_CountShouldMatchWithEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        int employeeCount = employeePayrollService.readEmployeeDetails();
        assertEquals(3 , employeeCount);
    }
}