package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Test to create and retrive for reporting structure.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    private String employeeUrl;
    private String employeeIdUrl;
    private String reportingStructureIdUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {

        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Employee testEmp = new Employee();
        ArrayList<Employee> directreps = new ArrayList<>();
        testEmp.setFirstName("Paul");
        testEmp.setLastName("Doe");
        testEmp.setDepartment("Engineering");
        testEmp.setPosition("Junior Developer");


        Employee createdSubEmp = restTemplate.postForEntity(employeeUrl, testEmp, Employee.class).getBody();
        assertNotNull(createdSubEmp.getEmployeeId());


        directreps.add(createdSubEmp);
        testEmployee.setDirectReports(directreps);
        // Create checks
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        assertNotNull(createdEmployee.getEmployeeId());


        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class,
                createdEmployee.getEmployeeId()).getBody();
        assertEquals(reportingStructure.getEmployee().getEmployeeId(), createdEmployee.getEmployeeId());
        assertEquals(reportingStructure.getNumberOfReports(),1);
    }

}
