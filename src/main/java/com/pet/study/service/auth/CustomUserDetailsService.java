package com.pet.study.service.auth;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pet.study.entity.User;
import com.pet.study.helper.AuthHelper;
import com.pet.study.security.CustomUserDetails;
import com.pet.study.service.helper.UserHelper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserHelper userHelper;

    @Autowired
    public CustomUserDetailsService(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userHelper.findUserByEmail(email);

        Set<String> roles = userHelper.getRolesStr(user);

        Collection<GrantedAuthority> authorities = AuthHelper.convertRolesToAuthorities(roles);

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                authorities,
                roles);
    }
}
