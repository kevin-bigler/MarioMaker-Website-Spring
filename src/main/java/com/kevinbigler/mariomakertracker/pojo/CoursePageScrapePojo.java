package com.kevinbigler.mariomakertracker.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by Kevin on 3/12/2017.
 */
public class CoursePageScrapePojo {
    private String nintendoId;

    private String name;    // or "title"

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

    private PlayerPreviewPojo worldRecordHolder;

    private String worldRecordHolderNintendoId;

    private String worldRecordTime;

    private PlayerPreviewPojo firstClearPlayer;

    private String firstClearPlayerNintendoId;

    private List<PlayerPreviewPojo> recentPlayers;

    private List<String> recentPlayersNintendoIds;

    private List<PlayerPreviewPojo> clearedByPlayers;

    private List<String> clearedByPlayersNintendoIds;

    private List<PlayerPreviewPojo> starredByPlayers;

    private List<String> starredByPlayersNintendoIds;

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

    public String getDifficultyRank() {
        return difficultyRank;
    }

    public void setDifficultyRank(String difficultyRank) {
        this.difficultyRank = difficultyRank;
    }

    public Double getClearRate() {
        return clearRate;
    }

    public void setClearRate(Double clearRate) {
        this.clearRate = clearRate;
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

    public PlayerPreviewPojo getWorldRecordHolder() {
        return worldRecordHolder;
    }

    public void setWorldRecordHolder(PlayerPreviewPojo worldRecordHolder) {
        this.worldRecordHolder = worldRecordHolder;
    }

    public String getWorldRecordHolderNintendoId() {
        return worldRecordHolderNintendoId;
    }

    public void setWorldRecordHolderNintendoId(String worldRecordHolderNintendoId) {
        this.worldRecordHolderNintendoId = worldRecordHolderNintendoId;
    }

    public String getWorldRecordTime() {
        return worldRecordTime;
    }

    public void setWorldRecordTime(String worldRecordTime) {
        this.worldRecordTime = worldRecordTime;
    }

    public PlayerPreviewPojo getFirstClearPlayer() {
        return firstClearPlayer;
    }

    public void setFirstClearPlayer(PlayerPreviewPojo firstClearPlayer) {
        this.firstClearPlayer = firstClearPlayer;
    }

    public String getFirstClearPlayerNintendoId() {
        return firstClearPlayerNintendoId;
    }

    public void setFirstClearPlayerNintendoId(String firstClearPlayerNintendoId) {
        this.firstClearPlayerNintendoId = firstClearPlayerNintendoId;
    }

    public List<PlayerPreviewPojo> getRecentPlayers() {
        return recentPlayers;
    }

    public void setRecentPlayers(List<PlayerPreviewPojo> recentPlayers) {
        this.recentPlayers = recentPlayers;
    }

    public List<String> getRecentPlayersNintendoIds() {
        return recentPlayersNintendoIds;
    }

    public void setRecentPlayersNintendoIds(List<String> recentPlayersNintendoIds) {
        this.recentPlayersNintendoIds = recentPlayersNintendoIds;
    }

    public List<PlayerPreviewPojo> getClearedByPlayers() {
        return clearedByPlayers;
    }

    public void setClearedByPlayers(List<PlayerPreviewPojo> clearedByPlayers) {
        this.clearedByPlayers = clearedByPlayers;
    }

    public List<String> getClearedByPlayersNintendoIds() {
        return clearedByPlayersNintendoIds;
    }

    public void setClearedByPlayersNintendoIds(List<String> clearedByPlayersNintendoIds) {
        this.clearedByPlayersNintendoIds = clearedByPlayersNintendoIds;
    }

    public List<PlayerPreviewPojo> getStarredByPlayers() {
        return starredByPlayers;
    }

    public void setStarredByPlayers(List<PlayerPreviewPojo> starredByPlayers) {
        this.starredByPlayers = starredByPlayers;
    }

    public List<String> getStarredByPlayersNintendoIds() {
        return starredByPlayersNintendoIds;
    }

    public void setStarredByPlayersNintendoIds(List<String> starredByPlayersNintendoIds) {
        this.starredByPlayersNintendoIds = starredByPlayersNintendoIds;
    }
}
