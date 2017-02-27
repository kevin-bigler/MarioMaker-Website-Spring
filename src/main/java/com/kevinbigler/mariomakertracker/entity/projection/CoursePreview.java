package com.kevinbigler.mariomakertracker.entity.projection;

import com.kevinbigler.mariomakertracker.entity.Course;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Kevin on 2/26/2017.
 */
//@Projection(name = "courses.summary", types = Course.class)
public interface CoursePreview {
    public Long getId();
    public String getNintendoId();
    public String getName();
}
