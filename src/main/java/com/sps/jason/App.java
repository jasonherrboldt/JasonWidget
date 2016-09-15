package com.sps.jason;

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
     * @param args 1) The input directory to scan, 2) and the output directory to save altered files.
     */
    public static void main( String[] args )
    {
        System.out.println("Hello from main!");

        // Fail if the wrong number of command line arguments are received.
        if(args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments received. Args length must be 2.");
        }

        System.out.println("Input directory: " + args[0] + ", output directory: " + args[1]);
    }
}
