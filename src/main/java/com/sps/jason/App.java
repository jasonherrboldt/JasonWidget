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

        //logs a debug message
        if(logger.isDebugEnabled()){
            logger.debug("This is a test debug message.");
        }

        //logs an error message with parameter
        logger.error("This is a test error message.");


        // Blow up if the wrong number of command line arguments are received.
        if(args.length != 2) {
            throw new IllegalArgumentException(args.length + " is an invalid number of arguments. Must be 2.");
        }

        File inputDirectory = new File(args[0]);
        File outputDirectory = new File(args[1]);

        if(!verifyInputDirectory(inputDirectory)) {
            throw new IllegalArgumentException("Unable to access directory " + inputDirectory +
                    ".\nPlease make sure the input directory is the first argument, and is wrapped in double quotes.");
        }

        String outputDirectoryCreationError = generateOutputDirectoryCreationError(outputDirectory);
        if(!outputDirectoryCreationError.equals("")) {
            throw new RuntimeException("Unable to create output directory " + outputDirectory + ". Error message: " +
                    outputDirectoryCreationError);
        }

        // Hand off the rest of the work to the Widget object.
        Widget widget = new Widget(inputDirectory, outputDirectory);

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
        String error = "";
        // Create a new output directory if one does not already exist.
        if (!outputDirectory.isDirectory()) {
            if(!outputDirectory.mkdir()) {
                error = "Unable to make new output directory" + outputDirectory;
            }
        } else {
            // Clean up and reuse existing output directory.
            if(outputDirectory.list().length > 0) {
                try{
                    for(File f : outputDirectory.listFiles()) {
                        if (!f.delete()) {
                            error = "Unable to delete existing file(s) in directory" + outputDirectory;
                        }
                    }
                } catch (NullPointerException e) {
                    error = "Encountered NullPointerException while attempting to delete existing files in directory" +
                            outputDirectory;
                }
            }
        }
        return error;
    }
}
