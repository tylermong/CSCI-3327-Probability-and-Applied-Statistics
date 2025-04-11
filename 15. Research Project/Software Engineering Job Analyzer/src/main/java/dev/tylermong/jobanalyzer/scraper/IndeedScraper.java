package dev.tylermong.jobanalyzer.scraper;

import dev.tylermong.jobanalyzer.model.JobPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class IndeedScraper
{
    public List<JobPost> scrape() throws Exception
    {
        String url = "https://www.indeed.com/jobs?q=software+engineer&l=";

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .timeout(10000)
                .get();

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