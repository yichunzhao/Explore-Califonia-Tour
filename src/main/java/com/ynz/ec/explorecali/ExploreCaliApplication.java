package com.ynz.ec.explorecali;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynz.ec.explorecali.domain.Difficulty;
import com.ynz.ec.explorecali.domain.Region;
import com.ynz.ec.explorecali.services.TourPackageService;
import com.ynz.ec.explorecali.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExploreCaliApplication implements CommandLineRunner {
    @Autowired
    private TourPackageService tourPackageService;

    @Autowired
    private TourService tourService;

    public static void main(String[] args) {
        SpringApplication.run(ExploreCaliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("...Create the default tour packages");

        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");


/*
        TourFromFile.importTours().forEach(tour -> tourService.createTour(
                tour.title,
                tour.description,
                tour.blurb,
                Integer.parseInt(tour.price),
                tour.length,
                tour.bullets,
                tour.keywords,
                tour.packageType,
                Difficulty.valueOf(tour.difficulty),
                Region.findByLabel(tour.region)
        ));
*/

        //Persist the Tours to the database
        TourFromFile.importTours().forEach(t-> tourService.createTour(
                t.title,
                t.description,
                t.blurb,
                Integer.parseInt(t.price),
                t.length,
                t.bullets,
                t.keywords,
                t.packageType,
                Difficulty.valueOf(t.difficulty),
                Region.findByLabel(t.region)));

        tourPackageService.lookUp().forEach(tourPackage -> System.out.println(tourPackage));
        System.out.println("total tours : " + tourService.total());


    }

    /**
     * Helper class to import the records in the ExploreCalifornia.json
     */
    static class TourFromFile {
        //attributes as listed in the .json file
        private String packageType, title, description, blurb, price, length, bullets, keywords, difficulty, region;

        /**
         * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
         *
         * @return a List of TourFromFile objects.
         * @throws IOException if ObjectMapper unable to open file.
         */
        static List<TourFromFile> importTours() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                    readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"), new TypeReference<List<TourFromFile>>() {
                    });
        }
    }


}
