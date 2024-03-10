package com.tveritin.service;

import com.tveritin.domain.Package;
import com.tveritin.domain.Portfolio;
import com.tveritin.domain.Stock;
import com.tveritin.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    private static final Integer DEFAULT_QUANTITY = 50;

    public Package findPackageById(String packageId) {
        Optional<Package> pack = packageRepository.findById(UUID.fromString(packageId));
        if (pack.isPresent()) {
            return pack.get();
        } else throw new NoSuchElementException();
    }

    public Package findPackageByPortfolioAndStockId(Portfolio portfolio, UUID stockId) {
        Optional<Package> pack = packageRepository.findByStockIdAndPortfolio(stockId, portfolio);
        if (pack.isPresent()) {
            return pack.get();
        } else throw new NoSuchElementException();
    }

    public void updateQuantity(Package pack) {
        packageRepository.save(pack);
    }

    public Package createDefaultPackage(Portfolio portfolio, Stock stock) {
        Package pack = new Package();
        pack.setQuantity(DEFAULT_QUANTITY);
        pack.setStock(stock);
        pack.setLastChangeDateTime(LocalDateTime.now());
        pack.setPortfolio(portfolio);

        pack = packageRepository.save(pack);
        return pack;
    }
}
