package com.tveritin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tveritin.clinet.BuyLotServiceClient;
import com.tveritin.clinet.SellLotServiceClient;
import com.tveritin.domain.Usser;
import com.tveritin.model.dto.UserDto;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:/application-test.yml")
public class UserControllerTest {
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String HAS_PASSWORD = "HAS_PASSWORD";
    private static final String BASE_URL = "http://localhost:8080";
    public static final String NAME = "USER_NAME";
    public static final String SURNAME = "SURNAME";

    private static final Gson GSON = new Gson();

    @MockBean
    BuyLotServiceClient buyLotServiceClient;
    @MockBean
    SellLotServiceClient sellLotServiceClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void cleanUpDb() {
        userRepository.deleteAll();
    }

    @Test
    public void createNewUser() throws Exception {
        // given:
        String url = BASE_URL + "/user";
        UserDto userDto = new UserDto()
                .username(USERNAME)
                .surName(SURNAME)
                .name(NAME)
                .email(EMAIL)
                .password(HAS_PASSWORD);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userDto);

        // when:
        var result = mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // then:
        UserDto createdUserDto = GSON.fromJson(
                result.getResponse().getContentAsString(),
                UserDto.class
        );
        Usser usser = userRepository.findById(createdUserDto.getId()).orElseThrow();
        Assertions.assertEquals(usser.getUsername(), USERNAME);
        Assertions.assertEquals(usser.getName(), NAME);
        Assertions.assertEquals(usser.getSurName(), SURNAME);
        Assertions.assertEquals(usser.getEmail(), EMAIL);
    }

    @Test
    public void getUser() throws Exception {
        // given:
        UserDto userDto = new UserDto()
                .username(USERNAME)
                .surName(SURNAME)
                .name(NAME)
                .email(EMAIL)
                .password(HAS_PASSWORD);
        UUID userId = userService.createUser(userDto).getId();

        String url = BASE_URL + "/user/" + userId.toString();

        // when:
        var result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        // then:
        UserDto createdUserDto = GSON.fromJson(
                result.getResponse().getContentAsString(),
                UserDto.class
        );
        Usser usser = userRepository.findById(createdUserDto.getId()).orElseThrow();
        Assertions.assertEquals(usser.getUsername(), USERNAME);
        Assertions.assertEquals(usser.getName(), NAME);
        Assertions.assertEquals(usser.getSurName(), SURNAME);
        Assertions.assertEquals(usser.getEmail(), EMAIL);
    }

    @Test
    public void getAllUsers() throws Exception {
        // given:
        String url = BASE_URL + "/user";

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

        userService.createUser(userDto_1);
        userService.createUser(userDto_2);

        // when:
        var result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        // then:
        Type listType = new TypeToken<ArrayList<UserDto>>() {
        }.getType();
        List<UserDto> createdUsersDto = GSON.fromJson(
                result.getResponse().getContentAsString(),
                listType
        );
        List<Usser> ussers = userRepository.findAll();
        Assertions.assertEquals(ussers.size(), 2);
        Assertions.assertEquals(createdUsersDto.size(), 2);
    }

}
