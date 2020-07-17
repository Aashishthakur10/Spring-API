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

@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository comp;
    @Autowired
    private EmployeeRepository emp;


    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}] for Mr. [{}] [{}]", compensation,
                compensation.getEmployee().getFirstName(),compensation.getEmployee().getLastName());

        comp.insert(compensation);
        return compensation;
    }

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
