package com.kevinbigler.mariomakertracker;

import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import com.kevinbigler.mariomakertracker.scraper.persistence.CoursePageScrapePersistence;
import com.kevinbigler.mariomakertracker.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kevin on 3/11/2017.
 */
@RestController
@RequestMapping("/scrape")
public class ScrapeController {

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private CoursePageScrapePersistence coursePageScrapePersistence;

    @RequestMapping(value = "/course/{nintendo_id}", method = RequestMethod.GET, produces = "application/json")
    public CoursePageScrapePojo scrapeCoursePage(@PathVariable("nintendo_id") String nintendoId) throws Exception {
        CoursePageScrapePojo coursePageScrape = scraperService.scrapeCoursePage(nintendoId);

        coursePageScrapePersistence.persist(coursePageScrape);

        return coursePageScrape;
//        return "scrapeCoursePage()";
    }

    @RequestMapping(value = "/course/{nintendo_id}", method = RequestMethod.GET, produces = "application/json")
    public CoursePageScrapePojo getLiveCourseData(@PathVariable("nintendo_id") String nintendoId) throws Exception {
        CoursePageScrapePojo coursePageScrape = scraperService.scrapeCoursePage(nintendoId);

        coursePageScrapePersistence.persist(coursePageScrape);

        return coursePageScrape;
//        return "scrapeCoursePage()";
    }
}
