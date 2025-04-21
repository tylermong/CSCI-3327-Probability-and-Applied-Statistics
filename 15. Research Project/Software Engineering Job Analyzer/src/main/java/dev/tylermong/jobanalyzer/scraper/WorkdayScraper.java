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

public class WorkdayScraper
{
    private final Set<String> VALID_SKILLS = Set.of(
        // Programming Languages
        "Java", "Python", "C++", "C#", "JavaScript", "TypeScript", "Go", "Rust", "Swift", "Kotlin", "Ruby", "PHP", "Scala", "Perl", "HTML", "CSS",

        // Frontend Frameworks/Libraries
        "React", "Angular", "Vue.js", "Svelte", "jQuery",

        // Backend Frameworks/Libraries
        "Node.js", "Express", "Django", "Flask", "Ruby on Rails", "Spring Boot", "ASP.NET", "Laravel",

        // Databases
        "SQL", "MySQL", "PostgreSQL", "SQLite", "MongoDB", "Cassandra", "Redis", "Oracle", "SQL Server",

        // Cloud Platforms
        "AWS", "Azure", "Google Cloud", "GCP",

        // DevOps & Tools
        "Docker", "Kubernetes", "Jenkins", "Git", "Terraform", "Ansible", "CI/CD", "Jira",

        // Operating Systems
        "Linux", "Windows", "macOS",

        // Methodologies
        "Agile", "Scrum", "Kanban",

        // Concepts
        "Data Structures", "Algorithms", "OOP", "Object-Oriented Programming", "Functional Programming", "REST", "API", "GraphQL", "Microservices",
        "Machine Learning", "ML", "Artificial Intelligence", "AI", "Data Science", "Big Data", "Cybersecurity", "Networking", "System Design",

        // Mobile Development
        "Android", "iOS", "React Native", "Flutter",

        // Testing
        "JUnit", "PyTest", "Selenium", "Cypress", "Jest"
    );

    public JobPost scrapeJob(String url) throws IOException
    {
        Document doc = Jsoup.connect(url).get();

        System.out.println(doc.html());
        String company = scrapeCompany(doc);
        String title = scrapeTitle(doc);
        String location = scrapeLocation(doc);
        List<String> skills = scrapeSkills(doc);

        JobPost job = new JobPost(company, title, location, skills, url);

        return job;
    }

    private String scrapeCompany(Document doc)
    {
        Element script = doc.selectFirst("script[type=application/ld+json]");
        
        if (script != null)
        {
            String json = script.html();
            Pattern pattern = Pattern.compile("\\\"hiringOrganization\\\"\\s*:\\s*\\{[^}]*?\\\"name\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find())
            {
                return matcher.group(1);
            }
        }
        return "";
    }

    private String scrapeTitle(Document doc)
    {
        Element script = doc.selectFirst("script[type=application/ld+json]");

        if (script != null)
        {
            String json = script.html();
            Pattern pattern = Pattern.compile("\\\"title\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find())
            {
                return matcher.group(1);
            }
        }
        return "";
    }

    private String scrapeLocation(Document doc)
    {
        Element script = doc.selectFirst("script[type=application/ld+json]");

        if (script != null)
        {
            String json = script.html();
            Pattern pattern = Pattern.compile("\\\"addressLocality\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find())
            {
                return matcher.group(1);
            }
        }
        return "";
    }
    
    private List<String> scrapeSkills(Document doc)
    {
        Element script = doc.selectFirst("script[type=application/ld+json]");

        if (script != null)
        {
            String json = script.html();
            Pattern pattern = Pattern.compile("\\\"description\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(json);

            if (matcher.find())
            {
                String descriptionText = matcher.group(1);
                String unescapedText = descriptionText.replace("\\\\n", " ").replace("\\\"", "\"");

                Set<String> foundSkills = new HashSet<>();
                // Pre-process VALID_SKILLS for efficient lookup
                Map<String, String> normalizedSkillMap = new HashMap<>();
                for (String skill : VALID_SKILLS)
                {
                    normalizedSkillMap.put(skill.toLowerCase(), skill);
                }

                // Split the description into words/tokens, keeping relevant chars like +, #, .
                // This regex splits by one or more characters that are NOT letters, digits, +, #, or .
                String[] words = unescapedText.split("[^a-zA-Z0-9+#.]+");

                for (String word : words)
                {
                    if (word.isEmpty())
                    {
                        continue;
                    }
                    String lowerWord = word.toLowerCase();
                    if (normalizedSkillMap.containsKey(lowerWord))
                    {
                        foundSkills.add(normalizedSkillMap.get(lowerWord));
                    }
                }
                return new ArrayList<>(foundSkills);
            }
        }
        return new ArrayList<>();
    }
}
