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
     * @param args args[0] is the input directory to scan, and args[1] is the output directory to save altered files.
     */
    public static void main( String[] args )
    {
        // Blow up if the wrong number of command line arguments are received.
        if(args.length != 2) {
            throw new IllegalArgumentException(args.length + " is an invalid number of arguments. Args length must be 2.");
        }

        String inputDirectory = args[0];
        String outputDirectory = args[1];

        System.out.println("input directory: " + inputDirectory + ", output directory: " + outputDirectory);

        // Blow up if input directory does not exist.
        File directory = new File(inputDirectory);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + inputDirectory + ".\nPlease make sure the root directory is the first argument," +
                    " and it is wrapped in double quotes.");
        }

    }
}
