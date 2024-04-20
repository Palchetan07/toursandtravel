package com.tourism.toursandtravel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.toursandtravel.Domains.Difficulty;
import com.tourism.toursandtravel.Domains.Region;
import com.tourism.toursandtravel.Domains.service.TourPackageService;
import com.tourism.toursandtravel.Domains.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class ToursandtravelApplication implements CommandLineRunner {


    @Autowired

    private TourPackageService tourPackageService;

    @Autowired
    private TourService tourService;

    public static void main(String[] args) {

        SpringApplication.run(ToursandtravelApplication.class, args);
    }


    @Override
    public void  run(String... args) throws Exception{


        createTourPackages();
        long numberOfPackage= tourPackageService.total();

        createTours();
        long noOfTours=tourService.total();




    }

    private  void  createTourPackages(){


        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");

    }



    /*
     * Create the tour entities from an external file.
     *
     * */


    private void createTours() throws IOException{
        TourFromFile.read("ExploreCalifornia.json").forEach(importedTour
                ->tourService.creatTour(
                importedTour.getTitle(),
                importedTour.getDescription(),
                importedTour.getBlurb(),
                importedTour.getPrice(),
                importedTour.getLength(),
                importedTour.getBullets(),
                importedTour.getBullets(),
                importedTour.getPackageType(),
                importedTour.getDifficulty(),
                importedTour.getRegion()));





    }


    /*Helper class to import the json file

     */

    private static class TourFromFile{


        private  String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;
        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport),
                            new TypeReference<List<TourFromFile>>() {});
        }



        protected  TourFromFile(){}


        String getPackageType(){return packageType;}
        String getTitle(){return title;}
        String getDescription(){return description;}
        String getBlurb(){return blurb;}

        String getLength(){return length;}
        String getBullets(){return bullets;}
        Integer getPrice(){
            return Integer.parseInt(price);

        }
        String getKeywords(){
            return keywords;

        }

        Difficulty getDifficulty(){return Difficulty.valueOf(difficulty);
        }

        Region getRegion(){return Region.findByLabel(region);
        }

    }


}
