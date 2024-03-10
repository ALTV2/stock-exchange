package com.tveritin.service.kafka;

import com.tveritin.service.MakeDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final MakeDealService makeDealService;

    @KafkaListener(topics = "fresh_open_lots")
    public void receiveLotDto(LotKafkaDto lotDto) {
//        LotKafkaDto lotDto = map.get("payload");
        if (lotDto.isForSell() == true) {
            makeDealService.processForSell(lotDto);
        } else {
            makeDealService.processForBuy(lotDto);
        }
    }
}

