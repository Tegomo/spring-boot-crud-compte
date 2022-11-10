package com.crud.compte.springbootcrudcompte.repository;

import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, Long> {

}
