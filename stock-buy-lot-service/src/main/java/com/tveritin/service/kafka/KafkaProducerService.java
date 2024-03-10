package com.tveritin.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, LotKafkaDto> kafkaTemplate;

    public void sendMessage(String topic, LotKafkaDto message) {
        kafkaTemplate.send(topic, message);
    }
}

