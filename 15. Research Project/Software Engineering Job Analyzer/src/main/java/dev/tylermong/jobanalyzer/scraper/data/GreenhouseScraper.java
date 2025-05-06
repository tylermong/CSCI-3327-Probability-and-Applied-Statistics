package dev.tylermong.jobanalyzer.scraper.data;

import dev.tylermong.jobanalyzer.model.JobPost;
import dev.tylermong.jobanalyzer.util.SkillKeywords;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Data scraping functionality for job posts on Greenhouse.io.
 */
public class GreenhouseScraper implements DataScraper
{
    /**
     * Scrapes the job post from the given URL.
     * 
     * @param  url         the URL of the job post
     * @return             a JobPost object containing the scraped data
     * @throws IOException if an error occurs while connecting to the URL
     */
    public JobPost scrapeJob(String url) throws IOException
    {
        Document doc = Jsoup.connect(url).get();

        String company = scrapeCompany(doc);
        String title = scrapeTitle(doc);
        String location = scrapeLocation(doc);
        List<String> skills = scrapeSkills(doc);

        JobPost job = new JobPost(company, title, location, skills, url);

        return job;
    }

    /**
     * Scrapes the company name from the URL
     * 
     * @param  doc the document to scrape
     * @return     the company name
     */
    private String scrapeCompany(Document doc)
    {
        String url = doc.baseUri();
        Pattern pattern = Pattern.compile("greenhouse\\.io/([^/]+)/jobs");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return "";
    }

    /**
     * Scrapes the job title from the document
     * 
     * @param  doc the document to scrape
     * @return     the job title
     */
    private String scrapeTitle(Document doc)
    {
        Element header = doc.selectFirst("h1.section-header.section-header--large.font-primary");
        if (header != null)
        {
            return header.text();
        }
        return "";
    }

    /**
     * Scrapes the job location from the document
     * 
     * @param  doc the document to scrape
     * @return     the job location
     */
    private String scrapeLocation(Document doc)
    {
        Element locDiv = doc.selectFirst("div.job__location > div");
        if (locDiv != null)
        {
            return locDiv.text().trim();
        }

        return "";
    }

    /**
     * Scrapes the skills from the job description
     * 
     * @param  doc the document to scrape
     * @return     a list of skills found in the job description
     */
    private List<String> scrapeSkills(Document doc)
    {
        Element descDiv = doc.selectFirst("div.job__description.body");
        if (descDiv != null)
        {
            String descriptionText = descDiv.text();

            // Pre-process VALID_SKILLS for efficient lookup
            Map<String, String> normalizedSkillMap = new HashMap<>();
            for (String skill : SkillKeywords.VALID_SKILLS)
            {
                normalizedSkillMap.put(skill.toLowerCase(), skill);
            }

            Set<String> foundSkills = new HashSet<>();
            // Split the description into words/tokens, keeping +, #, and .
            String[] words = descriptionText.split("[^a-zA-Z0-9+#.]+");

            // Iterate through the words and check if they are in the VALID_SKILLS list
            for (String word : words)
            {
                if (word.isEmpty())
                {
                    continue;
                }

                // Remove trailing punctuation (.,!?)
                String cleanedWord = word.replaceAll("[.,!?]+$", "");
                if (cleanedWord.isEmpty())
                {
                    continue;
                }

                // Use lowercase for consistency
                String lowerWord = cleanedWord.toLowerCase();
                if (normalizedSkillMap.containsKey(lowerWord))
                {
                    foundSkills.add(normalizedSkillMap.get(lowerWord));
                }
            }
            return new ArrayList<>(foundSkills);
        }

        return new ArrayList<>();
    }
}
