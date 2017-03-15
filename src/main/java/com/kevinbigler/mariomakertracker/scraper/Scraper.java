package com.kevinbigler.mariomakertracker.scraper;

import com.kevinbigler.mariomakertracker.exception.CourseNotFoundException;
import com.kevinbigler.mariomakertracker.pojo.ScrapePojo;

/**
 * Created by Kevin on 3/11/2017.
 */
public interface Scraper<T extends ScrapePojo> {
    public T scrape() throws Exception;
}
