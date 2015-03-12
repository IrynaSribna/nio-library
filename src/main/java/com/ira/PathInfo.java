package com.ira;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by iryna.sribna on 12.03.2015.
 */
public class PathInfo {

    public static void main(String[] args) throws IOException {
        Path testFilePath = Paths.get("C:\\my1\\my\\test.txt");   // not existing path
        System.out.println("Printing file information:");
        System.out.println("\t file name: " + testFilePath.getFileName());
        System.out.println("\t root of the path: " + testFilePath.getRoot());
        System.out.println("\t parent of the target: " + testFilePath.getParent());

        //print path elements:
        System.out.println("Printing elements of the path:");
        for(Path element : testFilePath) {
            System.out.println("\t path element: " + element);
        }

        System.out.println("=====================================================================================");
        //normalize(), toAbsolutePath(), toRelativePath()
        Path testFilePath2 = Paths.get(".\\.\\Test"); //removes dots
        System.out.println("The file name is: " + testFilePath2.getFileName());
        System.out.println("Its URI is: " + testFilePath2.toUri());
        System.out.println("Its absolute path is: " + testFilePath2.toAbsolutePath());
        System.out.println("Its normalized path is: " + testFilePath2.normalize());

        System.out.println("=====================================================================================");
        Path testPathNormalized = Paths.get(testFilePath2.normalize().toString());
        System.out.println("Normalized absolute path: " + testPathNormalized.toAbsolutePath());
        System.out.println("Normalized real path: " + testPathNormalized.toRealPath(LinkOption.NOFOLLOW_LINKS)); //file should exist

        System.out.println("=====================================================================================");
        //resolve()
        Path dirName = Paths.get("C:\\my\\Java\\nio-library");
        Path resolvePath = dirName.resolve("Test");
        System.out.println(resolvePath);

    }
}
