package com.tveritin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Portfolio portfolio;

    @ManyToOne
    private Stock stock;

    @Column
    private Integer quantity;

    @Column
    private LocalDateTime lastChangeDateTime;


    public void setId(UUID id) {
        this.id = id;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setQuantity(Integer quantity) {
        System.out.println("Setting of :" + quantity);
        this.quantity = quantity;
    }

    public void setLastChangeDateTime(LocalDateTime lastChangeDateTime) {
        this.lastChangeDateTime = lastChangeDateTime;
    }
}
