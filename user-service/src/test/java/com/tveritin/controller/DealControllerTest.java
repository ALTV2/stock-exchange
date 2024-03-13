package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tveritin.clinet.BuyLotServiceClient;
import com.tveritin.clinet.SellLotServiceClient;
import com.tveritin.domain.Package;
import com.tveritin.domain.Usser;
import com.tveritin.model.dto.DealDto;
import com.tveritin.model.dto.UserDto;
import com.tveritin.repository.StockRepository;
import com.tveritin.repository.UserRepository;
import com.tveritin.service.UserService;
import org.junit.jupiter.api.Assertions;
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
public class DealControllerTest {
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String HAS_PASSWORD = "HAS_PASSWORD";
    public static final int NUMBER_OF_STOCKS = 20;
    private static final String BASE_URL = "http://localhost:8080";
    public static final String NAME = "USER_NAME";
    public static final String SURNAME = "SURNAME";

    private static final Integer START_BALANCE = 200;
    private static final Integer START_QUANTITY = 50;
    public static final int PRICE_FOR_ONE_STOCK = 5;

    private UUID userId_1;
    private UUID userId_2;
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

        UserDto userDto_2 = new UserDto()
                .username(USERNAME)
                .surName(SURNAME)
                .name(NAME)
                .email(EMAIL)
                .password(HAS_PASSWORD);

        userId_1 = userService.createUser(userDto_1).getId();
        userId_2 = userService.createUser(userDto_2).getId();
        stockId = stockRepository.findAll().getFirst().getId();
    }

    @Test
    public void makeDeal() throws Exception {
        // given:
        String url = BASE_URL + "/deal";

        DealDto dealDto = new DealDto()
                .stockId(stockId)
                .sellerId(userId_1)
                .buyerId(userId_2)
                .priceForOneStock(PRICE_FOR_ONE_STOCK)
                .numberOfStocks(NUMBER_OF_STOCKS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dealDto);

        // when:
        var result = mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // then:
        Usser usserSeller = userService.findUserById(userId_1);
        Usser usserBuyer = userService.findUserById(userId_2);

        Package sellerPackage = usserSeller.getPortfolio().getPackages().getFirst();
        Package buerPackage = usserBuyer.getPortfolio().getPackages().getFirst();

        Assertions.assertEquals(usserSeller.getBalance(), START_BALANCE + NUMBER_OF_STOCKS * PRICE_FOR_ONE_STOCK);
        Assertions.assertEquals(usserBuyer.getBalance(), START_BALANCE - NUMBER_OF_STOCKS * PRICE_FOR_ONE_STOCK);
        Assertions.assertEquals(sellerPackage.getQuantity(), START_QUANTITY - NUMBER_OF_STOCKS);
        Assertions.assertEquals(buerPackage.getQuantity(), START_QUANTITY + NUMBER_OF_STOCKS);
    }
}
