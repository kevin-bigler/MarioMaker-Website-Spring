package com.kevinbigler.mariomakertracker.scraper;

import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;

/**
 * Created by Kevin on 3/11/2017.
 */
public interface Scraper {
    public void scrape() throws Exception;
}
