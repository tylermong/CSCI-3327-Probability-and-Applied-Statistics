package dev.tylermong.jobanalyzer.scraper;

import dev.tylermong.jobanalyzer.model.JobPost;
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

public class GreenhouseScraper
{
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

    private String scrapeTitle(Document doc)
    {
        Element header = doc.selectFirst("h1.section-header.section-header--large.font-primary");
        if (header != null)
        {
            return header.text();
        }
        return "";
    }

    private String scrapeLocation(Document doc)
    {
        Element locDiv = doc.selectFirst("div.job__location > div");
        if (locDiv != null)
        {
            return locDiv.text().trim();
        }

        return "";
    }

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

            for (String word : words)
            {
                if (word.isEmpty())
                {
                    continue;
                }

                String cleanedWord = word.replaceAll("[.,!?]+$", "");
                if (cleanedWord.isEmpty()) {
                    continue;
                }

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
