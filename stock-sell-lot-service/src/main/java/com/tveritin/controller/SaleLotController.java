package com.tveritin.controller;

import com.tveritin.model.dto.SaleLotDto;
import com.tveritin.service.SaleLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SaleLotController implements SaleLotApi {
    private final SaleLotService saleLotService;

    @Override
    public ResponseEntity<UUID> createSaleLot(SaleLotDto saleLotDto) {
        UUID createdId = saleLotService.createSaleLot(saleLotDto);
        return new ResponseEntity<>(
                createdId,
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Void> deleteSaleLotById(String lotId) {
        saleLotService.deleteSaleLot(lotId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SaleLotDto>> getAllSaleLots() {
        List<SaleLotDto> saleLots = saleLotService.getAllSaleLots();
        return ResponseEntity.ok(saleLots);
    }

    @Override
    public ResponseEntity<SaleLotDto> getSaleLotById(String lotId) {
        SaleLotDto saleLotDto = saleLotService.getSaleLotById(lotId);
        return ResponseEntity.ok(saleLotDto);
    }

    @Override
    public ResponseEntity<List<SaleLotDto>> getSaleLotsByStockId(String stockId) {
        List<SaleLotDto> saleLots = saleLotService.getSaleLotsByStockId(stockId);
        return ResponseEntity.ok(saleLots);
    }

    @Override
    public ResponseEntity<List<SaleLotDto>> getSaleLotsByUserId(String userId) {
        List<SaleLotDto> saleLots = saleLotService.getSaleLotsByUserId(userId);
        return ResponseEntity.ok(saleLots);
    }

    @Override
    public ResponseEntity<SaleLotDto> updateNUmberOfStocksSaleLotById(String lotId, Integer newNumberOfStocks) {
        SaleLotDto saleLotDto = saleLotService.updateNumberOfPrice(lotId, newNumberOfStocks);
        return ResponseEntity.ok(saleLotDto);
    }
}
