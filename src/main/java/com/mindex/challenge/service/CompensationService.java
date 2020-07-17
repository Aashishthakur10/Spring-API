package com.mindex.challenge.service;


import com.mindex.challenge.data.Compensation;

import java.util.List;

/**
 * Interface for task 2 of the coding challenge i.e. compensation,
 * here read and update functions are available for compensation
 *
 * @author aashishthakur at1948@rit.edu
 */
public interface CompensationService {

    Compensation create(Compensation compensation);
    Compensation read(String id);

}
