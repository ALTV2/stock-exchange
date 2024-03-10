package com.tveritin.clinet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public class SaleLotDto {
    private UUID id;
    private UUID stockId;
    private UUID userId;
    private Integer numberOfStocks;
    private Integer priceForOneStock;
    private Integer totalPrice;
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private OffsetDateTime creationDateTime;
    private Boolean saleForActualPrice;

    public SaleLotDto() {
    }

    public SaleLotDto id(UUID id) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public @Valid UUID getId() {
        return this.id;
    }

    public SaleLotDto stockId(UUID stockId) {
        this.stockId = stockId;
        return this;
    }

    @JsonProperty("stockId")
    public @Valid UUID getStockId() {
        return this.stockId;
    }

    public SaleLotDto userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("userId")
    public @Valid UUID getUserId() {
        return this.userId;
    }

    public SaleLotDto numberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
        return this;
    }

    @JsonProperty("numberOfStocks")
    public Integer getNumberOfStocks() {
        return this.numberOfStocks;
    }

    public SaleLotDto priceForOneStock(Integer priceForOneStock) {
        this.priceForOneStock = priceForOneStock;
        return this;
    }

    @JsonProperty("priceForOneStock")
    public Integer getPriceForOneStock() {
        return this.priceForOneStock;
    }

    public SaleLotDto totalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @JsonProperty("totalPrice")
    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public SaleLotDto creationDateTime(OffsetDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }

    @JsonProperty("creationDateTime")
    public @Valid OffsetDateTime getCreationDateTime() {
        return this.creationDateTime;
    }

    public SaleLotDto saleForActualPrice(Boolean saleForActualPrice) {
        this.saleForActualPrice = saleForActualPrice;
        return this;
    }

    @JsonProperty("saleForActualPrice")
    public Boolean getSaleForActualPrice() {
        return this.saleForActualPrice;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            SaleLotDto saleLotDto = (SaleLotDto)o;
            return Objects.equals(this.id, saleLotDto.id) && Objects.equals(this.stockId, saleLotDto.stockId) && Objects.equals(this.userId, saleLotDto.userId) && Objects.equals(this.numberOfStocks, saleLotDto.numberOfStocks) && Objects.equals(this.priceForOneStock, saleLotDto.priceForOneStock) && Objects.equals(this.totalPrice, saleLotDto.totalPrice) && Objects.equals(this.creationDateTime, saleLotDto.creationDateTime) && Objects.equals(this.saleForActualPrice, saleLotDto.saleForActualPrice);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.stockId, this.userId, this.numberOfStocks, this.priceForOneStock, this.totalPrice, this.creationDateTime, this.saleForActualPrice});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SaleLotDto {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    stockId: ").append(this.toIndentedString(this.stockId)).append("\n");
        sb.append("    userId: ").append(this.toIndentedString(this.userId)).append("\n");
        sb.append("    numberOfStocks: ").append(this.toIndentedString(this.numberOfStocks)).append("\n");
        sb.append("    priceForOneStock: ").append(this.toIndentedString(this.priceForOneStock)).append("\n");
        sb.append("    totalPrice: ").append(this.toIndentedString(this.totalPrice)).append("\n");
        sb.append("    creationDateTime: ").append(this.toIndentedString(this.creationDateTime)).append("\n");
        sb.append("    saleForActualPrice: ").append(this.toIndentedString(this.saleForActualPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
