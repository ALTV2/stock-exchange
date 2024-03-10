package com.tveritin.service;

import com.tveritin.client.buy.BuyLotServiceClient;
import com.tveritin.client.buy.dto.ApproveDellOfferDto;
import com.tveritin.client.buy.dto.DealOfferDto;
import com.tveritin.client.sell.SellLotServiceServiceClient;
import com.tveritin.client.user.UserServiceClient;
import com.tveritin.client.user.dto.DealDto;
import com.tveritin.service.kafka.LotKafkaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeDealService {
    private final UserServiceClient userServiceClient;
    private final BuyLotServiceClient buyLotServiceClient;
    private final SellLotServiceServiceClient sellLotServiceClient;

    public void processForSell(LotKafkaDto lotDto) {
        DealOfferDto dealOfferDto = new DealOfferDto();
        dealOfferDto.setMinPrise(lotDto.getPriceForOneStock());
        dealOfferDto.setStockId(lotDto.getStockId());
        dealOfferDto.setNumberOfStocks(lotDto.getNumberOfStocks());

        //Выбираем лоты и удолем их в сервисе лотов покупок
        ApproveDellOfferDto approveDellOfferDto = buyLotServiceClient.checkDeal(dealOfferDto).getBody();

        //удаляем или уменьшаем лот в сервисе лотов продажи
        if (approveDellOfferDto.getApproved()) {
            sellLotServiceClient.deleteSaleLotById(lotDto.getId().toString());
        } else {
            sellLotServiceClient.deleteSaleLotById(lotDto.getId().toString());
            sellLotServiceClient.updateNUmberOfStocksSaleLotById(lotDto.getId().toString(), approveDellOfferDto.getRemaining());
        }

        approveDellOfferDto.getLots().forEach(lot -> {
            DealDto dealDto = new DealDto();
            dealDto.setSellerId(lotDto.getUserId());
            dealDto.setBuyerId(lot.getUserId());
            dealDto.setStockId(lotDto.getStockId());
            dealDto.setNumberOfStocks(lot.getNumberOfStocks());
            dealDto.setPriceForOneStock(lot.getPriceForOneStock());
            userServiceClient.makeDeal(dealDto);
        });
    }
    public void processForBuy(LotKafkaDto lotDto) {
        //тут должен быть обратный процесс
        System.out.println("not impl");
    }
}
