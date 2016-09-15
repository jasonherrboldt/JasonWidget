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
     * @param args args[0] is the name of the input directory to scan,
     *             args[1] is the name of the output directory to save altered files.
     */
    public static void main( String[] args )
    {
        // Blow up if the wrong number of command line arguments are received.
        if(args.length != 2) {
            throw new IllegalArgumentException(args.length + " is an invalid number of arguments. Args length must be 2.");
        }

        File inputDirectory = new File(args[0]);
        File outputDirectory = new File(args[1]);

        // Blow up if input directory does not exist.
        if (!inputDirectory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + inputDirectory +
                    ".\nPlease make sure the input directory is the first argument, and it is wrapped in double quotes.");
        }

        // Create a new output directory if one does not already exist. Blow up if output directory can't be created.
        if (!outputDirectory.exists()) {
            if(!outputDirectory.mkdir()) {
                throw new RuntimeException("Unable to create output directory " + outputDirectory);
            }
        }

        // Hand off the rest of the work to the Widget object.
        Widget widget = new Widget(inputDirectory, outputDirectory);

    }
}
