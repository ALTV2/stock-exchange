package com.tveritin.mapper;

import com.tveritin.domain.Usser;
import com.tveritin.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "portfolio", ignore = true)
    Usser convertUserDtoToUser(UserDto dto);


    @Mapping(target = "portfolioId", expression = "java(usser.getPortfolio() != null ? usser.getPortfolio().getId() : null)")
    @Mapping(target = "links", ignore = true)
    UserDto convertUserToUserDto(Usser usser);

    List<UserDto> convertUserListToUserDtoList(List<Usser> ussers);

}
