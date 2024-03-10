package com.tveritin.clinet;

import com.tveritin.clinet.dto.BuyLotDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "buy-lot-service", url = "http://localhost:8081")
public interface BuyLotServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/sell/lot"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<UUID> createBuyLot(@RequestBody @Valid BuyLotDto buyLotDto);

    @RequestMapping(
            method = {RequestMethod.DELETE},
            value = {"/sell/lot/{lotId}"}
    )
    ResponseEntity<Void> deleteBuyLotById(@PathVariable("lotId") String lotId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot"},
            produces = {"application/json"}
    )
    ResponseEntity<List<BuyLotDto>> getAllBuyLots();

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/{lotId}"},
            produces = {"application/json"}
    )
    ResponseEntity<BuyLotDto> getBuyLotById(@PathVariable("lotId") String lotId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/stock/{stockId}"},
            produces = {"application/json"}
    )
    ResponseEntity<List<BuyLotDto>> getBuyLotsByStockId(@PathVariable("stockId") String stockId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/user/{userId}"},
            produces = {"application/json"}
    )
    ResponseEntity<List<BuyLotDto>> getBuyLotsByUserId(@PathVariable("userId") String userId);
}
