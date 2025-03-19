package com.example.Webhienmau.config;

import com.example.Webhienmau.model.Enum.RoleName;
import com.example.Webhienmau.model.Role;
import com.example.Webhienmau.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StartupService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initializeRoles() {
        Set<RoleName> roleNames = Set.of(RoleName.USER, RoleName.ADMIN);

        for (RoleName roleName : roleNames) {
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
