package com.tveritin.mapper;

import com.tveritin.domain.SaleLot;
import com.tveritin.model.dto.SaleLotDto;
import com.tveritin.service.kafka.LotKafkaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleLotMapper {

    @Mapping(target = "creationDateTime", expression = "java( localDateTimeToOffsetDateTime(saleLot.getCreationDateTime()) )")
    SaleLotDto convertSaleLotToDto(SaleLot saleLot);

    List<SaleLotDto> convertSaleLotListToDtoList(List<SaleLot> saleLots);

    @Mapping(target = "creationDateTime", expression = "java( offsetDateTimeToLocalDateTime(dto.getCreationDateTime()) )")
    SaleLot convertDtoToSaleLot(SaleLotDto dto);

    List<SaleLot> convertDtoListToSaleLotList(List<SaleLotDto> dtoList);

    @Mapping(target = "useActualPrice", source = "saleForActualPrice")
    @Mapping(target = "forSell", constant = "true")
    LotKafkaDto convertSaleLotToLotKafkaDto(SaleLot buyLot);

    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }
}
