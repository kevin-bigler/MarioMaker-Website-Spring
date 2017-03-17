package com.kevinbigler.mariomakertracker.scraper.persistence;

/**
 * Created by Kevin on 3/16/2017.
 */
public interface ScrapePersistence<T> {
    public void persist(T pojo) throws Exception;
}
