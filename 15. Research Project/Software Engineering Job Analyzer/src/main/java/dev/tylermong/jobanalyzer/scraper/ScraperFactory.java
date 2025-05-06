package dev.tylermong.jobanalyzer.scraper;

import dev.tylermong.jobanalyzer.scraper.data.*;
import dev.tylermong.jobanalyzer.scraper.postings.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class for creating instances of link and data scrapers.
 */
public class ScraperFactory
{
    /**
     * Creates a list of link scrapers.
     * 
     * @return a list of LinkScraper instances
     */
    public static List<LinkScraper> createLinkScrapers()
    {
        List<LinkScraper> linkScrapers = new ArrayList<>();

        linkScrapers.add(new SimplifyInternshipScraper());
        linkScrapers.add(new VanshInternshipScraper());

        return linkScrapers;
    }

    /**
     * Creates a map of data scrapers.
     * 
     * @return a map with job board names as keys and DataScraper instances as values
     */
    public static Map<String, DataScraper> createDataScrapers()
    {
        Map<String, DataScraper> dataScrapers = new HashMap<>();

        dataScrapers.put("greenhouse", new GreenhouseScraper());
        dataScrapers.put("lever", new LeverScraper());
        dataScrapers.put("workday", new WorkdayScraper());

        return dataScrapers;
    }
}