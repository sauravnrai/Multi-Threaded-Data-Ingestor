package com.ingestor.consumer;

import com.ingestor.config.RabbitMQConfig;
import com.ingestor.model.User;
import com.ingestor.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvConsumer {
    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(String message) {
        long start = System.currentTimeMillis();
        List<User> users = Arrays.stream(message.split("\n"))
            .map(this::parseLine)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        userRepository.saveAll(users);
        long end = System.currentTimeMillis();
        System.out.println("Batch processed in: " + (end - start) + " ms");
    }

    private User parseLine(String line) {
        try {
            String[] parts = line.split(",");
            String[] name = parts[0].split(" ", 2);
            return new User(name[0], name.length > 1 ? name[1] : "", parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
        } catch (Exception e) {
            return null;
        }
    }
}