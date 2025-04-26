package dev.tylermong.jobanalyzer.scraper.data;

import dev.tylermong.jobanalyzer.model.JobPost;
import java.io.IOException;

public interface DataScraper
{
    JobPost scrapeJob(String link) throws IOException;
}