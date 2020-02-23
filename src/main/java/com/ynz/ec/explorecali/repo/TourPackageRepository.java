package com.ynz.ec.explorecali.repo;

import com.ynz.ec.explorecali.domain.TourPackage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends PagingAndSortingRepository<TourPackage, String> {

    //Controlling API exposure, so that the public is not allowed to modify the database.
    //Class annotation;  @RepositoryRestResource(exported = false)
    //method level; @RestResource(exported=false)
    Optional<TourPackage> findByName(String name);

    Optional<TourPackage> findByCode(String code);

    @Override
    @RestResource(exported = false)
    <S extends TourPackage> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(TourPackage tourPackage);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TourPackage> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
