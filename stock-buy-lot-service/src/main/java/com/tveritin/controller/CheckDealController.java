package com.tveritin.controller;

import com.tveritin.model.dto.ApproveDellOfferDto;
import com.tveritin.model.dto.DealOfferDto;
import com.tveritin.service.CheckDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckDealController implements CheckDealApi{
    private final CheckDealService checkDealService;

    @Override
    public ResponseEntity<ApproveDellOfferDto> checkDeal(DealOfferDto dealOfferDto) {
        ApproveDellOfferDto approveDellOfferDto = checkDealService.check(dealOfferDto);
        return ResponseEntity.ok(approveDellOfferDto);
    }
}
