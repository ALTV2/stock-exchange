package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.tveritin.clinet.BuyLotServiceClient;
import com.tveritin.clinet.SellLotServiceClient;
import com.tveritin.domain.Portfolio;
import com.tveritin.domain.Usser;
import com.tveritin.model.dto.PortfolioDto;
import com.tveritin.model.dto.UserDto;
import com.tveritin.repository.PortfolioRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:/application-test.yml")
public class PortfolioControllerTest {
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String HAS_PASSWORD = "HAS_PASSWORD";
    private static final String BASE_URL = "http://localhost:8080";
    public static final String NAME = "USER_NAME";
    public static final String SURNAME = "SURNAME";

    private static final Gson GSON = new Gson();

    private UUID portfolioId;

    @MockBean
    BuyLotServiceClient buyLotServiceClient;
    @MockBean
    SellLotServiceClient sellLotServiceClient;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;

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

        portfolioId = userService.createUser(userDto_1).getPortfolioId();
    }

    @Test
    public void getPortfolio() throws Exception {
        // given:
        String url = BASE_URL + "/portfolio/" + portfolioId;

        // when:
        var result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        // then:
//        PortfolioDto portfolioDto = GSON.fromJson(
//                result.getResponse().getContentAsString(),
//                PortfolioDto.class
//        );

        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow();

        Assertions.assertEquals(portfolio.getId(), portfolioId);
    }
}
