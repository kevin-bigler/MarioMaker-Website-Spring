package com.kevinbigler.mariomakertracker.entity.repository;

import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.entity.projection.CoursePreview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kevin on 2/26/2017.
 */
public interface CoursePreviewRepository extends CrudRepository<Course, Long> {

//    @Query("SELECT c.id as id, c.nintendoId as nintendoId, c.name as name FROM Course c")
//    List<CoursePreview> findAllPreviews();

    List<CoursePreview> findAllProjectedBy();
    List<CoursePreview> findAllByNameIgnoreCase(String name);
    List<CoursePreview> findAllByNameIgnoreCaseContaining(String name);
    CoursePreview findByNintendoId(String nintendoId);
}
