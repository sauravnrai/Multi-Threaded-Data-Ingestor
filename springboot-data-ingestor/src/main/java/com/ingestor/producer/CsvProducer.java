package com.ingestor.producer;

import com.ingestor.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendBatch(List<String> batchLines) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, String.join("\n", batchLines));
    }
}