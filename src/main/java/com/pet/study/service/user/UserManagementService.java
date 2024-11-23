package com.pet.study.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pet.study.dto.auth.RegisterRequest;
import com.pet.study.entity.User;
import com.pet.study.exception.email.EmailAlreadyTakenException;
import com.pet.study.service.helper.UserHelper;

@Service
public class UserManagementService {

    private final PasswordEncoder passwordEncoder;
    private final UserHelper userHelper;

    @Autowired
    public UserManagementService(PasswordEncoder passwordEncoder, UserHelper userHelper) {
        this.passwordEncoder = passwordEncoder;
        this.userHelper = userHelper;
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userHelper.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyTakenException("Email is already taken!");
        }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userHelper.saveUser(user);
    }
}