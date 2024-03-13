package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tveritin.clinet.BuyLotServiceClient;
import com.tveritin.clinet.SellLotServiceClient;
import com.tveritin.model.dto.LotDto;
import com.tveritin.model.dto.UserDto;
import com.tveritin.repository.StockRepository;
import com.tveritin.repository.UserRepository;
import com.tveritin.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:/application-test.yml")
public class LotControllerTest {
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String HAS_PASSWORD = "HAS_PASSWORD";
    public static final int NUMBER_OF_STOCKS = 20;
    private static final String BASE_URL = "http://localhost:8080";
    public static final String NAME = "USER_NAME";
    public static final String SURNAME = "SURNAME";

    public static final int PRICE_FOR_ONE_STOCK = 5;

    private UUID userId_1;
    private UUID stockId;
    @MockBean
    BuyLotServiceClient buyLotServiceClient;
    @MockBean
    SellLotServiceClient sellLotServiceClient;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void cleanUpDb() {
        userRepository.deleteAll();

        UserDto userDto_1 = new UserDto()
                .username(USERNAME)
                .surName(SURNAME)
                .name(NAME)
                .email(EMAIL)
                .password(HAS_PASSWORD);

        userId_1 = userService.createUser(userDto_1).getId();
        stockId = stockRepository.findAll().getFirst().getId();
    }

    @Test
    public void createBuyLot() throws Exception {
        // given:
        String url = BASE_URL + "/lot/buy";

        LotDto lotDto = new LotDto()
                .stockId(stockId)
                .userId(userId_1)
                .useActualPrice(false)
                .priceForOneStock(PRICE_FOR_ONE_STOCK)
                .numberOfStocks(NUMBER_OF_STOCKS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lotDto);

        // when:
        mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createSellLot() throws Exception {
        // given:
        String url = BASE_URL + "/lot/sell";

        LotDto lotDto = new LotDto()
                .stockId(stockId)
                .userId(userId_1)
                .useActualPrice(false)
                .priceForOneStock(PRICE_FOR_ONE_STOCK)
                .numberOfStocks(NUMBER_OF_STOCKS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lotDto);

        // when:
        mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
    }
}
