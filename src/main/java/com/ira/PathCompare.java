package com.ira;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by iryna.sribna on 12.03.2015.
 */
public class PathCompare {

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("Test");
        Path path2 = Paths.get("C:\\my\\Java\\nio-library\\Test");

        //compareTo()
        System.out.println("path1.compareTo(path2) == 0 is: " + (path1.compareTo(path2) == 0));  // 0 means equal, returned result -> false

        //equals()
        System.out.println("path1.equals(path2) is: " + path1.equals(path2)); //not equal, returned result -> false


        //equals() using absolutePath()
        System.out.println("path2.equals(path1.toAbsolutePath()) is: " + path2.equals(path1.toAbsolutePath())); //true

        //isSameFile()
        System.out.println("Files.isSameFile(path1, path2) is: " + Files.isSameFile(path1, path2) + "\n"); //true

        //readAttributes()
        System.out.println("Basic File attributes: \n" + Files.readAttributes(path1, "*") + "\n");
        System.out.println("Dos File attributes: \n" + Files.readAttributes(path1, "dos:*") + "\n");        //for DOS
        //System.out.println("Posix File attributes: \n" + Files.readAttributes(path1, "posIx:*") + "\n"); //for Unix

        BasicFileAttributes fileAttributes = Files.readAttributes(path1, BasicFileAttributes.class);
        System.out.println("isDirectory: " + fileAttributes.isDirectory());
        System.out.println("File last accessed time: " + fileAttributes.lastAccessTime());
    }
}
