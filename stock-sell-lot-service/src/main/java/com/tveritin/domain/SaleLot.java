package com.tveritin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SaleLot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID stockId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long numberOfStocks;

    @Column(nullable = false)
    private Long priceForOneStock;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @Column
    private boolean saleForActualPrice;

    //???
    @Column(nullable = false)
    private Long totalPrice;
}
