package com.tveritin.service;

import com.tveritin.clinet.BuyLotServiceClient;
import com.tveritin.clinet.SellLotServiceClient;
import com.tveritin.clinet.dto.BuyLotDto;
import com.tveritin.clinet.dto.SaleLotDto;
import com.tveritin.domain.Package;
import com.tveritin.domain.Usser;
import com.tveritin.mapper.LotMapper;
import com.tveritin.model.dto.LotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotService {
    private final LotMapper lotMapper;
    private final BuyLotServiceClient buyLotServiceClient;
    private final SellLotServiceClient sellLotServiceClient;


    private final UserService userService;
    private final PackageService packageService;

    public void createBuyLot(LotDto lotDto) {
        Usser usser = userService.findUserById(lotDto.getUserId());
        int totalPrice = (lotDto.getNumberOfStocks() * lotDto.getPriceForOneStock());

        if ((double) totalPrice <= usser.getBalance()) {
            BuyLotDto buyLotDto = lotMapper.convertLotDtoToBuyLotDto(lotDto);
            buyLotServiceClient.createBuyLot(buyLotDto);
        } else throw new RuntimeException("You need update balance.");
    }

    public void createSellLot(LotDto lotDto) {
        Usser usser = userService.findUserById(lotDto.getUserId());
        Package pack = packageService.findPackageByPortfolioAndStockId(usser.getPortfolio(), lotDto.getStockId());

        if (lotDto.getNumberOfStocks() <= pack.getQuantity()) {
            SaleLotDto saleLotDto = lotMapper.convertLotDtoToSaleLotDto(lotDto);
            sellLotServiceClient.createSaleLot(saleLotDto);
        } else throw new RuntimeException("You need update balance.");
    }
}
