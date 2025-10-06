package org.knowledge.finder;

import org.knowledge.finder.generate.ManagerGenerate;

/**
 * The {@code Driver} class serves as the entry point for the Knowledge Finder application.
 * <p>
 * This constructor is not used directly, as the class is designed to run via its {@code main} method.
 * 
 * It configures the environment for lesson and context generation by defining input/output files,
 * project metadata, folder naming conventions, and archival settings. Once configured, it delegates
 * the processing workflow to an instance of {@link ManagerGenerate}.
 * </p>
 *
 * <h2>Workflow Overview:</h2>
 * <ol>
 *   <li>Define files to be archived after processing.</li>
 *   <li>Specify input and output files for lesson and context generation.</li>
 *   <li>Set project name and lesson folder naming prefix.</li>
 *   <li>Provide hyperlink reference to the context file for use in generated lessons.</li>
 *   <li>Instantiate {@link ManagerGenerate} with all parameters.</li>
 *   <li>Invoke {@code process()} to execute the full generation and archival pipeline.</li>
 * </ol>
 *
 * <p>
 * This class demonstrates a typical use case of the system:
 * reading structured content from an input file, generating lesson folders and markdown files,
 * linking them to a shared context, and archiving relevant artifacts.
 * </p>
 *
 * <p><strong>Author:</strong> John Smith</p>
 * <p><strong>Version:</strong> 1.0</p>
 */
public class Driver {

    /**
     * Main method and the starting point of the application.
     * <p>
     * This method sets up all required configuration parameters and triggers the generation process.
     * Command-line arguments are currently unused but reserved for future extensibility.
     * </p>
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {

        

        // Files to be archived after processing (e.g., logs and context)
        String[] archivefiles = {
            "conversion.log",
            "context.md",
            "application.log"
        };

        // Directory where archived files will be stored
        String archiveDir = "archive";

        // Input file containing raw lesson or webpage content
        String inputFile = "webpage.txt";

        // Output file where processed context will be written
        String outputFile = "context.md";

        // Project name used as the root directory for generated content
        String projectName = "TEST_FILES";

        // Prefix for naming lesson folders (e.g., LESSON_1, LESSON_2)
        String lessonFolderName = "LESSON_";

        // Hyperlink reference to the context file (used in generated lesson markdown files)
        String contextHyperLink = "## [Context](./../context.md)";

        // Instantiate ManagerGenerate with all configuration parameters
        ManagerGenerate contextGenerate = new ManagerGenerate(
            archivefiles,
            archiveDir,
            inputFile,
            outputFile,
            projectName,
            lessonFolderName,
            contextHyperLink
        );

        // Execute the full generation pipeline: context, lessons, folders, and archiving
        contextGenerate.process();
    }
}
