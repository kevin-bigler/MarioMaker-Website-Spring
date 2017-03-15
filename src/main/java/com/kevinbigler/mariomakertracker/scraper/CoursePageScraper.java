package com.kevinbigler.mariomakertracker.scraper;

import com.google.common.collect.ImmutableMap;
import com.kevinbigler.mariomakertracker.common.DateUtils;
import com.kevinbigler.mariomakertracker.common.MMUtils;
import com.kevinbigler.mariomakertracker.common.RestUtils;
import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;
import com.kevinbigler.mariomakertracker.exception.MissingScrapeValueException;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Created by Kevin on 3/11/2017.
 */
public class CoursePageScraper implements Scraper {
    private String nintendoId;
    private Course scrapedCourse;

    private static String COURSE_URL_PREFIX = "https://supermariomakerbookmark.nintendo.net/courses/";

    public CoursePageScraper(String nintendoId) {
        this.nintendoId = nintendoId;
    }

    @Override
    public void scrape() throws Exception {
        // check that the nintendoId is not null
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set");
        }

        // check that the nintendoId is properly formatted
        if ( ! MMUtils.isCourseNintendoIdValid(nintendoId) ) {
            throw new IllegalArgumentException("Nintendo ID is in an invalid format. Nintendo ID: " + nintendoId);
        }

        RestUtils restUtils = new RestUtils();
        ResponseEntity<String> responseEntity = restUtils.getAsText(getCourseUrl());

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new CourseNotFoundException("Course does not exist on MarioMaker website");
        }
        else if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new CourseNotFoundException("Course URL returned an unexpected HTTP Status Code: " + responseEntity.getStatusCode().toString());
        }

        String html = responseEntity.getBody();
        Document document = Jsoup.parse(html);
        document.setBaseUri(getCourseUrl());

        CoursePageScrapePojo coursePageScrape = new CoursePageScrapePojo();

        ScrapeHelper scrapeHelper = new ScrapeHelper();

        coursePageScrape.setNintendoId(nintendoId);

        // player_nintendo_id
        coursePageScrape.setCreatorNintendoId(scrapeHelper.getPlayerNintendoIdFromProfileLink( document.select(".creator a#mii") ));

        // player_info
//        coursePageScrape.setPlayerInfo(scrapeHelper.getPlayerBasicInfo( document.select(".course-detail-wrapper") ));

        // title
        coursePageScrape.setName(scrapeHelper.firstElementText( document.select(".course-title") ));

        // image_url
        coursePageScrape.setMainImageUrl(scrapeHelper.firstElementAttribute( document.select("img.course-image"), "src" ));

        // image_full_url
        coursePageScrape.setFullImageUrl(scrapeHelper.firstElementAttribute( document.select("img.course-image-full"), "src" ));

        // upload_date
        String uploadDateSource = scrapeHelper.firstElementText( document.select(".created_at") );
        // example: 10/17/2016
        Date uploadDate = DateUtils.createFromString("m/d/Y", uploadDateSource);
        coursePageScrape.setUploadDate(uploadDate);

        // gameskin
        // -- mode (SMB1, SMB2, SMW, NSMB)
        coursePageScrape.setGameskin(getGameskin(document));

        // image_full_url
        coursePageScrape.setMiiverseCommentsUrl(scrapeHelper.firstElementAttribute( document.select(".course-detail-wrapper a.miiverse"), "href" ));

        // -------------------------------------------------------------------------------------------------------------
        // data that changes over time
        // -------------------------------------------------------------------------------------------------------------

        // player_info
//        coursePageScrape.setPlayerInfo(scrapeHelper.getPlayerBasicInfo( document.select(".course-detail-wrapper") );

        // difficulty_rank
        coursePageScrape.setDifficultyRank(scrapeHelper.firstElementText( document.select(".course-header") ));

        // clear_rate
        coursePageScrape.setClearRate(scrapeHelper.getTypographyNumberAsDouble( document.select(".clear-rate") ));

        // number_stars
        coursePageScrape.setNumberStars(scrapeHelper.getTypographyNumberAsInteger( document.select(".liked-count") ));

        // number_footprints
        coursePageScrape.setNumberFootprints(scrapeHelper.getTypographyNumberAsInteger( document.select(".played-count") ));

        // number_shares
        coursePageScrape.setNumberShares(scrapeHelper.getTypographyNumberAsInteger( document.select(".shared-count") ));

        // number_clears
        String clearRatio = scrapeHelper.getTypographyNumber( document.select(".tried-count") );
        coursePageScrape.setNumberClears(Integer.valueOf(StringUtils.substringBefore(clearRatio, "/")));

        // number_attempts
        coursePageScrape.setNumberAttempts(Integer.valueOf(StringUtils.substringAfter(clearRatio, "/")));

        // number_comments
        coursePageScrape.setNumberComments(scrapeHelper.getTypographyNumberAsInteger( document.select(".comment-count") ));

        // tag
        coursePageScrape.setTag(scrapeHelper.firstElementText( document.select(".course-tag") ));

        // world_record_player_id
        // this is only relevant if the player is in our database -- handle it somewhere else (it"s not parsed data)

        // world_record_player_nintendo_id
        coursePageScrape.setWorldRecordHolderNintendoId(scrapeHelper.getPlayerNintendoIdFromProfileLink( document.select(".fastest-time-wrapper a#mii") ));

        // world_record_player_info
//        coursePageScrape.setWorldRecordPlayerInfo(scrapeHelper.getPlayerBasicInfo( document.select(".fastest-time-wrapper .user-wrapper") ));

        // world_record_time
        coursePageScrape.setWorldRecordTime(scrapeHelper.getTypographyNumber( document.select(".clear-time") ));

        // first_clear_player_id
        // this is only relevant if the player is in our database -- handle it somewhere else (it"s not parsed data)

        // first_clear_player_nintendo_id
        coursePageScrape.setFirstClearPlayerNintendoId(scrapeHelper.getPlayerNintendoIdFromProfileLink( document.select(".first-user a#mii") ));

        // first_clear_player_info
//        coursePageScrape.setFirstClearPlayerInfo(scrapeHelper.getPlayerBasicInfo( document.select(".first-user .user-wrapper") ));

        // recent_players_nintendo_ids
        coursePageScrape.setRecentPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".played-body .user-info-wrapper a#mii") ));

        // recent_players_infos
//        coursePageScrape.setRecentPlayersInfos(scrapeHelper.getPlayersBasicInfos( document.select(".played-body .user-wrapper") ));

        // cleared_by_players_nintendo_ids
        coursePageScrape.setClearedByPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".cleared-body .user-info-wrapper a#mii") ));

        // cleared_by_players_infos
//        coursePageScrape.setClearedByPlayersInfos(scrapeHelper.getPlayersBasicInfos( document.select(".cleared-body .user-wrapper") ));

        // starred_by_players_nintendo_ids
        coursePageScrape.setStarredByPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".liked-body .user-info-wrapper a#mii") ));

        // starred_by_players_infos
//        coursePageScrape.setStarredByPlayersInfos(scrapeHelper.getPlayersBasicInfos( document.select(".liked-body .user-wrapper") ));
    }

    public String getCourseUrl() {
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set.");
        }

        return COURSE_URL_PREFIX + nintendoId;
    }

    private String getGameskin(Element element) throws MissingScrapeValueException {
        // -- mode (SMB1, SMB2, SMW, NSMB)
        Map<String, String> gameskinSelectors = new HashMap<>(new ImmutableMap.Builder<String, String>()
                .put("SMB1", ".course-meta-info .common_gs_sb")
                .put("SMB3", ".course-meta-info .common_gs_sb3")
                .put("SMW", ".course-meta-info .common_gs_sw")
                .put("NSMB", ".course-meta-info .common_gs_sbu")
                .build());

        return gameskinSelectors.entrySet().stream()
                .filter(map -> ! element.select(map.getValue()).isEmpty())
                .map(map -> map.getKey())
                .findFirst()
                .orElseThrow(() -> new MissingScrapeValueException("Gameskin could not be determined."));
    }

    public String getNintendoId() {
        return nintendoId;
    }

    public void setNintendoId(String nintendoId) {
        this.nintendoId = nintendoId;
    }

    public Course getScrapedCourse() {
        return scrapedCourse;
    }

    public void setScrapedCourse(Course scrapedCourse) {
        this.scrapedCourse = scrapedCourse;
    }
}
