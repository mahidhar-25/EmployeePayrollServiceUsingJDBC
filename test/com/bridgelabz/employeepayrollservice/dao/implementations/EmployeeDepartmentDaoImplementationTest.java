package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.models.Department;
import com.bridgelabz.employeepayrollservice.models.Employee;
import com.bridgelabz.employeepayrollservice.models.EmployeeDepartment;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class EmployeeDepartmentDaoImplementationTest {
    private EmployeeDepartmentDaoImplementation employeeDepartmentDaoImplementation;
    @BeforeClass
    public static void setProperties() throws SQLException, IOException {
        Employee employee = new Employee("employeetest" , "testname" , "0123456789" , "M" , "GE1");
        new EmployeeDaoImplementation().insertNewEmployee(employee);
        Department department = new Department("dep1" , "Administration");
        new DepartmentDaoImplementation().insertNewDepartment(department);
    }

    @Before
    public void setUp() throws Exception {
        employeeDepartmentDaoImplementation = new EmployeeDepartmentDaoImplementation();
    }

    @Test
    public void ainsertNewEmployeeInDepartment() {
        EmployeeDepartment employeeDepartment = new EmployeeDepartment("employeetest" , "dep_hr");
        employeeDepartmentDaoImplementation.insertNewEmployeeInDepartment(employeeDepartment);
        EmployeeDepartment updatedEmployeeDepartment = employeeDepartmentDaoImplementation.getEmployeeDepartmentByEmployeeIdFromLocal("emp1");
        assertEquals("dep_hr" , updatedEmployeeDepartment.getDepartmentId());
    }

    @Test
    public void bupdateEmployeeDepartmentByEmployeeId() {
        EmployeeDepartment employeeDepartment = new EmployeeDepartment("employeetest" , "dep1");
        employeeDepartmentDaoImplementation.updateEmployeeDepartmentByEmployeeId("employeetest",employeeDepartment);
        EmployeeDepartment updatedEmployeeDepartment = employeeDepartmentDaoImplementation.getEmployeeDepartmentByEmployeeIdFromLocal("employeetest");
        assertEquals("dep1" , updatedEmployeeDepartment.getDepartmentId());
    }

//    @Test
//    public void cfindAllDepartmentsByEmployeeId() {
//        assertEquals(1 , employeeDepartmentDaoImplementation.findAllDepartmentsByEmployeeId("employeetest").size());
//    }
//
//    @Test
//    public void dfindAllEmployeesByDepartmentId() {
//        assertEquals(1 , employeeDepartmentDaoImplementation.findAllEmployeesByDepartmentId("dep1").size());
//    }

    @Test
    public void edeleteEmployeeDepartmentByEmployeeId() {
        employeeDepartmentDaoImplementation.deleteEmployeeDepartmentByEmployeeId("employeetest");
        EmployeeDepartment updatedEmployeeDepartment = employeeDepartmentDaoImplementation.getEmployeeDepartmentByEmployeeIdFromLocal("employeetest");
        assertNull(updatedEmployeeDepartment);
    }

    @Test
    public void fdeleteEmployeeDepartmentByEmployeeIdAndDepartmentId() {
        ainsertNewEmployeeInDepartment();
        employeeDepartmentDaoImplementation.deleteEmployeeDepartmentByEmployeeIdAndDepartmentId("employeetest" , "dep1");
        EmployeeDepartment updatedEmployeeDepartment = employeeDepartmentDaoImplementation.getEmployeeDepartmentByEmployeeIdFromLocal("employeetest");
        assertNull(updatedEmployeeDepartment);
    }


    @Test
    public void ggetAllEmployeeDepartments() {
        assertEquals(6 , employeeDepartmentDaoImplementation.getAllEmployeeDepartments().size());
    }
    @AfterClass
    public static void removeProperties() throws SQLException, IOException {
        new EmployeeDaoImplementation().deleteEmployeeById("employeetest");
        new DepartmentDaoImplementation().deleteDepartmentById("dep1");
    }
}