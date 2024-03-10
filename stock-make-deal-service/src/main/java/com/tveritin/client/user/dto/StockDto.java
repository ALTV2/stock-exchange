package com.tveritin.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
public class StockDto {
    private UUID id;
    private String ticketName;
    private String companyName;
    private Integer description;

    public StockDto() {
    }

    public StockDto(String ticketName, String companyName, Integer description) {
        this.ticketName = ticketName;
        this.companyName = companyName;
        this.description = description;
    }

    public StockDto id(UUID id) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public @Valid UUID getId() {
        return this.id;
    }

    public StockDto ticketName(String ticketName) {
        this.ticketName = ticketName;
        return this;
    }

    @JsonProperty("ticketName")
    public @NotNull String getTicketName() {
        return this.ticketName;
    }

    public StockDto companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @JsonProperty("companyName")
    public @NotNull String getCompanyName() {
        return this.companyName;
    }

    public StockDto description(Integer description) {
        this.description = description;
        return this;
    }

    @JsonProperty("description")
    public @NotNull Integer getDescription() {
        return this.description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            StockDto stockDto = (StockDto)o;
            return Objects.equals(this.id, stockDto.id) && Objects.equals(this.ticketName, stockDto.ticketName) && Objects.equals(this.companyName, stockDto.companyName) && Objects.equals(this.description, stockDto.description);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.ticketName, this.companyName, this.description});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StockDto {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    ticketName: ").append(this.toIndentedString(this.ticketName)).append("\n");
        sb.append("    companyName: ").append(this.toIndentedString(this.companyName)).append("\n");
        sb.append("    description: ").append(this.toIndentedString(this.description)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}