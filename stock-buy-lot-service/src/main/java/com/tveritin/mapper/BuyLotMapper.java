package com.tveritin.mapper;

import com.tveritin.domain.BuyLot;
import com.tveritin.model.dto.BuyLotDto;
import com.tveritin.service.kafka.LotKafkaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BuyLotMapper {

    @Mapping(target = "creationDateTime")
    BuyLotDto convertBuyLotToDto(BuyLot buyLot);

    List<BuyLotDto> convertBuyLotListToDtoList(List<BuyLot> buyLots);

    @Mapping(target = "creationDateTime")
    BuyLot convertDtoToBuyLot(BuyLotDto dto);

    List<BuyLot> convertDtoListToBuyLotList(List<BuyLotDto> dtoList);

    @Mapping(target = "useActualPrice", source = "buyForActualPrice")
    @Mapping(target = "forSell", constant = "false")
    LotKafkaDto convertBuyLotToLotKafkaDto(BuyLot buyLot);

    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }
}
