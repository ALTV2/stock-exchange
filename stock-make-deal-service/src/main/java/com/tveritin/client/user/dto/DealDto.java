package com.tveritin.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
public class DealDto {
    private UUID sellerId;
    private UUID buyerId;
    private UUID stockId;
    private Integer numberOfStocks;
    private Integer priceForOneStock;

    public DealDto() {
    }

    public DealDto sellerId(UUID sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    @JsonProperty("sellerId")
    public @Valid UUID getSellerId() {
        return this.sellerId;
    }

    public DealDto buyerId(UUID buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    @JsonProperty("buyerId")
    public @Valid UUID getBuyerId() {
        return this.buyerId;
    }

    public DealDto stockId(UUID stockId) {
        this.stockId = stockId;
        return this;
    }

    @JsonProperty("stockId")
    public @Valid UUID getStockId() {
        return this.stockId;
    }

    public DealDto numberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
        return this;
    }

    @JsonProperty("numberOfStocks")
    public Integer getNumberOfStocks() {
        return this.numberOfStocks;
    }

    public DealDto priceForOneStock(Integer priceForOneStock) {
        this.priceForOneStock = priceForOneStock;
        return this;
    }

    @JsonProperty("priceForOneStock")
    public Integer getPriceForOneStock() {
        return this.priceForOneStock;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DealDto dealDto = (DealDto)o;
            return Objects.equals(this.sellerId, dealDto.sellerId) && Objects.equals(this.buyerId, dealDto.buyerId) && Objects.equals(this.stockId, dealDto.stockId) && Objects.equals(this.numberOfStocks, dealDto.numberOfStocks) && Objects.equals(this.priceForOneStock, dealDto.priceForOneStock);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.sellerId, this.buyerId, this.stockId, this.numberOfStocks, this.priceForOneStock});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DealDto {\n");
        sb.append("    sellerId: ").append(this.toIndentedString(this.sellerId)).append("\n");
        sb.append("    buyerId: ").append(this.toIndentedString(this.buyerId)).append("\n");
        sb.append("    stockId: ").append(this.toIndentedString(this.stockId)).append("\n");
        sb.append("    numberOfStocks: ").append(this.toIndentedString(this.numberOfStocks)).append("\n");
        sb.append("    priceForOneStock: ").append(this.toIndentedString(this.priceForOneStock)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
