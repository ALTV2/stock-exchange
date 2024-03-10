package com.tveritin.service;

import com.tveritin.domain.SaleLot;
import com.tveritin.mapper.SaleLotMapper;
import com.tveritin.model.dto.SaleLotDto;
import com.tveritin.repository.SaleLotRepository;
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
public class SaleLotService {
    private final SaleLotRepository saleLotRepository;
    private final SaleLotMapper saleLotMapper;
    private final KafkaProducerService kafkaProducerService;

    public UUID createSaleLot(SaleLotDto saleLotDto) {
        SaleLot saleLot = saleLotMapper.convertDtoToSaleLot(saleLotDto);
        saleLot.setCreationDateTime(LocalDateTime.now());
        saleLot.setTotalPrice(((long) saleLotDto.getPriceForOneStock() * saleLotDto.getNumberOfStocks()));
        saleLot = saleLotRepository.save(saleLot);

        LotKafkaDto lotKafkaDto = saleLotMapper.convertSaleLotToLotKafkaDto(saleLot);
        kafkaProducerService.sendMessage("fresh_open_lots", lotKafkaDto);
        return saleLot.getId();
    }

    public void deleteSaleLot(String lotId) {
        saleLotRepository.deleteById(UUID.fromString(lotId));
    }

    public List<SaleLotDto> getAllSaleLots() {
        List<SaleLot> saleLots = saleLotRepository.findAll();

        return saleLotMapper.convertSaleLotListToDtoList(saleLots);
    }

    public SaleLotDto getSaleLotById(String lotId) {
        Optional<SaleLot> saleLot = saleLotRepository.findById(UUID.fromString(lotId));
        if (saleLot.isPresent()) {
            return saleLotMapper.convertSaleLotToDto(saleLot.get());
        } else throw new NoSuchElementException();
    }

    public List<SaleLotDto> getSaleLotsByStockId(String stockId) {
        List<SaleLot> saleLots = saleLotRepository.findSaleLotsByStockId(UUID.fromString(stockId));
        return saleLotMapper.convertSaleLotListToDtoList(saleLots);
    }

    public List<SaleLotDto> getSaleLotsByUserId(String userId) {
        List<SaleLot> saleLots = saleLotRepository.findSaleLotsByUserId(UUID.fromString(userId));
        return saleLotMapper.convertSaleLotListToDtoList(saleLots);
    }

    public SaleLotDto updateNumberOfPrice(String lotId, Integer newNumberOfStocks) {
        Optional<SaleLot> saleLot = saleLotRepository.findById(UUID.fromString(lotId));
        if (saleLot.isPresent()) {
            SaleLot lot = saleLot.get();
            lot.setNumberOfStocks(Long.valueOf(newNumberOfStocks));
            saleLotRepository.save(lot);
            return saleLotMapper.convertSaleLotToDto(lot);
        } else throw new NoSuchElementException();
    }
}
