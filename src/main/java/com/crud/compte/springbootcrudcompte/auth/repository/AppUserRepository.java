package com.crud.compte.springbootcrudcompte.auth.repository;

import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String username);
}
