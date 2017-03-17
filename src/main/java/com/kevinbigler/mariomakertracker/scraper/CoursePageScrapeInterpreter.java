package com.kevinbigler.mariomakertracker.scraper;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.CourseSnapshot;
import com.kevinbigler.mariomakertracker.entity.Player;
import com.kevinbigler.mariomakertracker.entity.projection.CoursePreview;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin on 3/16/2017.
 */
@Service
public class CoursePageScrapeInterpreter {
    public Course getCourse(CoursePageScrapePojo coursePageScrape) {
        // TODO
        return null;
    }

    public CourseSnapshot getCourseSnapshot(CoursePageScrapePojo coursePageScrape) {
        // TODO
        return null;
    }

    public CoursePreview getCoursePreview(CoursePageScrapePojo coursePageScrape) {
        // TODO
        return null;
    }

    public List<Player> getPlayers(CoursePageScrapePojo coursePageScrape) {
        // TODO
        return null;
    }
}
