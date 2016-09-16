package com.sps.jason;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Implements the system requirements.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class Widget {

    private File inputDirectory;
    private File outputDirectory;
    private final Logger logger = Logger.getLogger(Widget.class);
    private List<File> filesInMemory;

    /**
     * Public constructor.
     *
     * @param inputDirectory The input directory to scan.
     * @param outputDirectory The output directory to save modified files.
     */
    public Widget(File inputDirectory, File outputDirectory) {
//        logger.info("oh hai from the Widget constructor!");
//        logger.info("input directory: " + inputDirectory + ", output directory: " + outputDirectory);
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        // filesInMemory = new ArrayList<>();
    }

    /**
     * Open files in the input directory and read them into memory.
     */
    public void readFilesIntoMemory() {
        File[] filesInDirectory = inputDirectory.listFiles();
        if(filesInDirectory != null) {
            filesInMemory = new ArrayList<>(Arrays.asList(filesInDirectory));
        }
        System.out.println("Files discovered:");
        // Charset charset= Charset.forName("US-ASCII");
        Charset charset = StandardCharsets.UTF_8;
        try {
            for(File f: filesInMemory) {
                System.out.println("File " + f.getName() + ": \n");
                String content = new String(Files.readAllBytes(f.toPath()), charset);
                System.out.println(content + "\n\n\n");
            }
        } catch (IOException e) {

        }
    }

    /**
     * Parse an individual file, swap out strings per the system requirements, and save to output directory.
     *
     * @param fileToModifyAndSave The file to modify.
     */
    public void modifyAndSaveFile(File fileToModifyAndSave) {

    }

}



























