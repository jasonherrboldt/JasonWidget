package com.sps.jason;

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

        if(!verifyOutputDirectory(outputDirectory)) {
            throw new RuntimeException("Unable to create output directory " + outputDirectory);
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
     * Verify the output directory.
     *
     * @param outputDirectory The output directory to verify.
     * @return true if verified, false otherwise.
     */
    private static boolean verifyOutputDirectory(File outputDirectory) {
        boolean verified = true;
        // Create a new output directory if one does not already exist.
        if (!outputDirectory.isDirectory()) {
            if(!outputDirectory.mkdir()) {
                verified = false;
            }
        } else {
            // Clean up and reuse existing output directory.
            if(outputDirectory.list().length > 0) {
                try{
                    for(File f : outputDirectory.listFiles()) {
                        if (!f.delete()) {
                            verified = false;
                        }
                    }
                } catch (NullPointerException e) {
                    verified = false;
                }
            }
        }
        return verified;
    }
}
