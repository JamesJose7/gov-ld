package com.jeeps.ckan_extractor;

import com.jeeps.ckan_extractor.controller.CkanExtractor;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Instant start = Instant.now();
        CkanExtractor ckanExtractor = new CkanExtractor();
        //ckanExtractor.extract("http://ambar.utpl.edu.ec/api/action/");
//        chowkanExtractor.extract("http://data.europa.eu/euodp/data/api/action/");
//        ckanExtractor.extractByPost("http://data.europa.eu/euodp/data/api/3/action/");
//        ckanExtractor.extract("https://data.humdata.org/api/3/action/");
        ckanExtractor.extract("https://opendata.swiss/api/3/action/");
//        ckanExtractor.extract("https://data.gov.au/api/3/action/");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Elapsed time:\n" +
                "millis: %d\n" +
                "seconds: %d\n", timeElapsed, (timeElapsed / 1000));

//        SemanticCreator semanticCreator = new SemanticCreator();
//        semanticCreator.convertToDcat();
    }
}
