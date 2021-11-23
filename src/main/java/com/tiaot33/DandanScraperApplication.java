package com.tiaot33;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
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
        ConfigurableApplicationContext run = SpringApplication.run(DandanScraperApplication.class, args);
        PathProperty bean = run.getBean(PathProperty.class);
        System.out.println(bean.getTarget());
        System.out.println(bean.getSource());
    }

    @SneakyThrows
    //@Scheduled(fixedDelay = 1000*60*60*12)
    public void sche(){
        scraperAndMove.scraperDirsAndMove();
    }

}

