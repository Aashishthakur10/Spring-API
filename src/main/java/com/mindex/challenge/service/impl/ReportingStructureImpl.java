package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 *
 *
 */
@Service
public class ReportingStructureImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     *
     *
     * @param id
     * @return
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
        report.setNumberOfReports(findNumberOfReports(employee));

        return report;
    }

    /**
     *
     *
     *
     * @param employee
     * @return
     */
    public int findNumberOfReports(Employee employee){
        List<Employee> directReports = employee.getDirectReports();
        if (directReports== null || directReports.size()==0){
            return 0;
        }
        int count = 0;
        for(Employee directReport:directReports){
            count+= findNumberOfReports(employeeRepository.findByEmployeeId(directReport.getEmployeeId()))+1;
        }
        return count;
    }
}
