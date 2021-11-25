package com.tiaot33;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitSchedule implements ApplicationRunner {
    @Autowired
    ScraperAndMove scraperAndMove;
    @Override
    //@Scheduled(fixedDelay = 1000*60*60*12)
    public void run(ApplicationArguments args) throws Exception {
        Thread.sleep(10000);
        scraperAndMove.scraperDirsAndMove();
    }
}
