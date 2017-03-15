package com.kevinbigler.mariomakertracker.service;

import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import com.kevinbigler.mariomakertracker.scraper.CoursePageScraper;
import com.kevinbigler.mariomakertracker.scraper.Scraper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 3/11/2017.
 */
@Service
public class ScraperService {
//    @Value("${mariomakertracker.scrape-url.course-prefix}")
//    private String scrapeUrlCoursePrefix;

    public CoursePageScrapePojo scrapeCoursePage(String nintendoId) throws Exception {
        Scraper scraper = new CoursePageScraper(nintendoId);
        return (CoursePageScrapePojo) scraper.scrape();
    }
}
