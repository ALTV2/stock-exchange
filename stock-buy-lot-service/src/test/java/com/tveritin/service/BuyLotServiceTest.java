package com.tveritin.service;

import com.tveritin.domain.BuyLot;
import com.tveritin.mapper.BuyLotMapper;
import com.tveritin.model.dto.BuyLotDto;
import com.tveritin.repository.BuyLotRepository;
import com.tveritin.service.kafka.KafkaProducerService;
import com.tveritin.service.kafka.LotKafkaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BuyLotServiceTest {
    public static final String STRING_ID = UUID.randomUUID().toString();
    public static final UUID UUID_ID = UUID.fromString(STRING_ID);
    public static final int NUMBER_OF_STOCKS = 10;
    public static final int PRICE_FOR_ONE_STOCK = 10;

    @Mock
    private BuyLotRepository buyLotRepository;
    @Mock
    private BuyLotMapper buyLotMapper;
    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private BuyLotService buyLotService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBuyLot() {
        // given:
        BuyLotDto buyLotDto = new BuyLotDto();
        buyLotDto.setId(UUID_ID);
        buyLotDto.setStockId(UUID_ID);
        buyLotDto.setPriceForOneStock(PRICE_FOR_ONE_STOCK);
        buyLotDto.setNumberOfStocks(NUMBER_OF_STOCKS);

        BuyLot buyLot = new BuyLot();
        buyLot.setId(UUID_ID);

        LotKafkaDto lotKafkaDto = new LotKafkaDto();

        when(buyLotMapper.convertDtoToBuyLot(buyLotDto)).thenReturn(buyLot);
        when(buyLotRepository.save(Mockito.any(BuyLot.class))).thenReturn(buyLot);
        when(buyLotMapper.convertBuyLotToLotKafkaDto(buyLot)).thenReturn(lotKafkaDto);


        // when:
        UUID result = buyLotService.createBuyLot(buyLotDto);

        // then:
        assertNotNull(result);
        assertEquals(result, buyLot.getId());
        verify(buyLotRepository, times(1)).save(any(BuyLot.class));
        verify(kafkaProducerService, times(1)).sendMessage(eq("fresh_open_lots"), any(LotKafkaDto.class));
    }

    @Test
    public void testDeleteBuyLot() {
        // given:
        String lotId = STRING_ID;

        // when:
        buyLotService.deleteBuyLot(lotId);

        // then:
        verify(buyLotRepository, times(1)).deleteById(eq(UUID.fromString(lotId)));
    }


    @Test
    public void testGetAllBuyLots() {
        // given:
        List<BuyLot> buyLots = new ArrayList<>();
        buyLots.add(new BuyLot());
        buyLots.add(new BuyLot());

        List<BuyLotDto> buyLotDtoList = new ArrayList<>();
        buyLotDtoList.add(new BuyLotDto());
        buyLotDtoList.add(new BuyLotDto());

        when(buyLotRepository.findAll()).thenReturn(buyLots);
        when(buyLotMapper.convertBuyLotListToDtoList(anyList())).thenReturn(buyLotDtoList);

        // when:
        List<BuyLotDto> result = buyLotService.getAllBuyLots();

        //then:
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(buyLotRepository, times(1)).findAll();
    }

}
