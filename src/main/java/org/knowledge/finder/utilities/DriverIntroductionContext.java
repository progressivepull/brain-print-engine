package org.knowledge.finder.utilities;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DriverIntroductionContext {

    public static void main(String[] args) {

    	Path inputFile = Paths.get("intro.txt");
    	Path outputFile = Paths.get("intro_contexts.txt");
        
    	
    	try {
        	
            List<String> lines = Files.readAllLines(inputFile);

            List<String> lessons = new ArrayList<>();

            for (String line : lines) {
                line = line.trim();

                if (!line.isEmpty()) {
                    lessons.add(line);
                }
            }

            StringBuilder output = new StringBuilder();

            output.append("# 0. Introduction\n\n");

            for (int i = 0; i < lessons.size(); i++) {

                String title = lessons.get(i);

                String folderName = String.format(
                    "0.%d_%s",
                    i + 1,
                    title.replaceAll("[^a-zA-Z0-9]+", "_")
                         .replaceAll("_+$", "")
                );

                String link = String.format(
                    "./LESSON_0/%s/%s.md",
                    folderName,
                    folderName
                );

                output.append("* [ ")
                      .append(String.format("0.%d %s", i + 1, title))
                      .append(" ]( ")
                      .append(link)
                      .append(" )\n\n");
            }

            Files.write(
            	    outputFile,
            	    output.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8)
            	);

            System.out.println("Successfully created: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}

