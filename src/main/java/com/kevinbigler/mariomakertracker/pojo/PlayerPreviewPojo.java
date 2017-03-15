package com.kevinbigler.mariomakertracker.pojo;

/**
 * Created by Kevin on 3/12/2017.
 */
public class PlayerPreviewPojo implements ScrapePojo {

    private String nintendoId;

    private String name;

    private String nation;  // 2-character country code -- determines flag shown on MM website

    private String profileImageUid; // used as a prefix for accessing this player's profile images (various states: normal, happy, excited, etc)

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
