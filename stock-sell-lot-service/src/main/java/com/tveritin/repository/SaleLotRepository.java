package com.tveritin.repository;

import com.tveritin.domain.SaleLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleLotRepository extends JpaRepository<SaleLot, UUID> {

    List<SaleLot> findSaleLotsByUserId(UUID userId);

    List<SaleLot> findSaleLotsByStockId(UUID userId);

}
