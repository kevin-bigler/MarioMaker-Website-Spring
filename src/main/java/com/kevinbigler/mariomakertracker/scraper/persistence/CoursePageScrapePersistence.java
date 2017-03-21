package com.kevinbigler.mariomakertracker.scraper.persistence;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.repository.CourseRepository;
import com.kevinbigler.mariomakertracker.entity.repository.PlayerRepository;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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
        // save the scraped data
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
        Course course = courseRepository.findByNintendoId(coursePageScrape.getNintendoId());
        if (course == null) {
            course = new Course();
            course.setNintendoId(coursePageScrape.getNintendoId());
            course.setCreated(new Timestamp(System.currentTimeMillis()));
        }

        // copies properties by name from coursePageScrape to course
        BeanUtils.copyProperties(coursePageScrape, course, "recentPlayersNintendoIds", "clearedByPlayersNintendoIds", "starredByPlayersNintendoIds");
        course.setRecentPlayersNintendoIds( StringUtils.join(coursePageScrape.getRecentPlayersNintendoIds(), ",") );
        course.setClearedByPlayersNintendoIds( StringUtils.join(coursePageScrape.getClearedByPlayersNintendoIds(), ",") );
        course.setStarredByPlayersNintendoIds( StringUtils.join(coursePageScrape.getStarredByPlayersNintendoIds(), ",") );
        course.setUpdated(new Timestamp(System.currentTimeMillis()));

        courseRepository.save(course);
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
