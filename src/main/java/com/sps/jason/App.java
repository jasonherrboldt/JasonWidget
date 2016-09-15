package com.sps.jason;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Main engine class.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class App
{
    /**
     * Main engine method.
     *
     * @param args args[0]: the name of the input directory to scan,
     *             args[1]: the name of the output directory to save modified files.
     */
    public static void main( String[] args )
    {
        final Logger logger = Logger.getLogger(App.class);
        BasicConfigurator.configure();

//        if(logger.isDebugEnabled()){
//            logger.debug("This is a test debug message.");
//        }
//
        // logger.error("This is a test error message.");


        // Blow up if the wrong number of command line arguments are received.
        if(args.length != 2) {
            String errorMessage = args.length + " is an invalid number of arguments. Must be 2.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        File inputDirectory = new File(args[0]);
        File outputDirectory = new File(args[1]);

        // Blow up if input directory doesn't exist.
        if(!verifyInputDirectory(inputDirectory)) {
            String errorMessage = "Unable to access directory " + inputDirectory +
                    ".\nPlease make sure the input directory is the first argument, and is wrapped in double quotes.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Blow up if any problems occur while creating a new output directory or cleaning up an old one.
        String outputDirectoryCreationError = generateOutputDirectoryCreationError(outputDirectory);
        if(!outputDirectoryCreationError.equals("")) {
            String errorMessage = "Unable to create output directory " + outputDirectory + ". Error message: " +
                    outputDirectoryCreationError;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // Hand off the rest of the work to the Widget object.
        Widget widget = new Widget(inputDirectory, outputDirectory);
        widget.readFilesIntoMemory();

    }

    /**
     * Verify the input diretory.
     *
     * @param inputDirectory The input directory to verify.
     * @return true if verified, false otherwise.
     */
    private static boolean verifyInputDirectory(File inputDirectory) {
        return inputDirectory.isDirectory();
    }

    /**
     * Generate an output directory creation error message.
     *
     * @param outputDirectory The output directory to verify.
     * @return The error string generated, or "" if no error generated.
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
                try{
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
