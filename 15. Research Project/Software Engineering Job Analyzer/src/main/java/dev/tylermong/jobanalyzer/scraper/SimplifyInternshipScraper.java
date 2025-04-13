package dev.tylermong.jobanalyzer.scraper;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplifyInternshipScraper
{
    private static final String RAW_README_URL = "https://raw.githubusercontent.com/SimplifyJobs/Summer2025-Internships/refs/heads/dev/README.md";

    public List<String> scrapeLinks() throws IOException
    {
        String readmeContent = Jsoup.connect(RAW_README_URL)
                .ignoreContentType(true)
                .execute()
                .body();

        List<String> jobPostingURLs = new ArrayList<>();

        Pattern urlPattern = Pattern.compile("<a href=\"(https?://[^\"]+)\">");
        Matcher urlMatcher = urlPattern.matcher(readmeContent);

        for (int i = 0; i < 15 && urlMatcher.find(); i++)
        {
            String jobPostingURL = urlMatcher.group(1);

            if (jobPostingURL.contains("simplify.jobs"))
            {
                continue;
            }

            jobPostingURLs.add(jobPostingURL);
        }

        jobPostingURLs.remove(0);

        return jobPostingURLs;
    }
}