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

import java.util.AbstractMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tours/{tourId}/ratings")
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
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTourRating(@PathVariable("tourId") Integer tourId, @RequestBody @Validated RatingDto ratingDto) {
        Tour tour = verifyTourId(tourId);

        tourRatingRepository.save(new TourRating(new TourRatingPK(tour, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()));
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(@PathVariable("tourId") Integer tourId) {
        Tour tour = verifyTourId(tourId);

        return tourRatingRepository.findByRatingPKTourId(tourId)
                .stream()
                .map(r -> new RatingDto(r.getScore(), r.getComment(), r.getRatingPK().getCustomerId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/average")
    public AbstractMap.SimpleEntry getAverageRatingForTour(@PathVariable("tourId") Integer tourId) {

        OptionalDouble average = tourRatingRepository.findByRatingPKTourId(tourId).stream().mapToInt(r -> r.getScore()).average();

        return new AbstractMap.SimpleEntry<String, Double>("average:", average.isPresent() ? average.getAsDouble() : null);
    }

    private Tour verifyTourId(Integer tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new TourNotFoundException("tourId is not found."));
    }

    //@ResponseStatus is used on the method level;
    @ExceptionHandler(TourNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String returnNotFound(TourNotFoundException e) {
        return e.getMessage();
    }


}
