# JasonWidget
Requested coding exercise (under construction).

Excerpt from the submitted requirements (see SPSCodeExercise.txt for full specs):

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
* I elected to hardcode the replacement keys and values, as opposed to specifying them in a properties file.
* The input directory will not contain any subdirectories.

## Compile / Run Instructions

* Asdf
* Asdf
* Asdf

Asdf
