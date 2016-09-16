package com.sps.jason;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

/**
 * Implements the system requirements.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 9/15/16.
 */
public class Widget {

    private File inputDirectory;
    private File outputDirectory;
    private final Logger logger = Logger.getLogger(Widget.class);
    private static final String STRING_FIND = "monkey";
    private static final String STRING_REPLACE = "banana";

    /**
     * Public constructor.
     *
     * @param inputDirectory The input directory to scan.
     * @param outputDirectory The output directory to save modified files.
     */
    public Widget(File inputDirectory, File outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Uses multiple threads to process files in the input directory.
     */
    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        try {
            if(inputDirectory.listFiles() != null) {
                for(final File file : inputDirectory.listFiles()) {
                    service.submit(new Runnable() {
                        public void run() {
                            processFile(file);
                        }
                    });
                }
            }
        } catch (NullPointerException e) {
            logger.error("Exception thrown while processing files: " + e);
        }
    }

    /**
     * Helper method to process files per the system requirements.
     *
     * @param file The file to process.
     */
    private void processFile(File file) {
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

}