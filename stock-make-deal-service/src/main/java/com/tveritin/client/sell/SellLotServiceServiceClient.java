package com.tveritin.client.sell;

import com.tveritin.client.sell.dto.SaleLotDto;
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

@FeignClient(name = "sell-lot-service")
public interface SellLotServiceServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/sell/lot"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<UUID> createSaleLot(@RequestBody @Valid SaleLotDto saleLotDto);

    @RequestMapping(
            method = {RequestMethod.DELETE},
            value = {"/sell/lot/{lotId}"}
    )
    ResponseEntity<Void> deleteSaleLotById(@PathVariable("lotId") String lotId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot"},
            produces = {"application/json"}
    )
    ResponseEntity<List<SaleLotDto>> getAllSaleLots();

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/{lotId}"},
            produces = {"application/json"}
    )
    ResponseEntity<SaleLotDto> getSaleLotById(@PathVariable("lotId") String lotId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/stock/{stockId}"},
            produces = {"application/json"}
    )
    ResponseEntity<List<SaleLotDto>> getSaleLotsByStockId(@PathVariable("stockId") String stockId);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/sell/lot/user/{userId}"},
            produces = {"application/json"}
    )
    ResponseEntity<List<SaleLotDto>> getSaleLotsByUserId(@PathVariable("userId") String userId);

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/sell/lot/{lotId}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<SaleLotDto> updateNUmberOfStocksSaleLotById(@PathVariable("lotId") String lotId, @RequestBody @Valid Integer body);
}