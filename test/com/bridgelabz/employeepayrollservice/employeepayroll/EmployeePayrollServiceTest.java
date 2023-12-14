package com.bridgelabz.employeepayrollservice.employeepayroll;

import com.bridgelabz.employeepayrollservice.employeepayroll.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.employeepayroll.EmployeePayrollService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeePayrollServiceTest {
    private EmployeePayrollService employeePayrollService;
    @Before
    public void setup(){
       employeePayrollService = new EmployeePayrollService();
    }
    /*
    @desc : this test is to check the connectivity and initially the count of the from database is matching or not
     */
    @Test
    public void readEmployeeDetails_FromJavaProgramAnd_CountShouldMatchWithEmployeeCount() {
        ArrayList<EmployeePayrollData> employeeData = employeePayrollService.readEmployeeDetails();
        assertEquals(6 , employeeData.size());
    }

    /*
    @desc : whenever we up[date whether it is in sync with the database or not
     */
    @Test
    public void updateEmployeeSalaryInDatabaseAndCheckWhetherTheyAreInSync() {
        employeePayrollService.updateEmployeeSalaryInDatabase("Karthik" , 300000);
        boolean isDataInSync = employeePayrollService.checkEmployeePayrollIsInSyncWithDatabase("Karthik");
        assertTrue(isDataInSync);
    }
    /*
 @desc : whenever we up[date whether it is in sync with the database or not
  */
    @Test
    public void updateEmployeeSalaryInDatabaseUsingPreparedStatementAndCheckWhetherTheyAreInSync() {
        employeePayrollService.updateEmployeeSalaryInDatabaseUsingPreparedStatement("Karthik" , 30000);
        boolean isDataInSync = employeePayrollService.checkEmployeePayrollIsInSyncWithDatabase("Karthik");
        assertTrue(isDataInSync);
    }
    /*
    @desc : to get the employees who join in specific dates
     */

    @Test
    public void getEmployeeDetailsBetweenDatesWhoJoinedAndCheckWithOurLocalMemory(){
        LocalDate startDate = LocalDate.of(2022 , 01 , 01);
        LocalDate endDate = LocalDate.now();
        ArrayList<EmployeePayrollData> employeePayrollDataArrayList = employeePayrollService.getEmployeeDetailsWhoJoinedBetweenDates(startDate , endDate);
        assertEquals( 4, employeePayrollDataArrayList.size());
    }

    @Test
    public void getEmployeePayrollDetailsGroupingByGender(){
        int count = employeePayrollService.getEmployeePayrollDetailsGroupByGender();
        assertEquals(2 , count);
    }
}