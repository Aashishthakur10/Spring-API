package com.mindex.challenge.service.impl;


import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation for Compensation includes logic
 * for creating a new compensation for an employee or
 * checking a list of compensations for an employee.
 *
 * @author aashishthakur at1948@rit.edu
 */
@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository comp;
    @Autowired
    private EmployeeRepository emp;

    /**
     * Create compensation for the provided employee and contains fields
     * employee, effective date and salary in the body.
     * @param compensation      Comprises of the fields mentioned above.
     * @return                  Compensation created.
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}] for [{}] [{}]", compensation,
                compensation.getEmployee().getFirstName(),compensation.getEmployee().getLastName());
        if (compensation.getEmployee().getEmployeeId()==null){
            compensation.getEmployee().setEmployeeId(UUID.randomUUID().toString());
        }
        comp.insert(compensation);
        return compensation;
    }

    /**
     *  Read the compensation for the employee.
     *
     * @param id        Employee id
     * @return          Compensation for the employee.
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Compensation for [{}]",id);
        Employee employee = emp.findByEmployeeId(id);
        if (employee==null){
            throw new RuntimeException("Invalid request for id: " + id);
        }
        Compensation compensation = comp.findByEmployee(employee);
        if (compensation == null) {
            throw new RuntimeException("Invalid request for id: " + id);
        }
        return compensation;
    }
}
