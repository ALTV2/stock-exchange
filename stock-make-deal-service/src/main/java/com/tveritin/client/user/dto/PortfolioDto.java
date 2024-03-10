package com.tveritin.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
public class PortfolioDto {
    private UUID id;
    private @Valid List<@Valid PackageDto> packages = new ArrayList<>();
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private OffsetDateTime lastChangeDateTime;

    public PortfolioDto() {
    }

    public PortfolioDto(List<@Valid PackageDto> packages) {
        this.packages = packages;
    }

    public PortfolioDto id(UUID id) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public @Valid UUID getId() {
        return this.id;
    }

    public PortfolioDto packages(List<@Valid PackageDto> packages) {
        this.packages = packages;
        return this;
    }

    public PortfolioDto addPackagesItem(PackageDto packagesItem) {
        if (this.packages == null) {
            this.packages = new ArrayList<>();
        }

        this.packages.add(packagesItem);
        return this;
    }

    @JsonProperty("packages")
    public @NotNull @Valid List<@Valid PackageDto> getPackages() {
        return this.packages;
    }

    public PortfolioDto lastChangeDateTime(OffsetDateTime lastChangeDateTime) {
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
            PortfolioDto portfolioDto = (PortfolioDto)o;
            return Objects.equals(this.id, portfolioDto.id) && Objects.equals(this.packages, portfolioDto.packages) && Objects.equals(this.lastChangeDateTime, portfolioDto.lastChangeDateTime);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.packages, this.lastChangeDateTime});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PortfolioDto {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    packages: ").append(this.toIndentedString(this.packages)).append("\n");
        sb.append("    lastChangeDateTime: ").append(this.toIndentedString(this.lastChangeDateTime)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}