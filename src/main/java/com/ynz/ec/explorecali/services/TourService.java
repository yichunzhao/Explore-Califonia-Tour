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
                           String keywords, String tourPackageCode, Difficulty difficulty, Region region) {

        TourPackage found = tourPackageRepository.findById(tourPackageCode).orElseThrow(() -> new RuntimeException("the tour package does not exited"));

        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, found, difficulty, region));
    }

}
