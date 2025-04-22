package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.WorkdayScraper;
import dev.tylermong.jobanalyzer.model.JobPost;

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

        for (String link : links)
        {
            if (link.contains("workday"))
            {
                try
                {
                    JobPost jobPost = workdayScraper.scrapeJob(link);
                    System.out.println("\nCompany: " + jobPost.getCompany());
                    System.out.println("Title: " + jobPost.getTitle());
                    System.out.println("Location: " + jobPost.getLocation());
                    System.out.println("Skills: " + jobPost.getSkills());
                    System.out.println("URL: " + jobPost.getUrl());
                    
                    writer.write("Company: " + jobPost.getCompany() + "\n");
                    writer.write("Title: " + jobPost.getTitle() + "\n");
                    writer.write("Location: " + jobPost.getLocation() + "\n");
                    writer.write("Skills: " + jobPost.getSkills() + "\n");
                    writer.write("URL: " + jobPost.getUrl() + "\n\n");
                    writer.flush();
                }
                catch (IOException e)
                {
                    System.err.println("Error scraping job from link " + link + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        writer.close();
    }
}
