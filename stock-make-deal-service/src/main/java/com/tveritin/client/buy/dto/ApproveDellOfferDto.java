package com.tveritin.client.buy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class ApproveDellOfferDto {
    private Boolean approved;
    private Integer remaining;
    private @Valid List<@Valid LotForDealDto> lots;

    public ApproveDellOfferDto() {
    }

    public ApproveDellOfferDto approved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    @JsonProperty("approved")
    public Boolean getApproved() {
        return this.approved;
    }

    public ApproveDellOfferDto lots(List<@Valid LotForDealDto> lots) {
        this.lots = lots;
        return this;
    }

    public ApproveDellOfferDto addLotsItem(LotForDealDto lotsItem) {
        if (this.lots == null) {
            this.lots = new ArrayList();
        }

        this.lots.add(lotsItem);
        return this;
    }

    @JsonProperty("lots")
    public @Valid List<@Valid LotForDealDto> getLots() {
        return this.lots;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ApproveDellOfferDto approveDellOfferDto = (ApproveDellOfferDto)o;
            return Objects.equals(this.approved, approveDellOfferDto.approved) && Objects.equals(this.lots, approveDellOfferDto.lots);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.approved, this.lots});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApproveDellOfferDto {\n");
        sb.append("    approved: ").append(this.toIndentedString(this.approved)).append("\n");
        sb.append("    lots: ").append(this.toIndentedString(this.lots)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
