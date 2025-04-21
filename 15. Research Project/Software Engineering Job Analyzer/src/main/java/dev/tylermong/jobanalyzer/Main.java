package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.scraper.WorkdayScraper;
import dev.tylermong.jobanalyzer.model.JobPost;

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

        int count = 0;
        for (String link : links)
        {
            if (link.contains("workday") && count < 1)
            {
                try
                {
                    System.out.println("Scraping data from link: " + link);

                    JobPost jobPost = workdayScraper.scrapeJob(link);
                    System.out.println("Company: " + jobPost.getCompany());
                    System.out.println("Title: " + jobPost.getTitle());
                    System.out.println("Location: " + jobPost.getLocation());
                    System.out.println("Skills: " + jobPost.getSkills());
                    System.out.println("URL" + jobPost.getUrl());
                    count++;
                }
                catch (IOException e)
                {
                    System.err.println("Error scraping job from link " + link + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        /*try
        {
            List<String> jobPostingURLs = scraper.scrapeLinks();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt")))
            {
                for (String url : jobPostingURLs)
                {
                    writer.write(url);
                    writer.newLine();
                }
                System.out.println("Successfully saved " + jobPostingURLs.size() + " URLs to results.txt");
            }
            catch (IOException e)
            {
                System.err.println("Error writing to results.txt: " + e.getMessage());
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }
}
