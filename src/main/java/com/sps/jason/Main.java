package com.sps.jason;

import java.io.File;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Main engine class.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class Main
{
    /**
     * Main engine method.
     *
     * @param args args[0]: the name of the input directory to scan,
     *             args[1]: the name of the output directory to save modified files.
     */
    public static void main(String[] args)
    {
        // Set up the logger.
        final Logger logger = Logger.getLogger(Main.class);
        BasicConfigurator.configure();

        // Make sure the correct number of command line arguments was received.
        if(args.length != 2) {
            String errorMessage = args.length + " is an invalid number of arguments. Must be 2.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        File inputDirectory = new File(args[0]);
        File outputDirectory = new File(args[1]);

        // Make sure the input directory exists.
        if(!inputDirectory.isDirectory()) {
            String errorMessage = "Unable to access directory " + inputDirectory +
                    ".\nPlease make sure the input directory is the first argument, and is wrapped in double quotes.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Create the output directory if it doesn't exist, or clean / reuse if it does.
        String outputDirectoryCreationErrorMessage = generateOutputDirectoryCreationError(outputDirectory);
        if(!outputDirectoryCreationErrorMessage.equals("")) {
            String errorMessage = "Unable to create output directory " + outputDirectory + ". Error message: " +
                    outputDirectoryCreationErrorMessage;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // Hand the rest of the work off to the Widget object.
        Widget widget = new Widget(inputDirectory, outputDirectory);
        widget.run();
        // widget.readFilesIntoMemory();
        // widget.processFilesInMemory();
    }

    /**
     * Create / reuse an output directory, and generate an output directory creation error message in the process.
     *
     * @param outputDirectory The output directory to verify.
     * @return The error string generated, or "" if no error is generated.
     */
    private static String generateOutputDirectoryCreationError(File outputDirectory) {
        String errorMessage = "";
        // Create a new output directory if one does not already exist.
        if (!outputDirectory.isDirectory()) {
            if(!outputDirectory.mkdir()) {
                errorMessage = "Unable to make new output directory" + outputDirectory;
            }
        } else {
            // Clean up and reuse existing output directory.
            if(outputDirectory.list().length > 0) {
                try {
                    for(File f : outputDirectory.listFiles()) {
                        if (!f.delete()) {
                            errorMessage = "Unable to delete existing file(s) in directory" + outputDirectory;
                        }
                    }
                } catch (NullPointerException e) {
                    errorMessage = "Encountered NullPointerException while attempting to delete existing files in directory" +
                            outputDirectory;
                }
            }
        }
        return errorMessage;
    }
}