# JasonWidget
Requested coding exercise.

Excerpt from the submitted requirements (see CodeExercise.txt for full specs):

    Design, code a deliver a simple widget that scans an input folder taking in each
    individual file as a job. Input folder can be specified on the command-line or in a
    properties file. For each file the code should read in the contents and
    replace all instances of certain text strings with alternative text strings. These
    replacement keys and values can be hard-coded or read in from properties. The
    resulting modified text should be output to a configured output folder with an
    extension appended to the filename to indicate processed. Aside from any debug logging
    the tool should log at INFO level a line for each file successfully processed.

## Assumptions / Decisions

* I elected to inject the input and output directory names into the main method, as opposed to specifying them in a properties file.
* The injected input and output directory names will be wrapped in double quotes to handle directories with spaces in their names.
* The output directory will be created at runtime, or will clean and reuse a directory if it already exists.
* I elected to hardcode the replacement keys and values, as opposed to specifying them in a properties file. (All occurrences of the string "monkey" are replaced with "banana".)
* The input directory will not contain any subdirectories.
* I decided to skip unit tests because the replaceAll() functionality is already included in the Java String class, and has (presumably) already been tested to death. (There was no mention in the requirements document about not using common utility packages.) The rest of the work done by this program -- opening files, creating new ones, saving them to a directory -- falls under the scope of functional testing rather than unit testing.
* I elected to not use any kind of Collection interface because of the straightforward nature of multithreading I went with. (This is in the spirit of "The intent is to keep the scope simple and the time to complete short" from the requirements document.

## Compile / Run Instructions

* $ git clone [CLONE_URL]
* Open project in Intellij Idea
* Copy the files in the text_files directory and paste them into the desired local input directory.
* Notice that the text files contain only the words "asdf" and "monkey".
* Open the Maven Projects tab. Under Lifecycle, click Install.
* Open Edit Configurations and paste the desired input and output directories (respectively) into Program Arguments field. Be sure to wrap them in double quotes.
* Open Main.java, right click, and select Run 'Main.main()'.
* Notice the time-stamped log4j entries in the console.
* Navigate to output directory and inspect generated files -- all instances of the string "monkey" have been replaced with "banana".