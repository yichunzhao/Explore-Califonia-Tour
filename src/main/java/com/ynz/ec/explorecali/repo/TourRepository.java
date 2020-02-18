package com.ynz.ec.explorecali.repo;

import com.ynz.ec.explorecali.domain.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {
}
