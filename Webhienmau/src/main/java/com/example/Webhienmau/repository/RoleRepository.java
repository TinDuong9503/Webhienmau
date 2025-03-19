package com.example.Webhienmau.repository;

import com.example.Webhienmau.model.Enum.RoleName;
import com.example.Webhienmau.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository <Role, Long> {

    Role findByName(RoleName roleName);
    boolean existsByName(RoleName roleName);
}
