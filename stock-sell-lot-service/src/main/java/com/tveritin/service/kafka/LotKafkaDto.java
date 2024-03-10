package com.tveritin.service.kafka;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class LotKafkaDto {
    private UUID id;
    private UUID userId;
    private UUID stockId;
    private Integer numberOfStocks;
    private Integer priceForOneStock;
    private Boolean useActualPrice;
    private boolean isForSell;
}
