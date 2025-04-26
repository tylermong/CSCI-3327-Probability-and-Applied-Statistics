package dev.tylermong.jobanalyzer.scraper;
import dev.tylermong.jobanalyzer.scraper.data.*;

import java.util.HashMap;
import java.util.Map;

public class ScraperFactory
{
    public static Map<String, DataScraper> createDataScrapers()
    {
        Map<String, DataScraper> dataScrapers = new HashMap<>();

        dataScrapers.put("Greenhouse", new GreenhouseScraper());
        dataScrapers.put("Lever", new LeverScraper());
        dataScrapers.put("Workday", new WorkdayScraper());

        return dataScrapers;
    }
}