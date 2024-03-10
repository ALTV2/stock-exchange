package com.tveritin.client.buy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
public class DealOfferDto {
    private UUID stockId;
    private Integer minPrise;
    private Integer numberOfStocks;

    public DealOfferDto() {
    }

    public DealOfferDto stockId(UUID stockId) {
        this.stockId = stockId;
        return this;
    }

    @JsonProperty("stockId")
    public @Valid UUID getStockId() {
        return this.stockId;
    }

    public DealOfferDto minPrise(Integer minPrise) {
        this.minPrise = minPrise;
        return this;
    }

    @JsonProperty("minPrise")
    public Integer getMinPrise() {
        return this.minPrise;
    }

    public DealOfferDto numberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
        return this;
    }

    @JsonProperty("numberOfStocks")
    public Integer getNumberOfStocks() {
        return this.numberOfStocks;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DealOfferDto dealOfferDto = (DealOfferDto)o;
            return Objects.equals(this.stockId, dealOfferDto.stockId) && Objects.equals(this.minPrise, dealOfferDto.minPrise) && Objects.equals(this.numberOfStocks, dealOfferDto.numberOfStocks);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.stockId, this.minPrise, this.numberOfStocks});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DealOfferDto {\n");
        sb.append("    stockId: ").append(this.toIndentedString(this.stockId)).append("\n");
        sb.append("    minPrise: ").append(this.toIndentedString(this.minPrise)).append("\n");
        sb.append("    numberOfStocks: ").append(this.toIndentedString(this.numberOfStocks)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
