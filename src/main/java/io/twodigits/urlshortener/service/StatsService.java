package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.Stats;
import io.twodigits.urlshortener.model.dto.StatsDto;

public interface StatsService{
    /**
     * save statistic information for an URL.
     *
     * @param stat statistical info to store
     */
    void saveStatsForUrl(Stats stat);

    /**
     * returns List of Stats.
     *
     * @return List of Stats.
     */
    StatsDto retrieveStats();
}
