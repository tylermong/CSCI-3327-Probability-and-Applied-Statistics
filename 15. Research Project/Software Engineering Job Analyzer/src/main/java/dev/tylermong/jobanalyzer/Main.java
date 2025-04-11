package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.IndeedScraper;
import dev.tylermong.jobanalyzer.model.JobPost;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        IndeedScraper indeedScraper = new IndeedScraper();

        try
        {
            List<JobPost> jobs = indeedScraper.scrape();

            for (JobPost job : jobs)
            {
                System.out.println(job);
            }
        }
        catch (Exception e)
        {
            System.err.println("Error scraping job posts: " + e.getMessage());
        }
    }
}
