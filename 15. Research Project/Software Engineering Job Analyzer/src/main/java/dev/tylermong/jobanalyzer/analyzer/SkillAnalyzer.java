package dev.tylermong.jobanalyzer.analyzer;

import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class SkillAnalyzer
{
    public static void writeSkillFrequencyToFile() throws IOException
    {
        File input = new File("15. Research Project/Software Engineering Job Analyzer/DataResults.txt");
        File output = new File("15. Research Project/Software Engineering Job Analyzer/SkillFrequency.txt");

        System.out.println("\nWriting skill frequency...");
        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output)))
        {
            String line;
            Map<String, Integer> skillFrequency = new HashMap<>();

            while ((line = reader.readLine()) != null)
            {
                // Check if the line contains skills
                if (line.trim().startsWith("Skills: ["))
                {
                    // Extract the skills string between the brackets
                    String skillsString = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

                    // Handle empty skill lists
                    if (skillsString.isEmpty())
                    {
                        continue;
                    }

                    // Split the skills string by comma and space
                    String[] skills = skillsString.split(", ");
                    for (String skill : skills)
                    {
                        // Trim whitespace from each skill before counting
                        String trimmedSkill = skill.trim();
                        if (!trimmedSkill.isEmpty())
                        {
                            skillFrequency.put(trimmedSkill, skillFrequency.getOrDefault(trimmedSkill, 0) + 1);
                        }
                    }
                }
            }

            // Convert the map entries to a list
            List<Map.Entry<String, Integer>> sortedSkills = new ArrayList<>(skillFrequency.entrySet());

            // Sort the list by frequency in descending order
            sortedSkills.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            // Write the sorted skill frequencies to the output file
            for (Map.Entry<String, Integer> entry : sortedSkills)
            {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading or writing files: " + e.getMessage());
            throw e;
        }
        System.out.println("Skill frequency written to " + output.getPath());
    }
}
