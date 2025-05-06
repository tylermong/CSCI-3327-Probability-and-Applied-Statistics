package dev.tylermong.jobanalyzer.scraper.postings;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for link scraping functionality.
 */
public interface LinkScraper
{
    /**
     * Scrapes the job postings from the source.
     * 
     * @return             a list of job posting URLs
     * @throws IOException if an error occurs while connecting to the URL
     */
    List<String> scrapeLinks() throws IOException;

    /**
     * Counts the frequency of job boards in the list of job posting URLs.
     * 
     * @param  jobPostingURLs the list of job posting URLs
     * @return                a map with job board names as keys and their frequencies as values
     */
    Map<String, Integer> countJobBoardFrequencies(List<String> jobPostingURLs);

    /**
     * Writes the job board frequencies to a file.
     * 
     * @param  frequencies    the map containing the frequency of each job board
     * @param  outputFilePath the path to the output file
     * @throws IOException    if an error occurs while writing to the file
     */
    void writeFrequenciesToFile(Map<String, Integer> frequencies, String outputFilePath) throws IOException;
}