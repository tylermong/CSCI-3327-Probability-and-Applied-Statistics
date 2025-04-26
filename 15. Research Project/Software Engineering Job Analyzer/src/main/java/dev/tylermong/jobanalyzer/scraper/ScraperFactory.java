package dev.tylermong.jobanalyzer.scraper;
import dev.tylermong.jobanalyzer.scraper.data.*;

import java.util.HashMap;
import java.util.Map;

public class ScraperFactory
{
    public static Map<String, JobScraper> createDataScrapers()
    {
        Map<String, JobScraper> scrapers = new HashMap<>();

        scrapers.put("Greenhouse", new GreenhouseScraper());
        scrapers.put("Lever", new LeverScraper());
        scrapers.put("Workday", new WorkdayScraper());
        
        return scrapers;
    }
}