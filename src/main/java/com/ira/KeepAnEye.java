package com.ira;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by iryna.sribna on 13.03.2015.
 */
public class KeepAnEye {
    public static void main(String[] args) {
        Path path = Paths.get("..\\src");
        WatchService watchService = null;

        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //infinite loop
        for (;;) {
            WatchKey key = null;
            try{
                key = watchService.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //iterate each event
            for (WatchEvent<?> event : key.pollEvents()) {
                switch (event.kind().name()) {
                    case "OVERFLOW":
                        System.out.println("We lost some events");
                        break;
                    case "ENTRY_MODIFY":
                        System.out.println("File " + event.context() + " is changed");
                        break;

                }
            }
            key.reset(); //reset to get new notifications
        }

    }
}
