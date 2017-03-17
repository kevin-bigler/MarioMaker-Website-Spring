package com.kevinbigler.mariomakertracker.scraper.persistence;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.repository.CourseRepository;
import com.kevinbigler.mariomakertracker.entity.repository.PlayerRepository;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin on 3/16/2017.
 */
@Component
public class CoursePageScrapePersistence implements ScrapePersistence<CoursePageScrapePojo> {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void persist(CoursePageScrapePojo coursePageScrape) throws Exception {
        // TODO save the scraped data
        // 1. save the course
        // 2. save the course snapshot
        // 3. save the course preview (not sure if separate from course)
        // 4. save the players (player previews)

        saveCourse(coursePageScrape);
        saveCourseSnapshot(coursePageScrape);
        saveCoursePreview(coursePageScrape);
        savePlayers(coursePageScrape);
    }

    private void saveCourse(CoursePageScrapePojo coursePageScrape) {
        String nintendoId = coursePageScrape.getNintendoId();

        Course course = courseRepository.findByNintendoId(nintendoId);
        if (course == null) {
            course = new Course();
            course.setNintendoId(nintendoId);
        }

        // TODO other fields here
    }

    private void saveCourseSnapshot(CoursePageScrapePojo coursePageScrape) {
        // TODO
    }

    private void saveCoursePreview(CoursePageScrapePojo coursePageScrape) {
        // TODO
    }

    private void savePlayers(CoursePageScrapePojo coursePageScrape) {
        // TODO
    }
}
