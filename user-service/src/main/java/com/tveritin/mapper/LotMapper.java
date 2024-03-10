package com.tveritin.mapper;

import com.tveritin.clinet.dto.BuyLotDto;
import com.tveritin.clinet.dto.SaleLotDto;
import com.tveritin.model.dto.LotDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = LocalDateTime.class)
public interface LotMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "creationDateTime", ignore = true)
    @Mapping(target = "saleForActualPrice", source = "useActualPrice")
    SaleLotDto convertLotDtoToSaleLotDto(LotDto lotDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "creationDateTime", ignore = true)
    @Mapping(target = "buyForActualPrice", source = "useActualPrice")
    BuyLotDto convertLotDtoToBuyLotDto(LotDto lotDto);
}
