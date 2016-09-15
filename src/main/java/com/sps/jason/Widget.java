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
     * @param outputDirectory The output directory to save modified files.
     */
    public Widget(File inputDirectory, File outputDirectory) {
        System.out.println("oh hai from the Widget constructor!");
        System.out.println("input directory: " + inputDirectory + ", output directory: " + outputDirectory);


    }

    /**
     * Open files in the input directory and read them into memory.
     *
     * @return true if successful, false otherwise.
     */
    public boolean readFilesIntoMemory() {
        return true;
    }

    /**
     * Parse an individual file, swap out strings per the system requirements, and save to output directory.
     *
     * @param fileToModify The file to modify.
     */
    public void modifyAndSaveFile(File fileToModify) {

    }



}



























