package com.ynz.ec.explorecali.web;

import com.ynz.ec.explorecali.domain.Tour;
import com.ynz.ec.explorecali.domain.TourRating;
import com.ynz.ec.explorecali.domain.TourRatingPK;
import com.ynz.ec.explorecali.repo.TourRatingRepository;
import com.ynz.ec.explorecali.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/tours")
public class TourRatingController {
    private TourRatingRepository tourRatingRepository;
    private TourRepository tourRepository;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    //what difference?
    //@Validated: Spring annotation
    //@Valid: java annotation
    @PostMapping("/{tourId}/ratings")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTourRating(@PathVariable("tourId") Integer tourId, @RequestBody @Validated RatingDto ratingDto) {
        Tour tour = verifyTourId(tourId);

        tourRatingRepository.save(new TourRating(new TourRatingPK(tour, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()));
    }


    private Tour verifyTourId(Integer tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new TourNotFound("tourId is not found."));
    }

    @ExceptionHandler(TourNotFound.class)
    public String returnNotFound(NoSuchElementException e) {
        return e.getMessage();
    }


}
