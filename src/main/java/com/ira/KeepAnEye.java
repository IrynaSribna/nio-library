package com.ira;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by iryna.sribna on 13.03.2015.
 */
public class KeepAnEye {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\my\\Java\\nio-library\\src");
        WatchService watchService = null;
        System.out.println("Hello");
        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //infinite loop
        while (true) {
            WatchKey key = null;
            try {
                if (watchService != null) {
                    key = watchService.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //iterate each event
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    switch (event.kind().name()) {
                        case "OVERFLOW":
                            System.out.println("We lost some events");
                            break;
                        case "ENTRY_CREATE":
                            System.out.println("File " + event.context() + " is created");
                            break;

                    }
                }
                key.reset(); //reset to get new notifications
            }
        }

    }
}
