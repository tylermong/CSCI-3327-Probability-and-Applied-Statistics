package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.data.GreenhouseScraper;
import dev.tylermong.jobanalyzer.scraper.data.LeverScraper;
import dev.tylermong.jobanalyzer.scraper.data.WorkdayScraper;
import dev.tylermong.jobanalyzer.scraper.postings.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.postings.VanshInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.data.JobScraper;
import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.util.ProgressBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JobScrapingService
{
    private final SimplifyInternshipScraper simplifyLinkScraper;
    private final VanshInternshipScraper vanshLinkScraper;
    private final Map<String, JobScraper> scrapers;
    private final String outputFile;

    public JobScrapingService(
            SimplifyInternshipScraper simplifyLinkScraper,
            VanshInternshipScraper vanshLinkScraper,
            WorkdayScraper workdayDataScraper,
            GreenhouseScraper greenhouseDataScraper,
            LeverScraper leverDataScraper,
            String outputFile)
    {
        this.simplifyLinkScraper = simplifyLinkScraper;
        this.vanshLinkScraper = vanshLinkScraper;
        this.scrapers = Map.of(
            "workday", workdayDataScraper,
            "greenhouse", greenhouseDataScraper,
            "lever", leverDataScraper
        );
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
            .filter(link -> scrapers.keySet().stream().anyMatch(link::contains))
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
                
                scrapers.entrySet().stream()
                    .filter(entry -> link.contains(entry.getKey()))
                    .findFirst()
                    .ifPresent(entry -> {
                        try
                        {
                            JobPost post = entry.getValue().scrapeJob(link);
                            writePost(writer, post);
                        }
                        catch (IOException exception1)
                        {
                            System.err.println("\nError scraping " + link + ": " + exception1.getMessage());
                            exception1.printStackTrace();
                            try
                            {
                                errorWriter.write(link + ": " + exception1.getMessage());
                                errorWriter.newLine();
                            }
                            catch (IOException exception2)
                            {
                                System.err.println("Error writing to error log: " + exception2.getMessage());
                            }
                        }
                    });
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