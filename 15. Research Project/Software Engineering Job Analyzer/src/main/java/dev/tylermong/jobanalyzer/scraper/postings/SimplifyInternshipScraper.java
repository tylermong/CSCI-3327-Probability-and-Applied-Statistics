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

/**
 * Scraper for job postings from the SimplifyJobs GitHub repository.
 */
public class SimplifyInternshipScraper implements LinkScraper
{
    private static final String RAW_README_URL = "https://raw.githubusercontent.com/SimplifyJobs/Summer2025-Internships/refs/heads/dev/README.md";
    // title keywords to exclude
    private static final String[] EXCLUDED_KEYWORDS =
    { "Data", "Quality", "QA", "Test", "Testing", "Advise", "Advising", "Advisor", "Co-op", "Coop", "Research",
            "Researcher", "PHD", "Supply Chain", "Analytics", "GIS", "Solutions", "Hardware" };

    /**
     * Scrapes the job postings from the README file on the SimplifyJobs GitHub repository.
     * 
     * @return             a list of job posting URLs
     * @throws IOException if an error occurs while connecting to the URL
     */
    @Override
    public List<String> scrapeLinks() throws IOException
    {
        String readmeContent = Jsoup.connect(RAW_README_URL).ignoreContentType(true).execute().body();

        List<String> jobPostingURLs = new ArrayList<>();

        // Regex pattern to match the job posting rows in the README file
        Pattern rowPattern = Pattern.compile(
                "\\|\\s*\\*\\*\\[[^\\]]+\\]\\([^)]*\\)\\*\\*\\s*\\|\\s*([^|]+)\\|[^|]*\\|.*?<a href=\"(https?://[^\"]+)\"");
        Matcher rowMatcher = rowPattern.matcher(readmeContent);

        System.out.println("Scraping job postings from SimplifyJobs...");
        while (rowMatcher.find())
        {
            String title = rowMatcher.group(1);
            String url = rowMatcher.group(2);

            // Exclude job postings with certain keywords in the title or URLs containing "simplify.jobs"
            if (title.matches(".*\\b(" + String.join("|", EXCLUDED_KEYWORDS) + ")\\b.*")
                    || url.contains("simplify.jobs"))
            {
                continue;
            }

            jobPostingURLs.add(url);
        }
        System.out.println("Scraping completed. Found " + jobPostingURLs.size() + " job postings from SimplifyJobs.\n");

        writeFrequenciesToFile(countJobBoardFrequencies(jobPostingURLs),
                "15. Research Project/Software Engineering Job Analyzer/output/JobBoardFrequency.txt");

        return jobPostingURLs;
    }

    /**
     * Counts the frequency of job boards in the list of job posting URLs.
     * 
     * @param  jobPostingURLs the list of job posting URLs
     * @return                a map containing the frequency of each job board
     */
    @Override
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
                String domain = parts.length >= 2 ? parts[parts.length - 2] + "." + parts[parts.length - 1] : host;
                frequencies.put(domain, frequencies.getOrDefault(domain, 0) + 1);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage() + " for URL: " + urlString);
            }
        }
        return frequencies;
    }

    /**
     * Writes the job board frequencies to a file.
     * 
     * @param  frequencies    the map containing the frequency of each job board
     * @param  outputFilePath the path to the output file
     * @throws IOException    if an error occurs while writing to the file
     */
    @Override
    public void writeFrequenciesToFile(Map<String, Integer> frequencies, String outputFilePath) throws IOException
    {
        Path path = Paths.get(outputFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            frequencies.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer> comparingByValue(Comparator.reverseOrder())).forEach(entry ->
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