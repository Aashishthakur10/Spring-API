package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;

//CompensationServiceImplTest

import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test to create and retrive compensation details.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compUrl;
    private String compIdUrl;

    @Autowired
    private CompensationService compService;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compUrl = "http://localhost:" + port + "/compensation";

        employeeUrl = "http://localhost:" + port + "/employee";
        compIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");


        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        assertNotNull(createdEmployee.getEmployeeId());

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(createdEmployee);
        testCompensation.setEffectiveDate("10/12/2020");
        testCompensation.setSalary(50000);


        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compUrl, testCompensation,
                Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compIdUrl, Compensation.class,
                createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertCompensationEquivalence(createdCompensation, readCompensation);


    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
