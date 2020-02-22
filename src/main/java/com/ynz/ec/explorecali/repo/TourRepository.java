package com.ynz.ec.explorecali.repo;

import com.ynz.ec.explorecali.domain.Difficulty;
import com.ynz.ec.explorecali.domain.Region;
import com.ynz.ec.explorecali.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {

    //using named queried methods(filter keywords)
    //List<Tour> findByTourPackageCode(String code);

    Tour findByTitle(String title); //title is unique

    List<Tour> findByPrice(Integer price);

    Collection<Tour> findByDifficulty(Difficulty difficulty);

    List<Tour> findByRegion(Region region);

    //Intermediate Query method
    List<Tour> findByTourPackageCodeAndRegion(String code, Region region);

    List<Tour> findByRegionIn(List<Region> regions);

    List<Tour> findByPriceLessThan(Integer maxPrice);

    List<Tour> findByKeywordsContains(String keyword);

    List<Tour> findByTourPackageCodeAndBulletsLike(String code, String searchString);


    @Query("Select t From Tour t Where t.tourPackage.code = ?1 And t.difficulty = ?2 And t.region = ?3 And t.price <=?4")
    List<Tour> lookupTours(String code, Difficulty difficulty, Region region, Integer maxPrice);
    //the above is equivalent to the following:
    //List<Tour> findByTourPackageCodeAndDifficultyAndRegionAndPriceLessThan(String code, Difficulty difficulty, Region region, Integer maxPrice);


    //paging and sorting: URL path parameters
    //Size: the size of the pages ? size=3 default = 20
    //page: the page number (0 is 1st) ? page = 2 default = 0
    //sort: the sort by attribute ? sort = title default = entity id asc or desc

    Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Tour> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Tour tour);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Tour> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
