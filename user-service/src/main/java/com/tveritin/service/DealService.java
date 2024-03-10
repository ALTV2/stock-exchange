package com.tveritin.service;

import com.tveritin.domain.Package;
import com.tveritin.domain.Usser;
import com.tveritin.model.dto.DealDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {
    private final UserService userService;
    private final PackageService packageService;

    @Transactional
    public void makeDeal(DealDto dealDto) {
        Usser usserStockSeller = userService.findUserById(dealDto.getSellerId());
        Usser usserStockBuyer = userService.findUserById(dealDto.getBuyerId());

        Package sellerPackage = packageService.findPackageByPortfolioAndStockId(
                usserStockSeller.getPortfolio(),
                dealDto.getStockId()
        );

        Package buyerPackage = packageService.findPackageByPortfolioAndStockId(
                usserStockBuyer.getPortfolio(),
                dealDto.getStockId()
        );

        Double totalPrice = (double) (dealDto.getNumberOfStocks() * dealDto.getPriceForOneStock());
        if (sellerPackage.getQuantity() >= dealDto.getNumberOfStocks()
                && usserStockBuyer.getBalance() >= totalPrice) {

            usserStockSeller.setBalance(usserStockSeller.getBalance() + totalPrice);
            usserStockBuyer.setBalance(usserStockBuyer.getBalance() - totalPrice);

            sellerPackage.setQuantity(sellerPackage.getQuantity() - dealDto.getNumberOfStocks());
            buyerPackage.setQuantity(buyerPackage.getQuantity() + dealDto.getNumberOfStocks());

            packageService.updateQuantity(sellerPackage);
            packageService.updateQuantity(buyerPackage);

            userService.save(usserStockSeller);
            userService.save(usserStockBuyer);
        } else throw new RuntimeException() {
        };

    }

    private void changeMoneyAndStocks(Usser usserStockSeller, Usser usserStockBuyer) {

    }

    private void makeDealWhereInitiatorIsBuyer(DealDto dealDto) {

    }
}
