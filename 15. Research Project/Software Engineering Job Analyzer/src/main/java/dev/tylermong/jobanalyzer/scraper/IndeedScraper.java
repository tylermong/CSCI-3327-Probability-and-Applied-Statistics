package dev.tylermong.jobanalyzer.scraper;

import dev.tylermong.jobanalyzer.model.JobPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndeedScraper
{
    public List<JobPost> scrape() throws Exception
    {
        String url = "https://www.indeed.com/jobs?q=software+engineer&l=";
        
        // More realistic browser fingerprint
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("DNT", "1")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Sec-Fetch-Dest", "document")
                .header("Sec-Fetch-Mode", "navigate")
                .header("Sec-Fetch-Site", "none")
                .header("Sec-Fetch-User", "?1")
                .header("Cache-Control", "max-age=0")
                .referrer("https://www.google.com/")
                .timeout(15000)
                .followRedirects(true)
                .get();
                
        // Wait a random amount of time to appear more human-like
        Thread.sleep(new Random().nextInt(3000) + 2000);

        Elements jobs = doc.select("a.tapItem");

        List<JobPost> jobPosts = new ArrayList<>();

        for (Element job : jobs)
        {
            String title = job.select("h2.jobTitle").text();
            String description = job.select("div.job-snippet").text();
            String jobLink = "https://www.indeed.com" + job.attr("href");

            List<String> skills = extractSkills(description);
            boolean degreeRequired = description.toLowerCase().contains("degree");

            String experienceYears = "0";
            if (description.toLowerCase().contains("years"))
            {
                experienceYears = description.substring(description.indexOf("years") - 2, description.indexOf("years")).trim();
            }
            else
            {
                experienceYears = "0";
            }

            jobPosts.add(new JobPost(title, description, skills, degreeRequired, experienceYears, "Indeed"));
            
            // Add a small random pause between processing jobs
            Thread.sleep(new Random().nextInt(500) + 200);
        }

        return jobPosts;
    }

    private List<String> extractSkills(String text)
    {
        List<String> skills = new ArrayList<>();
        String[] skillKeywords = {"Java", "Python", "JavaScript", "C++", "C#", "SQL", "HTML", "CSS", "React", "Node.js"};

        for (String keyword : skillKeywords)
        {
            if (text.toLowerCase().contains(keyword.toLowerCase()))
            {
                skills.add(keyword);
            }
        }

        return skills;
    }
}