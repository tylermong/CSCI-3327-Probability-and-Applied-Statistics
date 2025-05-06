package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.ScraperFactory;
import dev.tylermong.jobanalyzer.analyzer.SkillAnalyzer;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // Initialize the job scraping service with link scrapers and data scrapers, and call the scrapeAndWrite method
        // to start the scraping process. The output file is specified for saving the scraped data.
        new JobScrapingService(
                ScraperFactory.createLinkScrapers(),
                ScraperFactory.createDataScrapers(),
                "15. Research Project/Software Engineering Job Analyzer/output/DataResults.txt").scrapeAndWrite();

        SkillAnalyzer.writeSkillFrequencyToFile();
    }
}