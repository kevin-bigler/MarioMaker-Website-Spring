package com.kevinbigler.mariomakertracker.scraper;

import com.kevinbigler.mariomakertracker.common.DateUtils;
import com.kevinbigler.mariomakertracker.common.MMUtils;
import com.kevinbigler.mariomakertracker.common.RestUtils;
import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;
import com.kevinbigler.mariomakertracker.pojo.CoursePageScrapePojo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.*;

import java.util.Date;

import static java.lang.String.format;

/**
 * Created by Kevin on 3/11/2017.
 */
public class CoursePageScraper implements Scraper<CoursePageScrapePojo> {
    private String nintendoId;
    private Course scrapedCourse;

    private static String COURSE_URL_PREFIX = "https://supermariomakerbookmark.nintendo.net/courses/";

    public CoursePageScraper(String nintendoId) {
        this.nintendoId = nintendoId;
    }

    @Override
    public CoursePageScrapePojo scrape() throws Exception {
        // check that the nintendoId is not null
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set");
        }

        // check that the nintendoId is properly formatted
        if ( ! MMUtils.isCourseNintendoIdValid(nintendoId) ) {
            throw new IllegalArgumentException("Nintendo ID is in an invalid format. Nintendo ID: " + nintendoId);
        }

        RestUtils restUtils = new RestUtils();
        ResponseEntity<String> responseEntity = restUtils.getAsTextHtml(getCourseUrl());

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
        coursePageScrape.setCreator(scrapeHelper.getPlayerPreview( document.select(".course-detail-wrapper") ));

        // title
        coursePageScrape.setName(document.select(".course-title").text());  // TODO change this to firstText() or w/e (jsoup will concat all found elements' text otherwise)

        // image_url
        coursePageScrape.setMainImageUrl(document.select("img.course-image").attr("abs:src"));

        // image_full_url
        coursePageScrape.setFullImageUrl(document.select("img.course-image-full").attr("abs:src"));

        // upload_date
        String uploadDateSource = document.select(".created_at").text();
        Date uploadDate = MMUtils.parseDateString(uploadDateSource);
        coursePageScrape.setUploadDate(uploadDate);

        // gameskin
        // -- mode (SMB1, SMB2, SMW, NSMB)
        coursePageScrape.setGameskin(scrapeHelper.getGameskin(document));

        // image_full_url
        coursePageScrape.setMiiverseCommentsUrl(document.select(".course-detail-wrapper a.miiverse").attr("abs:href"));

        // -------------------------------------------------------------------------------------------------------------
        // data that changes over time
        // -------------------------------------------------------------------------------------------------------------

        // difficulty_rank
        coursePageScrape.setDifficultyRank(document.select(".course-header").text());

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
        System.out.println("clearRatio: " + clearRatio);
        coursePageScrape.setNumberClears(Integer.valueOf(StringUtils.substringBefore(clearRatio, "/")));

        // number_attempts
        coursePageScrape.setNumberAttempts(Integer.valueOf(StringUtils.substringAfter(clearRatio, "/")));

        // number_comments
        coursePageScrape.setNumberComments(scrapeHelper.getTypographyNumberAsInteger( document.select(".comment-count") ));

        // tag
        coursePageScrape.setTag(document.select(".course-tag").text());  // TODO change this to firstText() or w/e (jsoup will concat all found elements' text otherwise)

        // world_record_player_nintendo_id
        coursePageScrape.setWorldRecordHolderNintendoId(scrapeHelper.getPlayerNintendoIdFromProfileLink( document.select(".fastest-time-wrapper a#mii") ));

        // world_record_player_info
        coursePageScrape.setWorldRecordHolder(scrapeHelper.getPlayerPreview( document.select(".fastest-time-wrapper .user-wrapper") ));

        // world_record_time
        coursePageScrape.setWorldRecordTime(scrapeHelper.getTypographyNumber( document.select(".clear-time") ));

        // first_clear_player_nintendo_id
        coursePageScrape.setFirstClearPlayerNintendoId(scrapeHelper.getPlayerNintendoIdFromProfileLink( document.select(".first-user a#mii") ));

        // first_clear_player_info
        coursePageScrape.setFirstClearPlayer(scrapeHelper.getPlayerPreview( document.select(".first-user .user-wrapper") ));

        // recent_players_nintendo_ids
        coursePageScrape.setRecentPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".played-body .user-info-wrapper a#mii") ));

        // recent_players_infos
        coursePageScrape.setRecentPlayers(scrapeHelper.getPlayersPreview( document.select(".played-body .user-wrapper") ));

        // cleared_by_players_nintendo_ids
        coursePageScrape.setClearedByPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".cleared-body .user-info-wrapper a#mii") ));

        // cleared_by_players_infos
        coursePageScrape.setClearedByPlayers(scrapeHelper.getPlayersPreview( document.select(".cleared-body .user-wrapper") ));

        // starred_by_players_nintendo_ids
        coursePageScrape.setStarredByPlayersNintendoIds(scrapeHelper.getAllPlayerNintendoIdsFromProfileLinks( document.select(".liked-body .user-info-wrapper a#mii") ));

        // starred_by_players_infos
        coursePageScrape.setStarredByPlayers(scrapeHelper.getPlayersPreview( document.select(".liked-body .user-wrapper") ));

        return coursePageScrape;
    }

    public String getCourseUrl() {
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set.");
        }

        return COURSE_URL_PREFIX + nintendoId;
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
