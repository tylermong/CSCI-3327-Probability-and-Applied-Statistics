package dev.tylermong.jobanalyzer.scraper;
import dev.tylermong.jobanalyzer.scraper.data.*;
import dev.tylermong.jobanalyzer.scraper.postings.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScraperFactory
{
    public static List<LinkScraper> createLinkScrapers()
    {
        List<LinkScraper> linkScrapers = new ArrayList<>();

        linkScrapers.add(new SimplifyInternshipScraper());
        linkScrapers.add(new VanshInternshipScraper());

        return linkScrapers;
    }

    public static Map<String, DataScraper> createDataScrapers()
    {
        Map<String, DataScraper> dataScrapers = new HashMap<>();

        dataScrapers.put("greenhouse", new GreenhouseScraper());
        dataScrapers.put("lever", new LeverScraper());
        dataScrapers.put("workday", new WorkdayScraper());

        return dataScrapers;
    }
}