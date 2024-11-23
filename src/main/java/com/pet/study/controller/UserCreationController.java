package com.pet.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.study.dto.CustomApiResponse;
import com.pet.study.dto.user.UserCreatedDTO;
import com.pet.study.dto.user.UserDTO;
import com.pet.study.service.user.UserCreationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserCreationController {

    private final UserCreationService userCreationService;

    @Autowired
    public UserCreationController(UserCreationService userCreationService) {
        this.userCreationService = userCreationService;
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<UserCreatedDTO>> createUser(@RequestBody UserDTO userDTO) {
        UserCreatedDTO userCreatedDTO = userCreationService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(true, "User created successfully", userCreatedDTO));
    }
}
