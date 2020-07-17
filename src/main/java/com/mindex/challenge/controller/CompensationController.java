package com.mindex.challenge.controller;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class for task 2 of the coding challenge i.e. compensation,
 * here read and update functions are available for compensation
 *
 * @author aashishthakur at1948@rit.edu
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Creates a new compensation for the employee, this requires
     * employee details along with effective date and salary.
     *
     * @param compensation        compensation is to be passed in
     *                            the body of the endpoint and contains
     *                            employee details with salary and
     *                            effective date.
     *
     * @return                    compensation created.
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Request to create compensation for [{}]", compensation);
        return compensationService.create(compensation);
    }

    /**
     * Reads the list of compensations for the given employee
     * id, if any available.
     *
     *
     * @param id            Employee id
     * @return              List of compensation for the employee.
     */
    @GetMapping("/compensation/{id}")
    public List<Compensation> read(@PathVariable String id) {
        LOG.debug("Reading compensation for id [{}]", id);
        return compensationService.read(id);
    }


}
