package dev.tylermong.jobanalyzer;

import dev.tylermong.jobanalyzer.scraper.SimplifyInternshipScraper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SimplifyInternshipScraper scraper = new SimplifyInternshipScraper();
        
        try
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
        }
    }
}
