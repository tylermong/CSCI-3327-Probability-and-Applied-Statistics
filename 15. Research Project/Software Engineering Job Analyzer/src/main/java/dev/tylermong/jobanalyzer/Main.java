package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.WorkdayScraper;
import dev.tylermong.jobanalyzer.analyzer.SkillAnalyzer;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        new JobScrapingService(
                new SimplifyInternshipScraper(),
                new WorkdayScraper(),
                "15. Research Project/Software Engineering Job Analyzer/WorkdayResults.txt").scrapeAndWrite();

        SkillAnalyzer.writeSkillFrequencyToFile();
    }
}