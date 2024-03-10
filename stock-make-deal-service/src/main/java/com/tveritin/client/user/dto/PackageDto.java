package com.tveritin.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
public class PackageDto {
    private UUID id;
    private StockDto stock;
    private Integer quantity;
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private OffsetDateTime lastChangeDateTime;

    public PackageDto() {
    }

    public PackageDto(StockDto stock, Integer quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public PackageDto id(UUID id) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public @Valid UUID getId() {
        return this.id;
    }

    public PackageDto stock(StockDto stock) {
        this.stock = stock;
        return this;
    }

    @JsonProperty("stock")
    public @NotNull @Valid StockDto getStock() {
        return this.stock;
    }

    public PackageDto quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonProperty("quantity")
    public @NotNull Integer getQuantity() {
        return this.quantity;
    }

    public PackageDto lastChangeDateTime(OffsetDateTime lastChangeDateTime) {
        this.lastChangeDateTime = lastChangeDateTime;
        return this;
    }

    @JsonProperty("lastChangeDateTime")
    public @Valid OffsetDateTime getLastChangeDateTime() {
        return this.lastChangeDateTime;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PackageDto packageDto = (PackageDto)o;
            return Objects.equals(this.id, packageDto.id) && Objects.equals(this.stock, packageDto.stock) && Objects.equals(this.quantity, packageDto.quantity) && Objects.equals(this.lastChangeDateTime, packageDto.lastChangeDateTime);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.stock, this.quantity, this.lastChangeDateTime});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PackageDto {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    stock: ").append(this.toIndentedString(this.stock)).append("\n");
        sb.append("    quantity: ").append(this.toIndentedString(this.quantity)).append("\n");
        sb.append("    lastChangeDateTime: ").append(this.toIndentedString(this.lastChangeDateTime)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
