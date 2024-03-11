package com.tveritin.service;

import com.tveritin.domain.Portfolio;
import com.tveritin.domain.Usser;
import com.tveritin.mapper.UserMapper;
import com.tveritin.model.dto.Link;
import com.tveritin.model.dto.UserDto;
import com.tveritin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PortfolioService portfolioService;
    private static final double STARTED_BALANCE = 200;

    public UserDto getUserById(String userId) {
        Optional<Usser> user = userRepository.findById(UUID.fromString(userId));
        if (user.isPresent()) {
            return addHATEOSLinks(userMapper.convertUserToUserDto(user.get()));
        } else throw new NoSuchElementException();
    }

    public Usser findUserById(UUID userId) {
        Optional<Usser> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else throw new NoSuchElementException();
    }

    public void save(Usser usser) {
        userRepository.save(usser);
    }

    public List<UserDto> getAllUsers() {
        return userMapper.convertUserListToUserDtoList(userRepository.findAll())
                .stream()
                .map(this::addHATEOSLinks)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        Usser usser = new Usser();

        usser.setUsername(userDto.getUsername());
        usser.setName(userDto.getName());
        usser.setSurName(userDto.getSurName());
        usser.setEmail(userDto.getEmail());
        usser.setBalance(STARTED_BALANCE);

        Usser createdUser = userRepository.save(usser);
        Portfolio portfolio = portfolioService.createPortfolio(createdUser);
        createdUser.setPortfolio(portfolio);
        userRepository.save(createdUser);

        return addHATEOSLinks(userMapper.convertUserToUserDto(createdUser));
    }

    private UserDto addHATEOSLinks (UserDto userDto) {
        Link potfolioLink = new Link();
        potfolioLink.setDescription("getPortfolio");
        potfolioLink.setHref("/portfolio/" + userDto.getPortfolioId());
        userDto.links(List.of(potfolioLink));
        return userDto;
    }
}
