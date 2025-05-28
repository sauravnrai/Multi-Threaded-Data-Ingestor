package com.ingestor.watcher;

import com.ingestor.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryWatcher implements CommandLineRunner {
    @Autowired
    private UserServices userServices;

    @Value("${csv.watch.dir:data}")
    private String watchDir;

    @Override
    public void run(String... args) throws Exception {
        File dir = new File(watchDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".csv"));
        if (files != null) {
            for (File file : files) {
                userServices.processCsv(file);
            }
        }
    }
}
