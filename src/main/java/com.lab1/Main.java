package com.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    private static void printClasspath(){
        System.out.println("Classpath is:");
        System.out.println(System.getProperty("java.class.path"));
    }

    public static String loadPostfixNotationFromFile(String filename) throws IOException {
        //check for the file in the resources directory
        File file = new File(
                Main.class.getClassLoader().getResource(filename).getFile());
        if(file != null){
            System.out.println("File found!");

            //alternatively, you can pass in the absolute path to a file straight to this if you hardcode this.
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String postfix = br.readLine();
            return postfix;


        }else{
            System.out.println("File was not found on the classpath.");
            return "";
        }
    }

    //run this program as "$java
    public static void main(String[] args) throws IOException {

        //args should pass the file name to load.  The file must be in the classpath!

        //print out the loaded classpath for easy debug if files aren't found.
        printClasspath();

        //load the file provided.  Filename must be the first and only argument!
        System.out.println("Attempting to loading file " + args[0]);
        String postfixNotation = loadPostfixNotationFromFile(args[0]);
        System.out.println("Postfix notation is: " + postfixNotation);






    }


}
