package com.tveritin.utils;

import com.tveritin.domain.Stock;
import com.tveritin.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DmlScriptExecutor {

    private final StockRepository stockRepository;

    @PostConstruct
    public void executeDmlScripts() throws InterruptedException {
        Thread.sleep(10000);
        Stock apple = new Stock();
        apple.setCompanyName("Apple Inc");
        apple.setDescription("Производство электрического оборудования");
        apple.setTicketName("AAPL");

        Stock google = new Stock();
        google.setCompanyName("Alphabet Class C");
        google.setDescription("Google - американская транснациональная корпорация.");
        google.setTicketName("GOOG");

        stockRepository.save(google);
        stockRepository.save(apple);
    }
}
