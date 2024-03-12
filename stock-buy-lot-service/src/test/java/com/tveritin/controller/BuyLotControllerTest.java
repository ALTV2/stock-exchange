package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tveritin.domain.BuyLot;
import com.tveritin.model.dto.BuyLotDto;
import com.tveritin.repository.BuyLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:/application-test.yml")
public class BuyLotControllerTest {
    private static final String BASE_URL = "http://localhost:8080";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private static final UUID STOCK_UUID = UUID.randomUUID();
    private static final UUID USER_UUID = UUID.randomUUID();

    public static final int NUMBER_OF_STOCKS = 10;
    public static final int PRICE_FOR_ONE_STOCK = 10;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyLotRepository buyLotRepository;

    @BeforeEach
    public void cleanUpDb() {
        buyLotRepository.deleteAll();
    }

    @Test
    public void callCreateBuyLot() throws Exception {
        // given:
        String url = BASE_URL + "/buy/lot";

        BuyLotDto buyLotDto = new BuyLotDto();
        buyLotDto.setUserId(USER_UUID);
        buyLotDto.setStockId(STOCK_UUID);
        buyLotDto.setPriceForOneStock(PRICE_FOR_ONE_STOCK);
        buyLotDto.setNumberOfStocks(NUMBER_OF_STOCKS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(buyLotDto);

        // when:
        var result = mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        // then :
        UUID reternedUUID = UUID.fromString(result.getResponse().getContentAsString().substring(1, 37));
        BuyLot createdLot = buyLotRepository.findById(reternedUUID).orElseThrow();

        Assertions.assertEquals(buyLotDto.getUserId(), createdLot.getUserId());
        Assertions.assertEquals(buyLotDto.getStockId(), createdLot.getStockId());
        Assertions.assertEquals((long) buyLotDto.getNumberOfStocks(), createdLot.getNumberOfStocks());
        Assertions.assertEquals((long) buyLotDto.getPriceForOneStock(), createdLot.getPriceForOneStock());
    }

    @Test
    public void callDeleteBuyLotById() throws Exception {
        // given:
        String url = BASE_URL + "/buy/lot";

        BuyLot buyLot = new BuyLot();
        buyLot.setStockId(STOCK_UUID);
        buyLot.setUserId(USER_UUID);
        buyLot.setPriceForOneStock((long) PRICE_FOR_ONE_STOCK);
        buyLot.setNumberOfStocks((long) NUMBER_OF_STOCKS);
        buyLot.setCreationDateTime(LocalDateTime.now());
        buyLot.setTotalPrice((long) (NUMBER_OF_STOCKS * PRICE_FOR_ONE_STOCK));

        UUID createdUUID = buyLotRepository.save(buyLot).getId();

        // when:
        mockMvc.perform(delete(url + "/" + createdUUID.toString())
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(""))
                .andExpect(status().isOk())
                .andReturn();

        // then :
        Assertions.assertTrue(buyLotRepository.findById(createdUUID).isEmpty());
    }

}
