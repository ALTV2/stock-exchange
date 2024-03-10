package com.tveritin.repository;

import com.tveritin.domain.Package;
import com.tveritin.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {
    Optional<Package> findByStockIdAndPortfolio (UUID stockId, Portfolio portfolio);
}
