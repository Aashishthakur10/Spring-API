package com.mindex.challenge.data;

/**
 * Class for task 2 of the coding challenge i.e. compensation,
 * here salary and effective date for each employee
 * are available for compensation details
 *
 * @author aashishthakur
 */
public class Compensation {
    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation(){

    }

    @Override
    public String toString() {
        return "Compensation for employee " + employee.getFirstName() +" "+ employee.getLastName()
                + ", salary : " + salary + ", and effective date = " + effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getSalary() {
        return salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
