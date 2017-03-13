package com.kevinbigler.mariomakertracker.scraper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.kevinbigler.mariomakertracker.common.MMUtils;
import com.kevinbigler.mariomakertracker.common.RestUtils;
import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;
import com.kevinbigler.mariomakertracker.exception.MissingScrapeValueException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        document.select("");
    }

    public String getCourseUrl() {
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set.");
        }

        return COURSE_URL_PREFIX + nintendoId;
    }

    private String getGameskin(Element element) {
        // -- mode (SMB1, SMB2, SMW, NSMB)

//        Maps.newHashMap();
        Map gameskinSelectors = new HashMap<>(new ImmutableMap.Builder<String, String>()
                .put("SMB1", ".course-meta-info .common_gs_sb")
                .put("SMB3", ".course-meta-info .common_gs_sb3")
                .put("SMW", ".course-meta-info .common_gs_sw")
                .put("NSMB", ".course-meta-info .common_gs_sbu")
                .build());

//        gameskinSelectors.forEach((gameskin,selector)->{
////            System.out.println("Item : " + k + " Count : " + v);
////            if("E".equals(k)){
////                System.out.println("Hello E");
////            }
//            if (! element.select(selector).isEmpty()) {
//                return gameskin;
//            }
//        });
        return gameskinSelectors.entrySet().stream()
                .filter(map -> ! element.select(map.getValue()).isEmpty())
                .map(map -> map.getKey())
                .findFirst()
                .orElseThrow(() -> new MissingScrapeValueException("Gameskin could not be determined."));
//        gameskinSelectors.stream().filter(player -> player.getName().contains(name))
//                .findFirst().orElse(null);
        /*
        $gameskinSelectors = [
        'SMB1' => '.course-meta-info .common_gs_sb',
                'SMB3' => '.course-meta-info .common_gs_sb3',
                'SMW' => '.course-meta-info .common_gs_sw',
                'NSMB' => '.course-meta-info .common_gs_sbu'
    ];

        foreach ($gameskinSelectors as $gameskin => $selector) {
            if ($this->domParseHelper->domHasElement($dom, $selector))
                return $gameskin;
        }
        */

        return null;
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
