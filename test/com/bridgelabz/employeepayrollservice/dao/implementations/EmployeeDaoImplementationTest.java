package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.models.Employee;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoImplementationTest {
    private EmployeeDaoImplementation employeeDaoImplementation;

    @Before
    public void setUp() throws Exception {
        employeeDaoImplementation = new EmployeeDaoImplementation();
    }

    @Test
    public void aInsertNewEmployee() {
        Employee employee = new Employee("employeetest" , "testname" , "0123456789" , "M" , "GE1");
        employeeDaoImplementation.insertNewEmployee(employee);
        Employee updateEmployee = employeeDaoImplementation.getEmployeeByIdFromLocal("employeetest");
        assertEquals("testname" , updateEmployee.getEmployeeName());
    }

    @Test
    public void bUpdateEmployeeDetailsLocally() {
        Employee employee = new Employee("employeetest" , "testname1" , "0123456789" , "M" , "GE1");
        employeeDaoImplementation.updateEmployeeById("employeetest",employee);
        Employee updateEmployee = employeeDaoImplementation.getEmployeeByIdFromLocal("employeetest");
        assertEquals("testname1" , updateEmployee.getEmployeeName());
    }

    @Test
    public void cFindEmployeeById() {
        Employee employee = employeeDaoImplementation.findEmployeeById("employeetest");
        assertEquals("testname1" , employee.getEmployeeName());
    }

    @Test
    public void dDeleteEmployeeById() {
        employeeDaoImplementation.deleteEmployeeById("employeetest");
        Employee updateEmployee = employeeDaoImplementation.getEmployeeByIdFromLocal("employeetest");
        assertNull(updateEmployee);
    }


    @Test
    public void eGetAllEmployeesFromACompanyByCompanyId() {
        ArrayList<Employee> listOfEmployees = employeeDaoImplementation.getAllEmployeesFromACompanyByCompanyId("GE1");
        assertEquals(2 , listOfEmployees.size());
    }

    @Test
    public void fGetAllEmployees() {
        assertEquals(3 , employeeDaoImplementation.getAllEmployees().size());
    }
}