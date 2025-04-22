package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.WorkdayScraper;
import dev.tylermong.jobanalyzer.scraper.GreenhouseScraper;
import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.util.ProgressBar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JobScrapingService
{
    private final SimplifyInternshipScraper linkScraper;
    private final WorkdayScraper workdayDataScraper;
    private final GreenhouseScraper greenhouseDataScraper;
    private final String outputFile;

    public JobScrapingService(
            SimplifyInternshipScraper linkScraper,
            WorkdayScraper workdayDataScraper,
            GreenhouseScraper greenhouseDataScraper,
            String outputFile)
    {
        this.linkScraper = linkScraper;
        this.workdayDataScraper = workdayDataScraper;
        this.greenhouseDataScraper = greenhouseDataScraper;
        this.outputFile = outputFile;
    }

    public void scrapeAndWrite() throws IOException
    {
        List<String> links = linkScraper.scrapeLinks();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)))
        {
            ProgressBar bar = new ProgressBar(50);
            int total = links.size();
            int processed = 0;
            System.out.println("Scraping job posts from " + total + " links");
            for (String link : links)
            {
                processed++;
                bar.update(processed, total);
                if (link.contains("workday"))
                {
                    try
                    {
                        JobPost post = workdayDataScraper.scrapeJob(link);
                        writePost(writer, post);
                    }
                    catch (IOException e)
                    {
                        System.err.println("\nError scraping " + link + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                else if (link.contains("greenhouse"))
                {
                    try
                    {
                        JobPost post = greenhouseDataScraper.scrapeJob(link);
                        writePost(writer, post);
                    }
                    catch (IOException e)
                    {
                        System.err.println("\nError scraping " + link + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\nDone. Results in " + outputFile);
        }
    }

    private void writePost(BufferedWriter w, JobPost p) throws IOException
    {
        w.write("Company: " + p.getCompany());
        w.newLine();
        w.write("Title: " + p.getTitle());
        w.newLine();
        w.write("Location: " + p.getLocation());
        w.newLine();
        w.write("Skills: " + p.getSkills());
        w.newLine();
        w.write("URL: " + p.getUrl());
        w.newLine();
        w.newLine();
        w.flush();
    }
}