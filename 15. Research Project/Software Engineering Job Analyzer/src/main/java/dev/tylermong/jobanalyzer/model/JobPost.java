package dev.tylermong.jobanalyzer.model;

import java.util.List;

public class JobPost
{
    private String title;
    private String description;
    private List<String> skills;
    private boolean degreeRequired;
    private String experienceYears;
    private String source;

    public JobPost(String title, String description, List<String> skills, boolean degreeRequired, String experienceYears, String source)
    {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.degreeRequired = degreeRequired;
        this.experienceYears = experienceYears;
        this.source = source;
    }

    public String toCSV()
    {
        return title + "," +
               String.join(";", skills) + "," +
               degreeRequired + "," +
               experienceYears;
    }
}