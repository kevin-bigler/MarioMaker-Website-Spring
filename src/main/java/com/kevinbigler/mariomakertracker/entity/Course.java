package com.kevinbigler.mariomakertracker.entity;

import com.kevinbigler.mariomakertracker.entity.projection.PlayerPreview;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

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

    private String name;    // or "title"

    private PlayerPreview creator;

    private String creatorNintendoId;

    private String mainImageUrl;

    private String fullImageUrl;

    private Date uploadDate;

    private String gameskin;

    private String miiverseCommentsUrl;

    // fields whose values change over time

    private String difficultyRank;

    private Double clearRate;

    private Integer numberStars;

    private Integer numberFootprints;

    private Integer numberShares;

    private Integer numberClears;

    private Integer numberAttempts;

    private Integer numberComments;

    private String tag;

    private PlayerPreview worldRecordHolder;

    private String worldRecordHolderNintendoId;

    private String worldRecordTime;

    private PlayerPreview firstClearPlayer;

    private String firstClearPlayerNintendoId;

    private Set<PlayerPreview> recentPlayers;

    private String recentPlayersNintendoIds;

    private Set<PlayerPreview> clearedByPlayers;

    private String clearedByPlayersNintendoIds;

    private Set<PlayerPreview> starredByPlayers;

    private String starredByPlayersNintendoIds;

    private Timestamp created;

    private Timestamp updated;

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

    public PlayerPreview getCreator() {
        return creator;
    }

    public void setCreator(PlayerPreview creator) {
        this.creator = creator;
    }

    public String getCreatorNintendoId() {
        return creatorNintendoId;
    }

    public void setCreatorNintendoId(String creatorNintendoId) {
        this.creatorNintendoId = creatorNintendoId;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getFullImageUrl() {
        return fullImageUrl;
    }

    public void setFullImageUrl(String fullImageUrl) {
        this.fullImageUrl = fullImageUrl;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getGameskin() {
        return gameskin;
    }

    public void setGameskin(String gameskin) {
        this.gameskin = gameskin;
    }

    public String getMiiverseCommentsUrl() {
        return miiverseCommentsUrl;
    }

    public void setMiiverseCommentsUrl(String miiverseCommentsUrl) {
        this.miiverseCommentsUrl = miiverseCommentsUrl;
    }

    public PlayerPreview getWorldRecordHolder() {
        return worldRecordHolder;
    }

    public void setWorldRecordHolder(PlayerPreview worldRecordHolder) {
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

    public String getDifficultyRank() {
        return difficultyRank;
    }

    public void setDifficultyRank(String difficultyRank) {
        this.difficultyRank = difficultyRank;
    }

    public Integer getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(Integer numberStars) {
        this.numberStars = numberStars;
    }

    public Integer getNumberFootprints() {
        return numberFootprints;
    }

    public void setNumberFootprints(Integer numberFootprints) {
        this.numberFootprints = numberFootprints;
    }

    public Integer getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(Integer numberShares) {
        this.numberShares = numberShares;
    }

    public Integer getNumberClears() {
        return numberClears;
    }

    public void setNumberClears(Integer numberClears) {
        this.numberClears = numberClears;
    }

    public Integer getNumberAttempts() {
        return numberAttempts;
    }

    public void setNumberAttempts(Integer numberAttempts) {
        this.numberAttempts = numberAttempts;
    }

    public Integer getNumberComments() {
        return numberComments;
    }

    public void setNumberComments(Integer numberComments) {
        this.numberComments = numberComments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWorldRecordHolderNintendoId() {
        return worldRecordHolderNintendoId;
    }

    public void setWorldRecordHolderNintendoId(String worldRecordHolderNintendoId) {
        this.worldRecordHolderNintendoId = worldRecordHolderNintendoId;
    }

    public PlayerPreview getFirstClearPlayer() {
        return firstClearPlayer;
    }

    public void setFirstClearPlayer(PlayerPreview firstClearPlayer) {
        this.firstClearPlayer = firstClearPlayer;
    }

    public String getFirstClearPlayerNintendoId() {
        return firstClearPlayerNintendoId;
    }

    public void setFirstClearPlayerNintendoId(String firstClearPlayerNintendoId) {
        this.firstClearPlayerNintendoId = firstClearPlayerNintendoId;
    }

    public Set<PlayerPreview> getRecentPlayers() {
        return recentPlayers;
    }

    public void setRecentPlayers(Set<PlayerPreview> recentPlayers) {
        this.recentPlayers = recentPlayers;
    }

    public String getRecentPlayersNintendoIds() {
        return recentPlayersNintendoIds;
    }

    public void setRecentPlayersNintendoIds(String recentPlayersNintendoIds) {
        this.recentPlayersNintendoIds = recentPlayersNintendoIds;
    }

    public Set<PlayerPreview> getClearedByPlayers() {
        return clearedByPlayers;
    }

    public void setClearedByPlayers(Set<PlayerPreview> clearedByPlayers) {
        this.clearedByPlayers = clearedByPlayers;
    }

    public String getClearedByPlayersNintendoIds() {
        return clearedByPlayersNintendoIds;
    }

    public void setClearedByPlayersNintendoIds(String clearedByPlayersNintendoIds) {
        this.clearedByPlayersNintendoIds = clearedByPlayersNintendoIds;
    }

    public Set<PlayerPreview> getStarredByPlayers() {
        return starredByPlayers;
    }

    public void setStarredByPlayers(Set<PlayerPreview> starredByPlayers) {
        this.starredByPlayers = starredByPlayers;
    }

    public String getStarredByPlayersNintendoIds() {
        return starredByPlayersNintendoIds;
    }

    public void setStarredByPlayersNintendoIds(String starredByPlayersNintendoIds) {
        this.starredByPlayersNintendoIds = starredByPlayersNintendoIds;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
