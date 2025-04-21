package dev.tylermong.jobanalyzer.scraper;

import dev.tylermong.jobanalyzer.model.JobPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        String title = doc.selectFirst("h2[data-automation-id=jobPostingHeader]").text();
        String location = doc.selectFirst("dd.css-129m7dg").text();
        String company = scrapeCompany(doc);
        String description = doc.text();
        List<String> skills = scrapeSkills(description);

        JobPost job = new JobPost(company, title, location, skills, url);

        return job;
    }
    
    private List<String> scrapeSkills(String text)
    {
        List<String> skills = new ArrayList<>();

        for (String skill : VALID_SKILLS)
        {
            if (text.toLowerCase().contains(skill.toLowerCase()))
            {
                skills.add(skill);
            }
        }
        
        return skills;
    }

    private String scrapeCompany(Document doc)
    {
        Element script = doc.selectFirst("script[type=application/ld+json]");
        
        if (script != null)
        {
            String json = script.html();
            Pattern pattern = Pattern.compile("\\\"hiringOrganization\\\"\\s*:\\s*\\\\{[\\\\s\\\\S]*?\\\"name\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find())
            {
                return matcher.group(1);
            }
        }
        return "";
    }
}
