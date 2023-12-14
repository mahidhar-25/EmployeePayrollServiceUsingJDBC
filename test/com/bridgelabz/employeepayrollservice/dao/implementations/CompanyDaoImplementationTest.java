package com.bridgelabz.employeepayrollservice.dao.implementations;

import com.bridgelabz.employeepayrollservice.models.Company;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyDaoImplementationTest {
    private CompanyDaoImplementation companyDaoImplementation;

    @Before
    public void setUp() throws Exception {
        companyDaoImplementation = new CompanyDaoImplementation();
    }

    @Test

    public void aInsertNewCompanyItShouldUpdateDatabase() {
        Company company = new Company("company1" , "testCompany");
        companyDaoImplementation.insertNewCompany(company);
        Company updatedCompany = companyDaoImplementation.getCompanyByIdFromLocal("company1");
        assertEquals("testCompany" , updatedCompany.getCompanyName());
    }

    @Test
    public void bUpdateCompanyById() {
        Company company = new Company("company1" , "testCompany1");
        companyDaoImplementation.updateCompanyById("company1" , company);
        Company updatedCompany = companyDaoImplementation.getCompanyByIdFromLocal("company1");
        assertEquals("testCompany1" , updatedCompany.getCompanyName());
    }

    @Test
    public void cFindCompanyById() {
        Company company = companyDaoImplementation.findCompanyById("company1");
        assertEquals("testCompany1" , company.getCompanyName());

    }

    @Test
    public void dDeleteCompanyById() {
        companyDaoImplementation.deleteCompanyById("company1");
        Company company = companyDaoImplementation.findCompanyById("company1");
        assertNull(company);
    }



    @Test
    public void eGetAllCompanies() {
        assertEquals(2 , companyDaoImplementation.getAllCompanies().size());
    }
}