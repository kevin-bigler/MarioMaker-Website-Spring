package com.kevinbigler.mariomakertracker;

import com.kevinbigler.mariomakertracker.common.DateUtils;
import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

/**
 * Created by Kevin on 3/3/2017.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/create-course/course-detail-page", method = RequestMethod.GET)
    @ResponseBody
    public String testCreateCourseFromCourseDetailPage() {
        String nintendoId = "DC0C-0000-02AD-6EBC";

        courseRepository.deleteByNintendoId(nintendoId);

        Course course = new Course();

        course.setNintendoId(nintendoId);
        course.setName("Old Dog, New Tricks");
        course.setCreatorNintendoId("thek3vinator");
//        course.setCreator(null);
        course.setMainImageUrl("https://dypqnhofrd2x2.cloudfront.net/DC0C-0000-02AD-6EBC.jpg");
        course.setFullImageUrl("https://dypqnhofrd2x2.cloudfront.net/DC0C-0000-02AD-6EBC_full.jpg");

        course.setUploadDate(DateUtils.createFromString("MM/dd/yyyy","10/17/2016"));
        course.setGameskin("SMW");
        course.setMiiverseCommentsUrl("https://miiverse.nintendo.net/posts/AYMHAAADAAB2V0fJp1azsw");
        course.setDifficultyRank("Expert");
        course.setClearRate(3.61D);
        course.setNumberStars(17);
        course.setNumberFootprints(84);
        course.setNumberShares(0);
        course.setNumberClears(9);
        course.setNumberAttempts(249);
        course.setNumberComments(8);
        course.setTag("---");
        course.setWorldRecordTime("04:51.955");
        course.setWorldRecordHolderNintendoId("EgixBacon");
//        course.setWorldRecordHolder(null);
        course.setFirstClearPlayerNintendoId("GeoKureli");
//        course.setFirstClearPlayer(null);
        course.setRecentPlayersNintendoIds("eaustinr,xxmegankayxx,muhi0217");
//        course.setRecentPlayers(null);
        course.setClearedByPlayersNintendoIds("awesome_world-25,Itsaname42,MeltyG");
//        course.setClearedByPlayers(null);
        course.setStarredByPlayersNintendoIds("cruzlucas,awesome_world-25,JToad1001");
//        course.setStarredByPlayers(null);
        course.setCreated(new Timestamp(System.currentTimeMillis()));
        course.setUpdated(null);

        courseRepository.save(course);

        return "testCreateCourseFromCourseDetailPage()";
    }

    @RequestMapping(value = "/delete-course/{nintendo_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String testDeleteCourseByNintendoId(@PathVariable("nintendo_id") String nintendoId) {
        if (courseRepository.findByNintendoId(nintendoId) == null) {
            return "no resource found";
        }

        courseRepository.deleteByNintendoId(nintendoId);

        if (courseRepository.findByNintendoId(nintendoId) == null) {
            return "resource deleted successfully";
        } else {
            return "failed to delete resource";
        }

    }

    @RequestMapping(value = "/scrape/course-page", method = RequestMethod.GET)
    @ResponseBody
    public String testScrapeCoursePage(@PathVariable("nintendo_id") String nintendoId) {
        // TODO
        return null;
    }
}
