package com.tveritin.controller;

import com.tveritin.model.dto.LotDto;
import com.tveritin.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotController implements LotApi {
    private final LotService lotService;

    @Override
    public ResponseEntity<Void> createBuyLot(LotDto lotDto) {
        lotService.createBuyLot(lotDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createSellLot(LotDto lotDto) {
        lotService.createSellLot(lotDto);
        return ResponseEntity.ok().build();
    }
}
