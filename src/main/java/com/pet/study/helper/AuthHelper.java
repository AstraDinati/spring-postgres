package com.pet.study.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthHelper {

    private AuthHelper() {
        // Приватный конструктор для предотвращения инстанцирования
    }

    /**
     * Конвертирует Set<String> ролей в Collection<GrantedAuthority>.
     *
     * @param roles Роли пользователя
     * @return Коллекция GrantedAuthority
     */
    public static Collection<GrantedAuthority> convertRolesToAuthorities(Set<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
