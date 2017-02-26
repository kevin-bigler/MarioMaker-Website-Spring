package com.kevinbigler.mariomakertracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Kevin on 2/25/2017.
 */
@Entity
public class CourseSnapshot {
    // TODO the fields - get from the PHP project

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
