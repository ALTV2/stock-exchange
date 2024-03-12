package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.tveritin.domain.BuyLot;
import com.tveritin.model.dto.ApproveDellOfferDto;
import com.tveritin.model.dto.DealOfferDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:/application-test.yml")
public class CheckDealControllerTest {
    private static final String BASE_URL = "http://localhost:8080";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private static final UUID STOCK_UUID = UUID.randomUUID();
    private static final UUID USER_UUID = UUID.randomUUID();

    public static final int NUMBER_OF_STOCKS = 10;
    public static final int PRICE_FOR_ONE_STOCK = 10;
    public static final Gson GSON = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyLotRepository buyLotRepository;

    @BeforeEach
    public void cleanUpDb() {
        buyLotRepository.deleteAll();
    }

    @Test
    public void callCheckDeal() throws Exception {
        // given:
        String url = BASE_URL + "/buy/check";

        BuyLot buyLot_1 = new BuyLot();
        BuyLot buyLot_2 = new BuyLot();
        BuyLot buyLot_3 = new BuyLot();
        BuyLot buyLot_4 = new BuyLot();

        fillLot(buyLot_1);
        fillLot(buyLot_2);
        fillLot(buyLot_3);
        fillLot(buyLot_4);

        UUID createdUUID_1 = buyLotRepository.save(buyLot_1).getId();
        UUID createdUUID_2 = buyLotRepository.save(buyLot_2).getId();
        UUID createdUUID_3 = buyLotRepository.save(buyLot_3).getId();
        UUID createdUUID_4 = buyLotRepository.save(buyLot_4).getId();

        DealOfferDto dealOfferDto = new DealOfferDto();
        dealOfferDto.setStockId(STOCK_UUID);
        dealOfferDto.setNumberOfStocks(NUMBER_OF_STOCKS * 3);
        dealOfferDto.setMinPrise(PRICE_FOR_ONE_STOCK - 1);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dealOfferDto);

        // when:
        var result = mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // then :
        ApproveDellOfferDto approveDellOfferDto = GSON.fromJson(
                result.getResponse().getContentAsString(),
                ApproveDellOfferDto.class
        );

        Assertions.assertTrue(approveDellOfferDto.getApproved());
        Assertions.assertEquals(approveDellOfferDto.getRemaining(), 0);
        Assertions.assertEquals(approveDellOfferDto.getLots().size(), 3);
        Assertions.assertEquals(buyLotRepository.findAll().size(), 1);
    }

    private static void fillLot(BuyLot buyLot) {
        buyLot.setStockId(STOCK_UUID);
        buyLot.setUserId(USER_UUID);
        buyLot.setPriceForOneStock((long) PRICE_FOR_ONE_STOCK);
        buyLot.setNumberOfStocks((long) NUMBER_OF_STOCKS);
        buyLot.setCreationDateTime(LocalDateTime.now());
        buyLot.setTotalPrice((long) (NUMBER_OF_STOCKS * PRICE_FOR_ONE_STOCK));
    }
}
