package com.crud.compte.springbootcrudcompte.auth.repository;

import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String rolename);
}
