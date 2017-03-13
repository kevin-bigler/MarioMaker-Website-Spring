package com.kevinbigler.mariomakertracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Kevin on 2/25/2017.
 */
@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nintendoId;

    private String name;

    private String nation;  // 2-character country code -- determines flag shown on MM website

    private String profileImageUid; // used as a prefix for accessing this player's profile images (various states: normal, happy, excited, etc)

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProfileImageUid() {
        return profileImageUid;
    }

    public void setProfileImageUid(String profileImageUid) {
        this.profileImageUid = profileImageUid;
    }
}
