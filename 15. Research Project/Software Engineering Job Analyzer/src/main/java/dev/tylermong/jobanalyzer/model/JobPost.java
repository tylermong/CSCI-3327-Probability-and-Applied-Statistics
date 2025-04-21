package dev.tylermong.jobanalyzer.model;

import java.util.List;

public class JobPost
{
    private String company;
    private String title;
    private String location;
    private List<String> skills;
    private String url;

    public JobPost(String company, String title, String location, List<String> skills, String url)
    {
        this.company = company;
        this.title = title;
        this.location = location;
        this.skills = skills;
        this.url = url;
    }
    
    public String getCompany()
    {
        return company;
    }

    public String getTitle()
    {
        return title;
    }

    public String getLocation()
    {
        return location;
    }

    public String getUrl()
    {
        return url;
    }

    public List<String> getSkills()
    {
        return skills;
    }
}