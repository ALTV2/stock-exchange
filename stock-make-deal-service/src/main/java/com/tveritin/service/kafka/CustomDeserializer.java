package com.tveritin.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class CustomDeserializer implements Deserializer<Object> {
    @Override
    public LotKafkaDto deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, LotKafkaDto.class);
        } catch (IOException e) {
            // Обработка ошибки десериализации
            return null;
        }
    }
}
