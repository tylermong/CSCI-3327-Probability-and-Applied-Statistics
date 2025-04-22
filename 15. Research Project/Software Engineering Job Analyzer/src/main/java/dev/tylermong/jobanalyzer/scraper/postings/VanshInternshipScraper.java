package dev.tylermong.jobanalyzer.scraper.postings;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.util.Comparator;

public class VanshInternshipScraper
{
    private static final String RAW_README_URL = "https://raw.githubusercontent.com/vanshb03/Summer2025-Internships/refs/heads/dev/README.md";
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


        // RESUME HERE:
        Pattern rowPattern = Pattern.compile(
                "\\|\\s*\\*\\*\\[[^\\]]+\\]\\([^)]*\\)\\*\\*\\s*\\|\\s*([^|]+)\\|[^|]*\\|.*?<a href=\"(https?://[^\"]+)\"");
        Matcher rowMatcher = rowPattern.matcher(readmeContent);

        while (rowMatcher.find())
        {
            String title = rowMatcher.group(1);
            String url = rowMatcher.group(2);

            if (title.matches(".*\\b(" + String.join("|", EXCLUDED_KEYWORDS) + ")\\b.*")
                    || url.contains("simplify.jobs"))
            {
                continue;
            }

            jobPostingURLs.add(url);
        }

        writeFrequenciesToFile(countJobBoardFrequencies(jobPostingURLs),
                "15. Research Project/Software Engineering Job Analyzer/JobBoardFrequency.txt");

        return jobPostingURLs;
    }

    public Map<String, Integer> countJobBoardFrequencies(List<String> jobPostingURLs)
    {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String urlString : jobPostingURLs)
        {
            try
            {
                URI uri = URI.create(urlString);
                String host = uri.getHost();
                String[] parts = host.split("\\.");
                String domain = parts.length >= 2
                        ? parts[parts.length - 2] + "." + parts[parts.length - 1]
                        : host;
                frequencies.put(domain, frequencies.getOrDefault(domain, 0) + 1);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage() + " for URL: " + urlString);
            }
        }
        return frequencies;
    }

    public void writeFrequenciesToFile(Map<String, Integer> frequencies, String outputFilePath) throws IOException
    {
        Path path = Paths.get(outputFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            frequencies.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                    .forEach(entry ->
                    {
                        try
                        {
                            writer.write(entry.getKey() + ": " + entry.getValue());
                            writer.newLine();
                        }
                        catch (IOException e)
                        {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                    });
        }
        catch (IOException e)
        {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}