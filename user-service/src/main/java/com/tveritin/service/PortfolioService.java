package com.tveritin.service;

import com.tveritin.domain.Package;
import com.tveritin.domain.Portfolio;
import com.tveritin.domain.Usser;
import com.tveritin.mapper.PortfolioMapper;
import com.tveritin.model.dto.PortfolioDto;
import com.tveritin.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    private final PackageService packageService;
    private final StockService stockService;

    public PortfolioDto getPortfolioById(String portfolioId) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(UUID.fromString(portfolioId));
        if (portfolio.isPresent()) {
            return portfolioMapper.convertPortfolioToPortfolioDto(portfolio.get());
        } else throw new NoSuchElementException();
    }

    public Portfolio createPortfolio(Usser usser) {
        final Portfolio portfolio = new Portfolio();
        portfolio.setLastChangeDateTime(LocalDateTime.now());
        portfolio.setUsser(usser);
        Portfolio createdPortfolio = portfolioRepository.save(portfolio);

        List<Package> packages = new ArrayList<>();
        stockService.getAllStocks()
                .forEach(stock -> {
                    packages.add(packageService.createDefaultPackage(portfolio, stock));
                });

        createdPortfolio.setPackages(packages);
        return portfolioRepository.save(portfolio);
    }
}
