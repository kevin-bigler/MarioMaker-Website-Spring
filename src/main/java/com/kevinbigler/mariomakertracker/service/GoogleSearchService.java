package com.kevinbigler.mariomakertracker.service;

import com.kevinbigler.mariomakertracker.common.RestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 3/19/2017.
 */
@Service
public class GoogleSearchService {
    @Value("${mariomakertracker.api.google-search.key}")
    private String googleSearchApiKey;

    @Value("${mariomakertracker.api.google-search.search-courses.id}")
    private String googleSearchCoursesId;

    @Value("${mariomakertracker.api.google-search.search-profiles.id}")
    private String googleSearchPlayersId;

    private static final String GOOGLE_SEARCH_API_URL = "";

    public String searchGoogle(String searchTerm) {
        RestUtils restUtils = new RestUtils();
        String searchUrl = GOOGLE_SEARCH_API_URL + searchTerm;    // TODO make this
        restUtils.getAsTextHtml(searchUrl);

        return null;
    }
}
