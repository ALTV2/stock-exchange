package com.tveritin.client.user;

import com.tveritin.client.user.dto.DealDto;
import com.tveritin.client.user.dto.PortfolioDto;
import com.tveritin.client.user.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service-client", url = "http://localhost:8080")
public interface UserServiceClient {
    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/portfolio/{portfolioId}"},
            produces = {"application/json"}
    )
    ResponseEntity<PortfolioDto> getPortfolio(@PathVariable("portfolioId") String portfolioId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/user/{userId}"},
            produces = {"application/json"}
    )
    ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId);

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/deal"},
            consumes = {"application/json"}
    )
    ResponseEntity<Void> makeDeal(@RequestBody @Valid DealDto dealDto);
}


