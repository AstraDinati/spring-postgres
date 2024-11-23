package com.pet.study.datainitializer;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pet.study.entity.Role;
import com.pet.study.entity.User;
import com.pet.study.repository.RoleRepository;
import com.pet.study.repository.UserRepository;

@Component
public class AdminUserLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void loadAdminUsers() {
        if (!userRepository.existsById(1L)) {
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("First");
            adminUser.setEmail("bigulipa@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("123"));

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new IllegalStateException("Role 'ADMIN' not found"));

            adminUser.setRoles(Set.of(adminRole));

            userRepository.save(adminUser);
        }
    }
}