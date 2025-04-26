package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.ScraperFactory;
import dev.tylermong.jobanalyzer.scraper.postings.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.postings.VanshInternshipScraper;
import dev.tylermong.jobanalyzer.analyzer.SkillAnalyzer;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        new JobScrapingService(
                new SimplifyInternshipScraper(),
                new VanshInternshipScraper(),
                ScraperFactory.createDataScrapers(),
                "15. Research Project/Software Engineering Job Analyzer/output/DataResults.txt").scrapeAndWrite();

        SkillAnalyzer.writeSkillFrequencyToFile();
    }
}