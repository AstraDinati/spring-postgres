package com.pet.study.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.study.dto.auth.RegisterRequest;
import com.pet.study.service.user.UserManagementService;

@Service
public class RegisterAuthService {
    private final UserManagementService userManagementService;

    @Autowired
    public RegisterAuthService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    public void registerUser(RegisterRequest registerRequest) {
        userManagementService.registerUser(registerRequest);
    }
}
