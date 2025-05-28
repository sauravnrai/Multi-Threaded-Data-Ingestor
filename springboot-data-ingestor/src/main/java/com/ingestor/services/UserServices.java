package com.ingestor.services;

import com.ingestor.producer.CsvProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class UserServices {
    @Autowired
    private CsvProducer producer;

    public void processCsv(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> batch = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                batch.add(line);
                if (batch.size() == 100) {
                    producer.sendBatch(new ArrayList<>(batch));
                    batch.clear();
                }
            }
            if (!batch.isEmpty()) {
                producer.sendBatch(batch);
            }
        }
    }
}
