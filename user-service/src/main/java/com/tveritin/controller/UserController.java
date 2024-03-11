package com.tveritin.controller;

import com.tveritin.model.dto.UserDto;
import com.tveritin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUser(String userId) {
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userDtoList);
    }

    @Override
    public ResponseEntity<UserDto> createNewUser(UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        return ResponseEntity.ok(createdUserDto);
    }
}
