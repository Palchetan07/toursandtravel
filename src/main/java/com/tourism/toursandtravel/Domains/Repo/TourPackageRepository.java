package com.tourism.toursandtravel.Domains.Repo;

import com.tourism.toursandtravel.Domains.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository  extends CrudRepository<TourPackage, String> {


    Optional<TourPackage> findByName(String name);

}
