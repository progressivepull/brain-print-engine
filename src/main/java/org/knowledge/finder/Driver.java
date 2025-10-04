package org.knowledge.finder;

import org.knowledge.finder.generate.ManagerGenerate;

/**
 * The {@code Driver} class serves as the entry point for the application.
 * <p>
 * It initializes project configuration parameters such as input files,
 * output files, project names, lesson folder structure, and archive settings.
 * Then, it creates an instance of {@link ManagerGenerate} to process
 * lessons and context files.
 * </p>
 *
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Defines archive files and target archive directory.</li>
 *   <li>Sets input/output files for lesson processing.</li>
 *   <li>Configures project name and lesson folder naming convention.</li>
 *   <li>Specifies hyperlink reference to the context file.</li>
 *   <li>Creates {@link ManagerGenerate} instance and invokes {@code process()}.</li>
 * </ol>
 *
 * <p>
 * This class demonstrates a typical use case of the system:
 * reading from an input file, generating lessons and context,
 * and organizing files into structured directories.
 * </p>
 *
 * @author 
 * @version 1.0
 */
public class Driver {

    /**
     * Main method and the starting point of the application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {

        // List of files that should be archived after processing
        String[] archivefiles = { "conversion.log", "context.md", "application.log" };

        // Directory where archived files will be stored
        String archiveDir = "archive";

        // Input text file containing lesson or webpage content
        String inputFile = "webpage.txt";

        // Output file where processed context will be written
        String outputFile = "context.md";

        // Project name (used as root directory for generated content)
        String projectName = "AI_Solutions_Developer";

        // Prefix for naming lesson folders
        String lessonFolderName = "LESSON_";

        // Hyperlink reference to the context file (used inside generated lesson markdown files)
        String contextHyperLink = "## [Context](./../context.md)";

        // Create an instance of ManagerGenerate with configured parameters
        ManagerGenerate contextGenerate = new ManagerGenerate(
                archivefiles,       // Files to be archived
                archiveDir,         // Archive directory
                inputFile,          // Input file
                outputFile,         // Output file
                projectName,        // Project name
                lessonFolderName,   // Lesson folder prefix
                contextHyperLink    // Hyperlink to context
        );

        // Start the process: generate context, lessons, folders, and archive files
        contextGenerate.process();
    }
}
