package com.kevinbigler.mariomakertracker.entity;

import javax.persistence.*;

/**
 * Created by Kevin on 2/25/2017.
 */
@Entity
public class PlayerPreview {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nintendoId;

    private String name;

    private String nation;  // 2-character code. used to display flag

    private String profileImagePrefix;  // UID that can be used to find any profile image for the player

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

    public String getProfileImagePrefix() {
        return profileImagePrefix;
    }

    public void setProfileImagePrefix(String profileImagePrefix) {
        this.profileImagePrefix = profileImagePrefix;
    }
}