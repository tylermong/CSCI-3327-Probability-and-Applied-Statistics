package dev.tylermong.jobanalyzer.scraper.data;

import dev.tylermong.jobanalyzer.model.JobPost;
import java.io.IOException;

/**
 * Interface for data scraping functionality.
 */
public interface DataScraper
{
    /**
     * Scrapes the job post from the given URL.
     * 
     * @param  link        the URL of the job post
     * @return             a JobPost object containing the scraped data
     * @throws IOException if an error occurs while connecting to the URL
     */
    JobPost scrapeJob(String link) throws IOException;
}