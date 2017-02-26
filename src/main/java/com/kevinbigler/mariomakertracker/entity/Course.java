package com.kevinbigler.mariomakertracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Kevin on 2/19/2017.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nintendoId;

    private String name;

    private String imageUrl;

    private String style;

    private String worldRecordHolder;

    private String worldRecordTime;

    private Double clearRate;

    private Integer starCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNintendoId() {
        return nintendoId;
    }

    public void setNintendoId(String nintendoId) {
        this.nintendoId = nintendoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getWorldRecordHolder() {
        return worldRecordHolder;
    }

    public void setWorldRecordHolder(String worldRecordHolder) {
        this.worldRecordHolder = worldRecordHolder;
    }

    public String getWorldRecordTime() {
        return worldRecordTime;
    }

    public void setWorldRecordTime(String worldRecordTime) {
        this.worldRecordTime = worldRecordTime;
    }

    public Double getClearRate() {
        return clearRate;
    }

    public void setClearRate(Double clearRate) {
        this.clearRate = clearRate;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }
}
