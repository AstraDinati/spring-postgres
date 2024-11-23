package com.pet.study.service.helper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pet.study.constants.ErrorMessages;
import com.pet.study.entity.User;
import com.pet.study.entity.Role;
import com.pet.study.exception.user.UserNotFoundException;
import com.pet.study.repository.UserRepository;

@Service
public class UserHelper {
    private final UserRepository userRepository;

    @Autowired
    public UserHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Set<String> getRolesStr(User user) {
        return user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
