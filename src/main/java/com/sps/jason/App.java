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

        // Blow up if input directory does not exist.
        if (!inputDirectory.isDirectory()) {
            throw new IllegalArgumentException("Unable to access directory " + inputDirectory +
                    ".\nPlease make sure the input directory is the first argument, and is wrapped in double quotes.");
        }

        // Create a new output directory if one does not already exist. Blow up if output directory can't be created.
        if (!outputDirectory.isDirectory()) {
            if(!outputDirectory.mkdir()) {
                throw new RuntimeException("Unable to create output directory " + outputDirectory);
            }
        } else {
            // Clean up and reuse existing output directory.
            if(outputDirectory.list().length > 0) {
                try{
                    for(File f : outputDirectory.listFiles()) {
                        if (!f.delete()) {
                            throw new RuntimeException("Unable to remove existing files from output directory" + outputDirectory);
                        }
                    }
                } catch (NullPointerException e) {
                    throw new RuntimeException("Unable to remove existing files from output directory" + outputDirectory);
                }
            }
        }

        // Hand off the rest of the work to the Widget object.
        Widget widget = new Widget(inputDirectory, outputDirectory);

    }
}
