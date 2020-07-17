package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the service for Reporting structure.
 *
 * @author aashishthakur at1948@rit.edu
 */
@Service
public class ReportingStructureImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    HashSet<String> idList = new HashSet<>();

    /**
     * Gets employee information and number of reports.
     *
     *
     * @param id            Employee id to get the information along with number
     *                      of
     * @return              Reporting structure with the number of reports
     */
    @Override
    public ReportingStructure getReport(String id) {
        LOG.debug("Getting report structure for [{}]",id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        ReportingStructure report = new ReportingStructure();
        report.setEmployee(employee);
        idList.add(employee.getEmployeeId());
        report.setNumberOfReports(findNumberOfReports(employee));
        return report;
    }

    /**
     * Using depth first search figures out the total number of
     * reports. (Only works for valid data, not in case it has
     * a cycle.)
     *
     * @param employee              Employee details for whom the
     *                              query is made.
     * @return                      The number of reports.
     */
    public int findNumberOfReports(Employee employee){
        List<Employee> directReports = employee.getDirectReports();
        // In case there are no direct reports.
        if (directReports== null || directReports.size()==0){
            return 0;
        }
        int count = 0;
        // loop over every sub ordinate to find if they have any reports.
        for(Employee directReport:directReports){
                Employee emp = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                //Update the details in the parent structure about the reports.
                directReport.setPosition(emp.getPosition());
                directReport.setDepartment(emp.getDepartment());
                directReport.setFirstName(emp.getFirstName());
                directReport.setLastName(emp.getLastName());
                directReport.setDirectReports(emp.getDirectReports());
                // Increase count for every report and look for their reports.
                count += findNumberOfReports(emp) + 1;
            }
        return count;
    }
}
