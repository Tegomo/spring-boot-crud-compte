package com.crud.compte.springbootcrudcompte.repository;

import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
