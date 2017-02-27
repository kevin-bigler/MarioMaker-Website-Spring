package com.kevinbigler.mariomakertracker;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.projection.CoursePreview;
import com.kevinbigler.mariomakertracker.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Kevin on 2/19/2017.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "/courses", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(path = "/courses/{nintendo_id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Course getCourseByNintendoId(@PathVariable(name = "nintendo_id") String nintendoId) {
        return courseService.getCourseByNintendoId(nintendoId);
    }

    // /courses/by-clear-rate/{from}/{to}
    @RequestMapping(path = "/courses/by-clear-rate/{from:\\d+[\\.\\d+]*}/{to:\\d+[\\.\\d+]*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCoursesByClearRateBetween(@PathVariable(name = "from") Double from, @PathVariable(name = "to") Double to) throws Exception {
        return courseService.getCoursesByClearRateBetween(from, to);
    }

    @RequestMapping(path = "/regex-test", method = RequestMethod.GET)
    public String regexTest() {
        String regex = "\\d+[\\.\\d+]*";
        List<String> testValues = Stream.of("1", "10", "100", "1.0", "10.0", "100.0", "1.00", "10.00", "100.00").collect(Collectors.toList());

        System.out.println("regex: " + regex);
        testValues.forEach(val -> System.out.println("test: " + val + " => " + (val.matches(regex) ? "yes" : "no")));

        return "regexTest()";
    }

    @RequestMapping(path = "/course-previews", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CoursePreview> getAllCoursePreviews() {
        return courseService.getAllCoursePreviews();
    }

    @RequestMapping(path = "/course-previews/{nintendo_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CoursePreview getCoursePreviewByNintendoId(@PathVariable(name = "nintendo_id") String nintendoId) {
        return courseService.getCoursePreviewByNintendoId(nintendoId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIllegalArgumentException(IllegalArgumentException e) {
//        e.printStackTrace();
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

    @RequestMapping("/courses/create-test/{nintendo_id}")
    public void coursesCreateTest(@PathVariable("nintendo_id") String nintendoId) {
        courseService.createTest(nintendoId);
    }

    @RequestMapping("/courses/update-test")
    public void coursesUpdateTest() {
        courseService.updateTest();
    }
}
