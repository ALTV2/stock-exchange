package com.tveritin.service;

import com.tveritin.domain.BuyLot;
import com.tveritin.mapper.BuyLotMapper;
import com.tveritin.model.dto.BuyLotDto;
import com.tveritin.repository.BuyLotRepository;
import com.tveritin.service.kafka.KafkaProducerService;
import com.tveritin.service.kafka.LotKafkaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuyLotService {
    private final BuyLotRepository buyLotRepository;
    private final BuyLotMapper buyLotMapper;
    private final KafkaProducerService kafkaProducerService;

    public UUID createBuyLot(BuyLotDto buyLotDto) {
        BuyLot buyLot = buyLotMapper.convertDtoToBuyLot(buyLotDto);
        buyLot.setCreationDateTime(LocalDateTime.now());
        buyLot.setTotalPrice(((long) buyLotDto.getPriceForOneStock() * buyLotDto.getNumberOfStocks()));
        buyLot = buyLotRepository.save(buyLot);

        LotKafkaDto lotKafkaDto = buyLotMapper.convertBuyLotToLotKafkaDto(buyLot);
        kafkaProducerService.sendMessage("fresh_open_lots", lotKafkaDto);
        return buyLot.getId();
    }

    public void deleteBuyLot(String lotId) {
        buyLotRepository.deleteById(UUID.fromString(lotId));
    }

    public List<BuyLotDto> getAllBuyLots() {
        List<BuyLot> buyLots = buyLotRepository.findAll();

        return buyLotMapper.convertBuyLotListToDtoList(buyLots);
    }

    public BuyLotDto getBuyLotById(String lotId) {
        Optional<BuyLot> buyLot = buyLotRepository.findById(UUID.fromString(lotId));
        if (buyLot.isPresent()) {
            return buyLotMapper.convertBuyLotToDto(buyLot.get());
        } else throw new NoSuchElementException();
    }

    public List<BuyLotDto> getBuyLotsByStockId(String stockId) {
        List<BuyLot> buyLots = buyLotRepository.findBuyLotsByStockId(UUID.fromString(stockId));
        return buyLotMapper.convertBuyLotListToDtoList(buyLots);
    }

    public List<BuyLotDto> getBuyLotsByUserId(String userId) {
        List<BuyLot> buyLots = buyLotRepository.findBuyLotsByUserId(UUID.fromString(userId));
        return buyLotMapper.convertBuyLotListToDtoList(buyLots);
    }

    public void reduceAmount(BuyLot lot, Integer amount) {
        lot.setNumberOfStocks(lot.getNumberOfStocks() - amount);
        buyLotRepository.save(lot);
    }
}
