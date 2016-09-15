package com.sps.jason;

import java.io.File;

/**
 * Implements the system requirements.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class Widget {

    private String inputDirectory;
    private String outputDirectory;

    /**
     * Public constructor.
     *
     * @param inputDirectory The input directory to scan.
     * @param outputDirectory The output directory to save altered files.
     */
    public Widget(File inputDirectory, File outputDirectory) {
        System.out.println("oh hai from the Widget constructor!");
        System.out.println("input directory: " + inputDirectory + ", output directory: " + outputDirectory);


    }

}
