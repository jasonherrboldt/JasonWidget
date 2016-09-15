package com.sps.jason;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Implements the system requirements.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class Widget {

    private String inputDirectory;
    private String outputDirectory;
    // private Logger logger = null;
    final Logger logger = Logger.getLogger(Widget.class);

    /**
     * Public constructor.
     *
     * @param inputDirectory The input directory to scan.
     * @param outputDirectory The output directory to save modified files.
     */
    public Widget(File inputDirectory, File outputDirectory) {
        logger.info("oh hai from the Widget constructor!");
        logger.info("input directory: " + inputDirectory + ", output directory: " + outputDirectory);
        // final Logger logger = Logger.getLogger(Widget.class);
        // Set up the logger.
        // BasicConfigurator.configure();
    }

    /**
     * Open files in the input directory and read them into memory.
     */
    public void readFilesIntoMemory() {
        // logger.info("test info message from readFilesIntoMemory");
        // logger.error("This is a test error message from readFilesIntoMemory");
    }

    /**
     * Parse an individual file, swap out strings per the system requirements, and save to output directory.
     *
     * @param fileToModifyAndSave The file to modify.
     */
    public void modifyAndSaveFile(File fileToModifyAndSave) {

    }



}



























