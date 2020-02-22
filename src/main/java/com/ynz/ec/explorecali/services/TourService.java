package com.ynz.ec.explorecali.services;

import com.ynz.ec.explorecali.domain.Difficulty;
import com.ynz.ec.explorecali.domain.Region;
import com.ynz.ec.explorecali.domain.Tour;
import com.ynz.ec.explorecali.domain.TourPackage;
import com.ynz.ec.explorecali.repo.TourPackageRepository;
import com.ynz.ec.explorecali.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration, String bullets,
                           String keywords, String tourPackageName, Difficulty difficulty, Region region) {

        //creating a tour that is associated with a tour package.(a tour package have multiple tours.)
        TourPackage found = tourPackageRepository.findByName(tourPackageName).orElseThrow(() -> new RuntimeException("the tour package does not exited"));

        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, found, difficulty, region));
    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public Long total() {
        return tourRepository.count();
    }


}
