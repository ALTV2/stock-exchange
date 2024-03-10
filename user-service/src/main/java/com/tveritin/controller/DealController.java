package com.tveritin.controller;

import com.tveritin.model.dto.DealDto;
import com.tveritin.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DealController implements DealApi{
    private final DealService dealService;

    @Override
    public ResponseEntity<Void> makeDeal(DealDto dealDto) {
        dealService.makeDeal(dealDto);
        return ResponseEntity.ok().build();
    }
}
