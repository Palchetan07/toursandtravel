package com.tourism.toursandtravel.Domains.service;

import com.tourism.toursandtravel.Domains.Region;
import com.tourism.toursandtravel.Domains.Repo.TourPackageRepository;
import com.tourism.toursandtravel.Domains.Repo.TourRepository;
import com.tourism.toursandtravel.Domains.Tour;
import com.tourism.toursandtravel.Domains.Difficulty;
import com.tourism.toursandtravel.Domains.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService (TourRepository tourRepository, TourPackageRepository tourPackageRepository){
        this.tourPackageRepository=tourPackageRepository;
        this.tourRepository = tourRepository;

    }



//Create a tour obj and persist it to the Database.


    public Tour creatTour( String title , String description , String blurb, Integer price, String duration , String bullets , String keywords , String tourPackageName , Difficulty difficulty, Region region){
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()-> new RuntimeException("Tour Package does not exist!" + tourPackageName));



        return tourRepository.save(new Tour(title,description,blurb,price,duration,bullets,keywords,tourPackage,difficulty,region));


    }

    // we are done with the injecting the services in repository - >
    // objects  are fulfill the contracts - >
    public long total(){

        return tourRepository.count();

    }


}
