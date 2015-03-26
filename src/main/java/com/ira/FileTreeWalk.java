package com.ira;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by iryna.sribna on 13.03.2015.
 */
class MyFileVisitor extends SimpleFileVisitor<Path> {
    private Path source, destination;

    public MyFileVisitor(Path s, Path d) {
        source = s;
        destination = d;
    }

    public FileVisitResult visitFile(Path sourcePath, BasicFileAttributes fileAttributes) {
        Path newd = destination.resolve(source.relativize(sourcePath));

        try {
            Files.copy(sourcePath, newd, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("----------------------- Directory name: " + path.getParent() + "----------------------------");
        return FileVisitResult.CONTINUE;
    }
}

public class FileTreeWalk {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage FileTreeWalk <source-path> <destination-path>");
            System.exit(-1);
        }

        Path pathSource = Paths.get(args[0]);
        Path pathDestination = Paths.get(args[1]);
        try {
            Files.walkFileTree(pathSource, new MyFileVisitor(pathSource,pathDestination));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
