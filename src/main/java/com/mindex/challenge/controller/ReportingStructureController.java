package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Class for task 1 of the coding challenge i.e. Reporting structure,
 * here reporting management method is available to get a list of
 * unique reports under a given employee.
 *
 * @author aashishthakur at1948@rit.edu
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private com.mindex.challenge.service.ReportingStructureService ReportingStructureService;

    /**
     * For a given employee id, returns the list of unique reports.
     *
     * @param id            Employee id
     * @return              Reporting structure which contains employee
     *                      info along with list of reports.
     */
    @GetMapping("numberOfReports/{id}")
    public ReportingStructure reportingManagement(@PathVariable String id){

        LOG.debug("Received number of reports request for id [{}]", id);
        return ReportingStructureService.getReport(id);
    }

}
