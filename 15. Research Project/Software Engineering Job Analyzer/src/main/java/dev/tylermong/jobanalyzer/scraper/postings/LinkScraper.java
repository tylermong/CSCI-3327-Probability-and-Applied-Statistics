package dev.tylermong.jobanalyzer.scraper.postings;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LinkScraper
{
    List<String> scrapeLinks() throws IOException;

    Map<String, Integer> countJobBoardFrequencies(List<String> jobPostingURLs);

    void writeFrequenciesToFile(Map<String, Integer> frequencies, String outputFilePath) throws IOException;
}