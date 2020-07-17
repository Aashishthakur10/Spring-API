package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for task 2 of the coding challenge i.e. compensation,
 * here read and update functions are available for compensation
 *
 * @author aashishthakur
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    List<Compensation> findByEmployee(Employee employee);
}
