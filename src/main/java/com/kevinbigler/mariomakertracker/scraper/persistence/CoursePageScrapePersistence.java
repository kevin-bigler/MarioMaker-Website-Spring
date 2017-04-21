package com.kevinbigler.mariomakertracker.scraper.persistence;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.CourseSnapshot;
import com.kevinbigler.mariomakertracker.entity.Player;
import com.kevinbigler.mariomakertracker.entity.repository.CourseRepository;
import com.kevinbigler.mariomakertracker.entity.repository.CourseSnapshotRepository;
import com.kevinbigler.mariomakertracker.entity.repository.PlayerRepository;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import com.kevinbigler.mariomakertracker.pojo.PlayerPreviewPojo;
import com.kevinbigler.mariomakertracker.service.CourseService;
import com.kevinbigler.mariomakertracker.service.PlayerService;
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

    @Autowired
    CourseSnapshotRepository courseSnapshotRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    CourseService courseService;

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
        saveRelationships(coursePageScrape);
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
        CourseSnapshot courseSnapshot = courseSnapshotRepository.findByNintendoId(coursePageScrape.getNintendoId());
        if (courseSnapshot == null) {
            courseSnapshot = new CourseSnapshot();
            courseSnapshot.setNintendoId(coursePageScrape.getNintendoId());
            courseSnapshot.setCreated(new Timestamp(System.currentTimeMillis()));
        }

        // copies properties by name from coursePageScrape to courseSnapshot
        BeanUtils.copyProperties(coursePageScrape, courseSnapshot, "recentPlayersNintendoIds", "clearedByPlayersNintendoIds", "starredByPlayersNintendoIds");
        courseSnapshot.setRecentPlayersNintendoIds( StringUtils.join(coursePageScrape.getRecentPlayersNintendoIds(), ",") );
        courseSnapshot.setClearedByPlayersNintendoIds( StringUtils.join(coursePageScrape.getClearedByPlayersNintendoIds(), ",") );
        courseSnapshot.setStarredByPlayersNintendoIds( StringUtils.join(coursePageScrape.getStarredByPlayersNintendoIds(), ",") );
        courseSnapshot.setUpdated(new Timestamp(System.currentTimeMillis()));

        courseSnapshotRepository.save(courseSnapshot);
    }

    private void saveCoursePreview(CoursePageScrapePojo coursePageScrape) {
        // TODO
        // will this be a separate table, or a subset of info from the Course table?
    }

    private void savePlayers(CoursePageScrapePojo coursePageScrape) {
        // save each player that we have info for
        // - creator, first clear, world record holder
        // - starred by, cleared by, recent players
        playerService.savePlayer(coursePageScrape.getCreator());
        playerService.savePlayer(coursePageScrape.getFirstClearPlayer());
        playerService.savePlayer(coursePageScrape.getWorldRecordHolder());

        if (coursePageScrape.getStarredByPlayers() != null) {
            coursePageScrape.getStarredByPlayers().forEach(player -> playerService.savePlayer(player));
        }
        if (coursePageScrape.getClearedByPlayers() != null) {
            coursePageScrape.getClearedByPlayers().forEach(player -> playerService.savePlayer(player));
        }
        if (coursePageScrape.getRecentPlayers() != null) {
            coursePageScrape.getRecentPlayers().forEach(player -> playerService.savePlayer(player));
        }
    }

    private void saveRelationships(CoursePageScrapePojo coursePageScrape) {
        // TODO save the relationship of this course and coursesnapshot to those players
    }
}
