package dev.tylermong.jobanalyzer.scraper.data;

import dev.tylermong.jobanalyzer.model.JobPost;
import java.io.IOException;

public interface JobScraper
{
    JobPost scrapeJob(String link) throws IOException;
}