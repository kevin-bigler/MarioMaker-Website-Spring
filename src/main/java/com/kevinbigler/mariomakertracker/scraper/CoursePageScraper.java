package com.kevinbigler.mariomakertracker.scraper;

import com.kevinbigler.mariomakertracker.common.MMUtils;
import com.kevinbigler.mariomakertracker.common.RestUtils;
import com.kevinbigler.mariomakertracker.entity.Course;
import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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

        
    }

    public String getCourseUrl() {
        if (nintendoId == null || nintendoId.isEmpty()) {
            throw new IllegalStateException("Nintendo ID not set.");
        }

        return COURSE_URL_PREFIX + nintendoId;
    }

    // TODO use something like this for getting images
    private void scrapeFile(String fileUrl) {
        String fileNameOriginal = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        String extension = fileUrl.substring(fileUrl.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String fileNameGenerated = uuid + extension;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                fileUrl,
                HttpMethod.GET, entity, byte[].class);

        System.out.println("HTTP Status: " + response.getStatusCode().toString());

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                Files.write(Paths.get(fileNameGenerated), response.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // TODO log the error. handle error (halt execution?)
        }
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
