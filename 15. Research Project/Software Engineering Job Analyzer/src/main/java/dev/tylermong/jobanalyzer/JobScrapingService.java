package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.data.GreenhouseScraper;
import dev.tylermong.jobanalyzer.scraper.data.WorkdayScraper;
import dev.tylermong.jobanalyzer.scraper.postings.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.postings.VanshInternshipScraper;
import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.util.ProgressBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JobScrapingService
{
    private final SimplifyInternshipScraper simplifyLinkScraper;
    private final VanshInternshipScraper vanshLinkScraper;
    private final WorkdayScraper workdayDataScraper;
    private final GreenhouseScraper greenhouseDataScraper;
    private final String outputFile;

    public JobScrapingService(
            SimplifyInternshipScraper simplifyLinkScraper,
            VanshInternshipScraper vanshLinkScraper,
            WorkdayScraper workdayDataScraper,
            GreenhouseScraper greenhouseDataScraper,
            String outputFile)
    {
        this.simplifyLinkScraper = simplifyLinkScraper;
        this.vanshLinkScraper = vanshLinkScraper;
        this.workdayDataScraper = workdayDataScraper;
        this.greenhouseDataScraper = greenhouseDataScraper;
        this.outputFile = outputFile;
    }

    public void scrapeAndWrite() throws IOException
    {
        File outputDirectory = new File("15. Research Project/Software Engineering Job Analyzer/output/");
        if (!outputDirectory.exists())
        {
            outputDirectory.mkdirs();
            System.out.println("Created output directory: " + outputDirectory.getPath());
        }

        Stream<String> allLinks = Stream.concat(
            simplifyLinkScraper.scrapeLinks().stream(),
            vanshLinkScraper.scrapeLinks().stream()
        );

        List<String> links = allLinks
            .map(link -> {
                int queryIndex = link.indexOf('?');
                return (queryIndex == -1) ? link : link.substring(0, queryIndex);
            })
            .distinct()
            .sorted(String::compareToIgnoreCase)
            .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/AllLinks.txt")))
        {
            for (String link : links)
            {
                writer.write(link);
                writer.newLine();
            }
        }
        System.out.println(links.size() + " unique links saved to: AllLinks.txt");

        List<String> useableLinks = links.stream()
            .filter(link -> link.contains("workday") || link.contains("greenhouse"))
            .collect(Collectors.toList());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/UseableLinks.txt")))
        {
            for (String link : useableLinks)
            {
                writer.write(link);
                writer.newLine();
            }
        }
        System.out.println(useableLinks.size() + " useable links saved to: UseableLinks.txt");
                
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/ErrorLog.txt")))
        {
            ProgressBar bar = new ProgressBar(50);
            int total = useableLinks.size();
            int processed = 0;
            System.out.println("\nScraping data from " + total + " links");
            for (String link : useableLinks)
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
                        errorWriter.write(link + ": " + e.getMessage());
                        errorWriter.newLine();
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
                        errorWriter.write(link + ": " + e.getMessage());
                        errorWriter.newLine();
                    }
                }
            }
            System.out.println("\nScraping completed. Data saved to: " + outputFile);
        }
    }

    private void writePost(BufferedWriter w, JobPost p) throws IOException
    {
        // If the post is empty (job filled/removed), skip it
        if (p.getCompany().trim().isEmpty() && p.getTitle().trim().isEmpty() && p.getLocation().trim().isEmpty() && p.getSkills().isEmpty())
        {
            return;
        }

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