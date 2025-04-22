package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.WorkdayScraper;
import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.analyzer.SkillAnalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        SimplifyInternshipScraper simplifyScraper = new SimplifyInternshipScraper();
        List<String> links = new ArrayList<>();
        try
        {
            links = simplifyScraper.scrapeLinks();
        }
        catch (IOException e)
        {
            System.err.println("Error scraping links: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        WorkdayScraper workdayScraper = new WorkdayScraper();
        BufferedWriter writer = new BufferedWriter(new FileWriter("15. Research Project/Software Engineering Job Analyzer/WorkdayResults.txt"));

        System.out.println("Scraping job posts from links. " + links.size() + " links found.");
        int totalLinks = links.size();
        int processedLinks = 0;
        int barWidth = 50;

        for (String link : links)
        {
            processedLinks++;
            double progress = (double) processedLinks / totalLinks;
            int filledWidth = (int) (barWidth * progress);
            int emptyWidth = barWidth - filledWidth;
            StringBuilder progressBar = new StringBuilder("");
            for (int i = 0; i < filledWidth; i++)
            {
                progressBar.append("█");
            }
            for (int i = 0; i < emptyWidth; i++)
            {
                progressBar.append("▒");
            }
            progressBar.append(String.format(" %.1f%%", progress * 100));
            progressBar.append(" (").append(processedLinks).append("/").append(totalLinks).append(")");

            System.out.print("\r" + progressBar.toString());

            if (link.contains("workday"))
            {
                try
                {
                    JobPost jobPost = workdayScraper.scrapeJob(link);
                    
                    writer.write("Company: " + jobPost.getCompany() + "\n");
                    writer.write("Title: " + jobPost.getTitle() + "\n");
                    writer.write("Location: " + jobPost.getLocation() + "\n");
                    writer.write("Skills: " + jobPost.getSkills() + "\n");
                    writer.write("URL: " + jobPost.getUrl() + "\n\n");
                    writer.flush();
                }
                catch (IOException e)
                {
                    System.err.println("\nError scraping job from link " + link + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        System.out.println("\nJob posts scraped successfully. Results written to WorkdayResults.txt.");
        writer.close();

        // Write skill frequency to file
        System.out.println("\nWriting skill frequency to file...");
        SkillAnalyzer.writeSkillFrequencyToFile();
        System.out.println("Skill frequency written to SkillFrequency.txt.");
    }
}
