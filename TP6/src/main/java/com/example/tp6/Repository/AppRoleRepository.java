package com.example.tp6.Repository;

import com.example.tp6.Entitie.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByRoleName(String roleName);
}
