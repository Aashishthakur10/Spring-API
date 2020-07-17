package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 *
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private com.mindex.challenge.service.ReportingStructureService ReportingStructureService;

    /**
     *
     *
     * @param id
     * @return
     */
    @GetMapping("numberOfReports/{id}")
    public ReportingStructure reportingManagement(@PathVariable String id){

        LOG.debug("Received number of reports request for id [{}]", id);
        return ReportingStructureService.getReport(id);
    }

}
