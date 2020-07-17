package com.mindex.challenge.data;
import java.util.List;

/**
 * Reporting Structure for employee consisting
 * their number of reports.
 *
 * @author aashishthakur at1948@rit.edu
 */
public class ReportingStructure {

    public ReportingStructure() {
    }

    private Employee employee;
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

}
