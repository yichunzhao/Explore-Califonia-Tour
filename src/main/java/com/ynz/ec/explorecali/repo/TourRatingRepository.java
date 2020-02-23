package com.ynz.ec.explorecali.repo;

import com.ynz.ec.explorecali.domain.TourRating;
import com.ynz.ec.explorecali.domain.TourRatingPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Tour rating repository interface
 */

@RepositoryRestResource(exported = false)//we don't expose on the spring data rest
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPK> {
    /**
     * Lookup all the tourRatings for a tour.
     *
     * @param tourId is the tour Id
     * @return a lit of any found {@link TourRating}
     */
    List<TourRating> findByRatingPKTourId(Integer tourId);


    /**
     * Lookup a TourRating by the TourId and CustomerId
     *
     * @param tourId
     * @param customerId
     * @return TourRating if found
     */
    Optional<TourRating> findByRatingPKTourIdAndRatingPKCustomerId(Integer tourId, Integer customerId);

}
