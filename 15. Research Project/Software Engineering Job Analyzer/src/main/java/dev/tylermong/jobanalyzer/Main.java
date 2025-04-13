package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;
import dev.tylermong.jobanalyzer.model.JobPost;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SimplifyInternshipScraper scraper = new SimplifyInternshipScraper();
        try
        {
            List<String> jobPostingURLs = scraper.scrapeLinks();
            for (String url : jobPostingURLs)
            {
                System.out.println(url);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
