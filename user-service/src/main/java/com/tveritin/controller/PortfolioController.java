package com.tveritin.controller;

import com.tveritin.model.dto.PortfolioDto;
import com.tveritin.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PortfolioController implements PortfolioApi{
    private final PortfolioService portfolioService;

    @Override
    public ResponseEntity<PortfolioDto> getPortfolio(String portfolioId) {
        PortfolioDto portfolioDto = portfolioService.getPortfolioById(portfolioId);
        return ResponseEntity.ok(portfolioDto);
    }
}
