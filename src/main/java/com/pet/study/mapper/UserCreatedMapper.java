package com.pet.study.mapper;

import org.mapstruct.Mapper;

import com.pet.study.dto.user.UserCreatedDTO;
import com.pet.study.entity.User;

@Mapper(componentModel = "spring")
public interface UserCreatedMapper {

    UserCreatedDTO toUserCreatedDTO(User user);

}
