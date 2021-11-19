package com.tiaot33;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class DandanScraperApplication {
    @Autowired
    ScraperAndMove scraperAndMove;

    public static void main(String[] args) {
        SpringApplication.run(DandanScraperApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000*60*60*12)
    public void sche(){
        scraperAndMove.scraperDirsAndMove();
    }

}

