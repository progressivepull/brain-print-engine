package org.knowledge.finder.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import org.knowledge.finder.data.LessonsData;

/**
 * The {@code ContextGenerate} class is responsible for generating a structured
 * project directory containing lesson-based markdown files from an input text file.
 * <p>
 * It reads content from an input file, processes headings and lessons, and creates:
 * <ul>
 *   <li>A main {@code context.md} file with structured links to lessons.</li>
 *   <li>Subdirectories for each lesson section.</li>
 *   <li>Individual markdown files for each subsection of a lesson.</li>
 * </ul>
 * </p>
 * <p>
 * This class uses {@link LessonsData} to manage lessons and section headings,
 * and handles all I/O operations required for file and directory creation.
 * </p>
 */
public class ContextGenerate {

    private static final Logger logger = Logger.getLogger(ContextGenerate.class.getName());

    // File names for reading and writing
    private String inputFileName;
    private String outputFileName;

    // Name of the project (used as the root directory name)
    private String projectName;

    // Path object representing the project directory
    private Path directoryProject;    

    // Data object holding parsed lesson titles and subsections
    private LessonsData lessonsData;

    // Stores folder names for later creation
    private List<String> folderNamesList = new ArrayList<>();

    // Base prefix used when naming lesson folders
    private String lessonFolderName;

    // Footer link or page reference appended to each generated markdown file
    private String contextLinkPage;

    /**
     * Constructs a {@code ContextGenerate} instance with the required configuration.
     *
     * @param inputFileName     the input file containing raw lesson content
     * @param outputFileName    the output file name for processed content
     * @param projectNameWithDash the project name, also used as directory name
     * @param lessonsData       the {@link LessonsData} object storing processed lessons
     * @param lessonFolderName  the base name for lesson subdirectories
     * @param contextLinkPage   the common link or footer text appended in each markdown file
     */
    public ContextGenerate(String inputFileName, String outputFileName, String projectNameWithDash,
                           LessonsData lessonsData, String lessonFolderName, String contextLinkPage) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.projectName = projectNameWithDash;
        this.lessonsData = lessonsData;
        this.lessonFolderName = lessonFolderName;
        this.contextLinkPage = contextLinkPage;
    }

    /** @return the input file name */
    public String getInputFileName() { return inputFileName; }

    /** @param inputFileName the input file name to set */
    public void setInputFileName(String inputFileName) { this.inputFileName = inputFileName; }

    /** @return the output file name */
    public String getOutputFileName() { return outputFileName; }

    /** @param outputFileName the output file name to set */
    public void setOutputFileName(String outputFileName) { this.outputFileName = outputFileName; }

    /** @return the project name */
    public String getProjectName() { return projectName; }

    /** @param projectName the project name to set */
    public void setProjectName(String projectName) { this.projectName = projectName; }

    /** @return the project directory path */
    public Path getDirectoryProject() { return directoryProject; }

    /** @param directoryProject the project directory to set */
    public void setDirectoryProject(Path directoryProject) { this.directoryProject = directoryProject; }

    /** @return the lessons data object */
    public LessonsData getLessonsData() { return lessonsData; }

    /** @param lessonsData the lessons data to set */
    public void setLessonsData(LessonsData lessonsData) { this.lessonsData = lessonsData; }

    /**
     * Checks whether a given line represents a section heading.
     *
     * @param line a line from the input file
     * @return {@code true} if the line starts with "# ", otherwise {@code false}
     */
    public boolean isSectionHeading(String line) {
        return line.startsWith("# ");
    }

    /**
     * Main processing method that orchestrates the workflow:
     * <ol>
     *   <li>Creates the project directory.</li>
     *   <li>Reads and parses the input file.</li>
     *   <li>Populates {@link LessonsData} with headings and lessons.</li>
     *   <li>Generates {@code context.md} with links to lesson subsections.</li>
     *   <li>Creates subfolders and markdown files for each lesson subsection.</li>
     * </ol>
     */
    public void process() {
        // Step 1: Create root directory for the project
        createDir();

        // Path of the main context.md file
        Path contextFile = directoryProject.resolve("context.md");

        // Step 2: Read input file and populate LessonsData
        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(contextFile.toFile()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Headings are treated as section titles, others as lesson entries
            	//System.out.println(line);
            	
                if (isSectionHeading(line)) {
                    lessonsData.setTitle(line);
                } else {
                    lessonsData.setLessons(line);
                }
            }
            
            lessonsData.printTitles();
            lessonsData.printLessons();
            lessonsData.printLessonsCount();

            // Finalize lessons data (internally organizes it)
            lessonsData.done();

            // Step 3: Write structured headings and lessons into context.md
            for (int sectionNumber = 1; sectionNumber < lessonsData.getLessonsCount().size(); sectionNumber++) {
            	//System.out.println("Section Number : " + sectionNumber);
                handleHeading(writer, sectionNumber);
                handleLesson(writer, sectionNumber);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during file conversion: " + e.getMessage(), e);
        }

        // Step 4: Create subfolders and markdown lesson files
        createSubfoldersAndMarkdownFiles();
    }

    /**
     * Creates the project root directory using the project name.
     * If the directory already exists, it will not be recreated.
     */
    public void createDir() {
        directoryProject = Paths.get(projectName);
        try {
            Files.createDirectories(directoryProject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles writing a section heading to the context file and stores its folder name.
     *
     * @param writer        the {@link BufferedWriter} for writing to the context file
     * @param sectionNumber the current section number
     * @throws IOException if an I/O error occurs
     */
    public void handleHeading(BufferedWriter writer, Integer sectionNumber) throws IOException {
        // Add lesson folder name to internal list for later use
        folderNamesList.add(this.lessonFolderName + sectionNumber);

        // Fetch the heading (title) from lessonsData
        String line = lessonsData.getTitles().get(sectionNumber);

        // Write the heading to context.md
        writer.write(line + "\n \r");
    }

    /**
     * Handles writing lesson subsections as links into the context file.
     *
     * @param writer        the {@link BufferedWriter} for writing to the context file
     * @param sectionNumber the current section number
     * @throws IOException if an I/O error occurs
     */
    public void handleLesson(BufferedWriter writer, Integer sectionNumber) throws IOException {
        // Get number of subsections under this section
    	
    	//System.out.println("______________Section Number_______________" + sectionNumber);
        int subSectionCount = lessonsData.getLessonsCount().get(sectionNumber);
        
        //System.out.println("Sub Section Count : " + subSectionCount);

        // For each subsection, generate a markdown link
        for (int subSectionNumber = 1; subSectionNumber < subSectionCount; subSectionNumber++) {
            String line = lessonsData.getLessons().get(sectionNumber + "." + subSectionNumber);

            // Convert lesson name to a markdown link
            String markDownLine = "* [ " + line.replace("_", " ") + " ]( ./"
                    + lessonFolderName + sectionNumber + "/" + line.replace(" ", "_") + ".md )";

            // Write the link into context.md
            writer.write(markDownLine + "\n \r");
        }
    }

    /**
     * Creates subfolders for each lesson section and generates individual
     * markdown files for all subsections.
     * <p>
     * Each markdown file contains a title and a link reference defined by {@code contextLinkPage}.
     * </p>
     */
    public void createSubfoldersAndMarkdownFiles() {
        for (String folderName : folderNamesList) {
            Path subfolderPath = directoryProject.resolve(folderName);

            try {
                // Create subdirectory for lesson section
                Files.createDirectories(subfolderPath);

                // Determine which section this folder corresponds to
                int sectionNumber = Integer.parseInt(folderName.replace(this.lessonFolderName, ""));
                int subSectionCount = lessonsData.getLessonsCount().get(sectionNumber);

                // For each subsection, create a markdown file
                for (int subSectionNumber = 1; subSectionNumber <= subSectionCount; subSectionNumber++) {
                    String line = lessonsData.getLessons().get(sectionNumber + "." + subSectionNumber);

                    // Build filename (spaces replaced with underscores)
                    String fileName = line.replace(" ", "_") + ".md";
                    Path markdownFilePath = subfolderPath.resolve(fileName);

                    // Content of each markdown file
                    String fileContent = "# " + line.replace("_", " ") + " \n \r \n \r " + contextLinkPage;

                    // Write content into markdown file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(markdownFilePath.toFile()))) {
                        writer.write(fileContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
