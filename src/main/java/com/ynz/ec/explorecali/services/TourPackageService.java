package com.ynz.ec.explorecali.services;


import com.ynz.ec.explorecali.domain.TourPackage;
import com.ynz.ec.explorecali.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        TourPackage saved = null;

        if (!tourPackageRepository.existsById(code)) {
            saved = tourPackageRepository.save(new TourPackage(code, name));
        }

        return saved;
    }

    public Iterable<TourPackage> lookUp() {
        return tourPackageRepository.findAll();
    }

    public long total(){
        return tourPackageRepository.count();
    }


}
