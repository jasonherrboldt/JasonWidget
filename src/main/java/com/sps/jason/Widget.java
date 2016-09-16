package com.sps.jason;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    // The number of sections into which filesInMemory will be split for multithreading.
    private static final int SECTIONS = 4;
    private static final int NUMBER_OF_THREADS = 5;
    private static final String STRING_FIND = "monkey";
    private static final String STRING_REPLACE = "banana";

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
            List<?> synchList = Collections.synchronizedList(filesInMemory);
        }
        /*
        Charset charset = StandardCharsets.UTF_8;
        try {
            for(File f: filesInMemory) {
                String content = new String(Files.readAllBytes(f.toPath()), charset);
                content = content.replaceAll(STRING_FIND, STRING_REPLACE);
                PrintWriter writer = new PrintWriter(outputDirectory + "/(processed)_" + f.getName());
                writer.print(content);
                writer.close();
                logger.info("File " + f.getName() + " was successfully processed.");
            }
        } catch (IOException e) {
            logger.error("Encountered exception while replacing strings in a file. " + e);
        }
        */
    }

    /**
     * Process the files in memory using multithreading.
     */
    public void processFilesInMemory() {

//        logger.info("There are " + filesInMemory.size() + " files in memory.");
//
//        // Number of files in memory can be evenly divided into the specified number of sections.
//        if(filesInMemory.size() % SECTIONS == 0) {
//            logger.info("Files in memory CAN be split evenly into four sections.");
//            // List<Integer> head = numbers.subList(0, 4);
//            for(int i = 0; i < filesInMemory.size(); i += 4) {
//                logger.info("i : " + i);
//            }
//
//
//        } else {
//            int remainder = filesInMemory.size() % SECTIONS;
//            logger.info("Files in memory can NOT be split evenly into four sections.");
//            for(int i = 0; i < filesInMemory.size(); i += filesInMemory.size() / SECTIONS) {
//                logger.info("i : " + i);
//                List<File> sublist = filesInMemory.subList(i, i + 4);
//                logger.info("I am thread " + i + " and I am responsible for files:");
//                for(File f : sublist) {
//                    logger.info(f.getName());
//                }
//            }
//        }


    }

    /**
     * Parse an individual file, swap out strings per the system requirements, and save to output directory.
     *
     * @param fileToModifyAndSave The file to modify.
     */
    private void modifyAndSaveFile(File fileToModifyAndSave) {

    }

    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            for(final File file : inputDirectory.listFiles()){
                service.submit(new Runnable(){
                    public void run(){
                        // logger.info("File name: " + file.getName());
                        Charset charset = StandardCharsets.UTF_8;
                        try {
                            String content = new String(Files.readAllBytes(file.toPath()), charset);
                            content = content.replaceAll(STRING_FIND, STRING_REPLACE);
                            PrintWriter writer = new PrintWriter(outputDirectory + "/(processed)_" + file.getName());
                            writer.print(content);
                            writer.close();
                            logger.info("File " + file.getName() + " was successfully processed.");
                        } catch (IOException e) {
                            logger.error("Encountered exception while replacing strings in a file. " + e);
                        }
                    }
                });
            }
        } catch (NullPointerException e) {
            logger.error("Exception thrown while processing files: " + e);
        }
    }

}



























