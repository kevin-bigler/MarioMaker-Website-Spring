package com.kevinbigler.mariomakertracker.entity.repository;

import com.kevinbigler.mariomakertracker.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kevin on 2/19/2017.
 */
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findAllByNameIgnoreCase(String name);
    List<Course> findAllByNameIgnoreCaseContaining(String name);
    Course findByNintendoId(String nintendoId);
    List<Course> findByClearRateBetween(Double from, Double to);

    void deleteByNintendoId(String nintendoId);
}
