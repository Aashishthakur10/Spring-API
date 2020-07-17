package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Interface for task 1 of the coding challenge i.e. Reporting structure,
 * here reporting management method is available to get a list of unique reports
 * under a given employee.
 *
 * @author aashishthakur at1948@rit.edu
 */
public interface ReportingStructureService {

    ReportingStructure getReport(String id);
}
