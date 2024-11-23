package com.pet.study.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.pet.study.dto.user.UserDTO;
import com.pet.study.entity.Role;
import com.pet.study.entity.User;
import com.pet.study.repository.RoleRepository;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToStrings")
    public abstract UserDTO toDto(User user);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapStringsToRoles")
    public abstract User toEntity(UserDTO userDTO);

    @Named("mapRolesToStrings")
    public Set<String> mapRolesToStrings(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Named("mapStringsToRoles")
    public Set<Role> mapStringsToRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseGet(() -> {
                            Role newRole = new Role();
                            newRole.setName(name);
                            return newRole;
                        }))
                .collect(Collectors.toSet());
    }

}
