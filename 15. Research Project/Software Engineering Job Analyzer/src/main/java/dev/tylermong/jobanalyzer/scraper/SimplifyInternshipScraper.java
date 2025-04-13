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
    // title keywords to exclude
    private static final String[] EXCLUDED_KEYWORDS = {
            "Data", "Quality", "QA", "Test", "Testing", "Advise", "Advising", "Advisor", "Co-op", "Coop", "Research",
            "Researcher", "PHD", "Supply Chain", "Analytics", "GIS", "Solutions", "Hardware"
    };

    public List<String> scrapeLinks() throws IOException
    {
        String readmeContent = Jsoup.connect(RAW_README_URL)
                .ignoreContentType(true)
                .execute()
                .body();

        List<String> jobPostingURLs = new ArrayList<>();

        Pattern rowPattern = Pattern.compile(
                "\\|\\s*\\*\\*\\[[^\\]]+\\]\\([^)]*\\)\\*\\*\\s*\\|\\s*([^|]+)\\|[^|]*\\|.*?<a href=\"(https?://[^\"]+)\"");
        Matcher rowMatcher = rowPattern.matcher(readmeContent);

        int count = 0;
        while (rowMatcher.find() && count < 50)
        {
            String title = rowMatcher.group(1);
            String url = rowMatcher.group(2);

            System.out.println("Title: " + title);
            System.out.println("URL: " + url);

            // contains any of the excluded keywords
            if (title.matches(".*\\b(" + String.join("|", EXCLUDED_KEYWORDS) + ")\\b.*"))
            {
                System.out.println("[SKIPPED]\n");
                continue;
            }

            if (url.contains("simplify.jobs"))
            {
                continue;
            }

            jobPostingURLs.add(url);
            System.out.println("[ADDED]\n");
            count++;
        }

        return jobPostingURLs;
    }
}