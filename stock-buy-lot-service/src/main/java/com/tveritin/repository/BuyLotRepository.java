package com.tveritin.repository;

import com.tveritin.domain.BuyLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BuyLotRepository extends JpaRepository<BuyLot, UUID> {

    List<BuyLot> findBuyLotsByUserId(UUID userId);

    List<BuyLot> findBuyLotsByStockId(UUID stockId);

    List<BuyLot> findBuyLotsByStockIdOrderByPriceForOneStockDesc(UUID stockId);

}
