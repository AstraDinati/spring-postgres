package com.pet.study.datainitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    private final RoleLoader roleLoader;
    private final AdminUserLoader adminUserLoader;

    @Autowired
    public DataLoader(RoleLoader roleLoader, AdminUserLoader adminUserLoader) {
        this.roleLoader = roleLoader;
        this.adminUserLoader = adminUserLoader;
    }

    @PostConstruct
    public void init() {
        roleLoader.loadRoles();
        adminUserLoader.loadAdminUsers();
    }
}