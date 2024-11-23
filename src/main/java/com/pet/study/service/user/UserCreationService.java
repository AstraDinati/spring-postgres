package com.pet.study.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.study.constants.ErrorMessages;
import com.pet.study.dto.user.UserCreatedDTO;
import com.pet.study.dto.user.UserDTO;
import com.pet.study.entity.User;
import com.pet.study.exception.email.EmailAlreadyTakenException;
import com.pet.study.mapper.UserCreatedMapper;
import com.pet.study.mapper.UserMapper;
import com.pet.study.repository.UserRepository;
import com.pet.study.service.helper.UserHelper;

@Service
public class UserCreationService {

    private final UserHelper userHelper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreatedMapper userCreatedMapper;

    @Autowired
    public UserCreationService(UserHelper userHelper,
            UserRepository userRepository,
            UserMapper userMapper,
            UserCreatedMapper userCreatedMapper) {
        this.userHelper = userHelper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userCreatedMapper = userCreatedMapper;

    }

    public UserCreatedDTO createUser(UserDTO userDTO) {
        if (userHelper.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyTakenException(ErrorMessages.EMAIL_ALREADY_TAKEN);
        }

        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userCreatedMapper.toUserCreatedDTO(savedUser);
    }
}
