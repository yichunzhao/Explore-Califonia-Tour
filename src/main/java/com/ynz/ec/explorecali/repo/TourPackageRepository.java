package com.ynz.ec.explorecali.repo;

import com.ynz.ec.explorecali.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
}
