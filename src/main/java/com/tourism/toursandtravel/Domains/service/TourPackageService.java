package com.tourism.toursandtravel.Domains.service;

import com.tourism.toursandtravel.Domains.Repo.TourPackageRepository;
import com.tourism.toursandtravel.Domains.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService
{
    private TourPackageRepository tourPackageRepository;


    @Autowired   //autowired to the constructor!
    public TourPackageService(TourPackageRepository tourPackageRepository){
        this.tourPackageRepository = tourPackageRepository;

    }


    public TourPackage createTourPackage(String code , String name){

        return tourPackageRepository.findById(code)
                .orElse(tourPackageRepository.save( new TourPackage(code, name)));

    }


    // For look up of all the packages which are there in the system !


    public Iterable<TourPackage> lookup(){
        return  tourPackageRepository.findAll();

    }

    public long total(){ return tourPackageRepository.count();
    }

}
