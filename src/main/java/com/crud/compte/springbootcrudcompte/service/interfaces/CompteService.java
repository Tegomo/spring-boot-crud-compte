package com.crud.compte.springbootcrudcompte.service.interfaces;


import com.crud.compte.springbootcrudcompte.model.entities.Compte;

import java.util.List;
import java.util.Optional;


public interface CompteService {
    List<Compte> compteList();

    Optional<Compte> findCompte(Long code);

    Compte save(Compte compte);

    void delete(Long code);
}
