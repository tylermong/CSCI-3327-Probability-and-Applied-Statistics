package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.postings.LinkScraper;
import dev.tylermong.jobanalyzer.scraper.data.DataScraper;
import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.util.ProgressBar;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for scraping links and data. It collects links from different GitHub repositories, filters them, and
 * then scrapes data from them. This class also handles the creation of output files for scraped data and error logging.
 */
public class JobScrapingService
{
    private final List<LinkScraper> linkScrapers;
    private final Map<String, DataScraper> dataScrapers;
    private final String outputFile;
    private final String deadLinksFile = "15. Research Project/Software Engineering Job Analyzer/output/DeadLinks.txt";

    /**
     * Constructor for JobScrapingService.
     * 
     * @param linkScrapers list of link scrapers to collect links from
     * @param dataScrapers map of data scrapers to scrape data from the links
     * @param outputFile   path to the output file where scraped data will be saved
     */
    public JobScrapingService(List<LinkScraper> linkScrapers, Map<String, DataScraper> dataScrapers, String outputFile)
    {
        this.linkScrapers = linkScrapers;
        this.dataScrapers = dataScrapers;
        this.outputFile = outputFile;
    }

    /**
     * Scrapes links from the provided link scrapers, filters them, and then scrapes data from the useable links.
     * 
     * @throws IOException if an I/O error occurs during file operations
     */
    public void scrapeAndWrite() throws IOException
    {
        File outputDirectory = new File("15. Research Project/Software Engineering Job Analyzer/output/");
        if (!outputDirectory.exists())
        {
            outputDirectory.mkdirs();
            System.out.println("Created output directory: " + outputDirectory.getPath());
        }

        // Collect links from all scrapers
        List<String> allLinks = new ArrayList<>();
        for (LinkScraper scraper : linkScrapers)
        {
            allLinks.addAll(scraper.scrapeLinks());
        }

        // Remove duplicates and sort links
        List<String> links = allLinks.stream().map(link ->
        {
            int queryIndex = link.indexOf('?');
            return (queryIndex == -1) ? link : link.substring(0, queryIndex);
        }).distinct().sorted(String::compareToIgnoreCase).collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/AllLinks.txt")))
        {
            for (String link : links)
            {
                writer.write(link);
                writer.newLine();
            }
        }
        System.out.println(links.size() + " unique links saved to: AllLinks.txt");

        Set<String> deadLinks = loadDeadLinks();
        System.out.println("\nLoaded " + deadLinks.size() + " known dead links.");

        // Filter out dead links and links that don't match any data scraper
        List<String> useableLinks = links.stream().filter(link -> !deadLinks.contains(link)).filter(
                link -> dataScrapers.keySet().stream().anyMatch(key -> link.toLowerCase().contains(key.toLowerCase())))
                .collect(Collectors.toList());
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/UseableLinks.txt")))
        {
            for (String link : useableLinks)
            {
                writer.write(link);
                writer.newLine();
            }
        }
        System.out.println("\n" + useableLinks.size() + " useable links saved to: UseableLinks.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                BufferedWriter errorWriter = new BufferedWriter(
                        new FileWriter("15. Research Project/Software Engineering Job Analyzer/output/ErrorLog.txt")))
        {
            ProgressBar bar = new ProgressBar(50);
            int total = useableLinks.size();
            int processed = 0;
            AtomicInteger deadLinkCount = new AtomicInteger(0); // Must use in lambda expressions
            System.out.println("Scraping data from " + total + " links...\n");
            for (String link : useableLinks)
            {
                processed++;
                bar.update(processed, total);

                // Try to scrape data from the link. If it fails, add the link to dead links and log the error.
                dataScrapers.entrySet().stream().filter(entry -> link.contains(entry.getKey())).findFirst()
                        .ifPresent(entry ->
                        {
                            try
                            {
                                JobPost post = entry.getValue().scrapeJob(link);
                                writePost(writer, post, link);
                            }
                            catch (IOException exception1)
                            {
                                addDeadLink(link);
                                deadLinkCount.incrementAndGet();
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
            System.out.println("\n\n" + deadLinkCount + " new dead links found and added to DeadLinks.txt");
            System.out.println("Error log saved to: ErrorLog.txt");
            System.out.println("\nScraping completed. Data saved to: " + outputFile);
        }
    }

    /**
     * Writes a job post to the output file. If the post is empty (job filled/removed), it adds the link to dead links
     * and skips writing it.
     * 
     * @param  w           BufferedWriter to write the job post to
     * @param  p           JobPost object containing the job post data
     * @param  link        URL of the job post
     * @throws IOException if an I/O error occurs during writing
     */
    private void writePost(BufferedWriter w, JobPost p, String link) throws IOException
    {
        // If the post is empty (job filled/removed), add to dead links and skip it
        if (p.getCompany().trim().isEmpty() && p.getTitle().trim().isEmpty() && p.getLocation().trim().isEmpty()
                && p.getSkills().isEmpty())
        {
            addDeadLink(link);
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

    /**
     * Adds a dead link to the dead links file. If the file does not exist, it creates it.
     * 
     * @param link the URL of the dead link to be added
     */
    private void addDeadLink(String link)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(deadLinksFile, true)))
        {
            writer.write(link);
            writer.newLine();
        }
        catch (IOException exception)
        {
            System.err.println("Error adding dead link: " + exception.getMessage());
        }
    }

    /**
     * Loads dead links from the dead links file. If the file does not exist, it returns an empty set.
     * 
     * @return             a set of dead links
     * @throws IOException if an I/O error occurs during reading
     */
    private Set<String> loadDeadLinks() throws IOException
    {
        File file = new File(deadLinksFile);
        if (!file.exists())
        {
            return new HashSet<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            return reader.lines().collect(Collectors.toSet());
        }
    }
}