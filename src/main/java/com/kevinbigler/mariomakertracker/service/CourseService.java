package com.kevinbigler.mariomakertracker.service;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.projection.CoursePreview;
import com.kevinbigler.mariomakertracker.entity.repository.CoursePreviewRepository;
import com.kevinbigler.mariomakertracker.entity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin on 2/19/2017.
 */
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CoursePreviewRepository coursePreviewRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByNintendoId(String nintendoId) {
        return courseRepository.findByNintendoId(nintendoId);
    }

    public List<Course> getCoursesByClearRateBetween(Double from, Double to) throws Exception {
        if (from == null || to == null)
            throw new IllegalArgumentException("From and To must be given.");
        if (from < 0.00D || to > 100.0D)
            throw new IllegalArgumentException("From and To must be within a 0 to 100 range.");
        from = from * 0.01D;
        to = to * 0.01D;

        return courseRepository.findByClearRateBetween(from, to);
    }

    public CoursePreview getCoursePreviewByNintendoId(String nintendoId) {
        return coursePreviewRepository.findByNintendoId(nintendoId);
    }

    public List<CoursePreview> getAllCoursePreviews() {
        return coursePreviewRepository.findAllProjectedBy();
    }
}
