package com.tveritin.controller;

import com.tveritin.model.dto.BuyLotDto;
import com.tveritin.service.BuyLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BuyLotController implements BuyLotApi {
    private final BuyLotService buyLotService;

    @Override
    public ResponseEntity<UUID> createBuyLot(BuyLotDto buyLotDto) {
        UUID createdId = buyLotService.createBuyLot(buyLotDto);
        return new ResponseEntity<>(
                createdId,
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Void> deleteBuyLotById(String lotId) {
        buyLotService.deleteBuyLot(lotId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<BuyLotDto>> getAllBuyLots() {
        List<BuyLotDto> buyLots = buyLotService.getAllBuyLots();
        return ResponseEntity.ok(buyLots);
    }

    @Override
    public ResponseEntity<BuyLotDto> getBuyLotById(String lotId) {
        BuyLotDto buyLotDto = buyLotService.getBuyLotById(lotId);
        return ResponseEntity.ok(buyLotDto);
    }

    @Override
    public ResponseEntity<List<BuyLotDto>> getBuyLotsByStockId(String stockId) {
        List<BuyLotDto> buyLots = buyLotService.getBuyLotsByStockId(stockId);
        return ResponseEntity.ok(buyLots);
    }

    @Override
    public ResponseEntity<List<BuyLotDto>> getBuyLotsByUserId(String userId) {
        List<BuyLotDto> buyLots = buyLotService.getBuyLotsByUserId(userId);
        return ResponseEntity.ok(buyLots);
    }
}
