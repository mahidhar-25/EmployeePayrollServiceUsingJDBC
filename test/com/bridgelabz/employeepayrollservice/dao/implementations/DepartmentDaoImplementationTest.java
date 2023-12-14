package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.models.Department;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentDaoImplementationTest {
    private DepartmentDaoImplementation departmentDaoImplementation;

    @Before
    public void setUp() throws Exception {
         departmentDaoImplementation = new DepartmentDaoImplementation();
    }

    @Test
    public void insertNewDepartmentTestShouldUpdateTheDataBase() {
        Department department = new Department("dep1" , "Administration");
        departmentDaoImplementation.insertNewDepartment(department);
        Department updatedDepartment = departmentDaoImplementation.getDepartmentByIdFromLocal("dep1");
        assertEquals("Administration" , updatedDepartment.getDepartmentName());
    }

    @Test
    public void updateDepartmentByIdShouldUpdateInDatabaseAndLocally() {
        Department department = new Department("dep1" , "SalesAdministration");
        departmentDaoImplementation.updateDepartmentById("dep1" , department);
        Department updatedDepartment = departmentDaoImplementation.getDepartmentByIdFromLocal("dep1");
        assertEquals("SalesAdministration" , updatedDepartment.getDepartmentName());
    }

    @Test
    public void findDepartmentByIdItShoudReturnCorrectDepartment() {
      Department department = departmentDaoImplementation.findDepartmentById("dep1");
      assertEquals("SalesAdministration" , department.getDepartmentName());
    }


    @Test
    public void deleteDepartmentByIdShouldDeleteInDatabase() {
        departmentDaoImplementation.deleteDepartmentById("dep1" );
        Department updatedDepartment = departmentDaoImplementation.getDepartmentByIdFromLocal("dep1");
        assertNull(updatedDepartment);
    }

    @Test
    public void getAllDepartmentsShouldMatchCountSize() {
        assertEquals(2 , departmentDaoImplementation.getAllDepartments().size());

    }

}