package com.tveritin.service;

import com.tveritin.domain.BuyLot;
import com.tveritin.model.dto.ApproveDellOfferDto;
import com.tveritin.model.dto.DealOfferDto;
import com.tveritin.model.dto.LotForDealDto;
import com.tveritin.repository.BuyLotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckDealService {
    private final BuyLotRepository buyLotRepository;
    private final BuyLotService buyLotService;

    public ApproveDellOfferDto check(DealOfferDto dealOfferDto) {
        List<BuyLot> lots = buyLotRepository.findBuyLotsByStockId(dealOfferDto.getStockId());
        ApproveDellOfferDto approveDellOfferDto = new ApproveDellOfferDto();
        int needNumber = dealOfferDto.getNumberOfStocks();
        List<LotForDealDto> approveLots = new ArrayList<>();

        for (BuyLot lot : lots) {
            if (lot.getPriceForOneStock() >= dealOfferDto.getMinPrise() && needNumber > 0) {
                LotForDealDto lotForDealDto = new LotForDealDto();

                if (lot.getNumberOfStocks() > needNumber) {
                    lotForDealDto.setClosedLot(false);
                    lotForDealDto.setPriceForOneStock(lot.getPriceForOneStock().intValue());
                    lotForDealDto.setUserId(lot.getUserId());
                    lotForDealDto.setNumberOfStocks(needNumber);
                    lotForDealDto.setTotalPrice((int) (needNumber * lot.getPriceForOneStock()));
                    buyLotService.reduceAmount(lot, needNumber);
                    needNumber = 0;
                } else {
                    lotForDealDto.setClosedLot(true);
                    lotForDealDto.setPriceForOneStock(lot.getPriceForOneStock().intValue());
                    lotForDealDto.setUserId(lot.getUserId());
                    lotForDealDto.setNumberOfStocks(lot.getNumberOfStocks().intValue());
                    lotForDealDto.setTotalPrice((int) (lot.getNumberOfStocks().intValue() * lot.getPriceForOneStock()));
                    needNumber -= lotForDealDto.getNumberOfStocks();
                    buyLotService.deleteBuyLot(lot.getId().toString());
                }
                approveLots.add(lotForDealDto);
            }
        }
        approveDellOfferDto.setLots(approveLots);
        approveDellOfferDto.setApproved(needNumber <= 0);
        approveDellOfferDto.setRemaining(needNumber);

        return approveDellOfferDto;
    }
}
