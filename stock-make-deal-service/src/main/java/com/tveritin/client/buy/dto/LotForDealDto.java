package com.tveritin.client.buy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
public class LotForDealDto {
    private UUID userId;
    private Integer numberOfStocks;
    private Integer priceForOneStock;
    private Boolean closedLot;
    private Integer totalPrice;

    public LotForDealDto() {
    }

    public LotForDealDto userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("userId")
    public @Valid UUID getUserId() {
        return this.userId;
    }

    public LotForDealDto numberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
        return this;
    }

    @JsonProperty("numberOfStocks")
    public Integer getNumberOfStocks() {
        return this.numberOfStocks;
    }

    public LotForDealDto priceForOneStock(Integer priceForOneStock) {
        this.priceForOneStock = priceForOneStock;
        return this;
    }

    @JsonProperty("priceForOneStock")
    public Integer getPriceForOneStock() {
        return this.priceForOneStock;
    }

    public LotForDealDto closedLot(Boolean closedLot) {
        this.closedLot = closedLot;
        return this;
    }

    @JsonProperty("closedLot")
    public Boolean getClosedLot() {
        return this.closedLot;
    }

    public LotForDealDto totalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @JsonProperty("totalPrice")
    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            LotForDealDto lotForDealDto = (LotForDealDto)o;
            return Objects.equals(this.userId, lotForDealDto.userId) && Objects.equals(this.numberOfStocks, lotForDealDto.numberOfStocks) && Objects.equals(this.priceForOneStock, lotForDealDto.priceForOneStock) && Objects.equals(this.closedLot, lotForDealDto.closedLot) && Objects.equals(this.totalPrice, lotForDealDto.totalPrice);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.userId, this.numberOfStocks, this.priceForOneStock, this.closedLot, this.totalPrice});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LotForDealDto {\n");
        sb.append("    userId: ").append(this.toIndentedString(this.userId)).append("\n");
        sb.append("    numberOfStocks: ").append(this.toIndentedString(this.numberOfStocks)).append("\n");
        sb.append("    priceForOneStock: ").append(this.toIndentedString(this.priceForOneStock)).append("\n");
        sb.append("    closedLot: ").append(this.toIndentedString(this.closedLot)).append("\n");
        sb.append("    totalPrice: ").append(this.toIndentedString(this.totalPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
