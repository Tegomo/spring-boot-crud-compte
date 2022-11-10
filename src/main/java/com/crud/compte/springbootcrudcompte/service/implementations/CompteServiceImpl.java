package com.crud.compte.springbootcrudcompte.service.implementations;


import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import com.crud.compte.springbootcrudcompte.repository.CompteRepository;
import com.crud.compte.springbootcrudcompte.service.interfaces.CompteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;

    @Autowired
    CompteServiceImpl(CompteRepository compteRepository){
        this.compteRepository = compteRepository;
    };

    @Override
    public List<Compte> compteList() {
        return compteRepository.findAll();
    }

    @Override
    public Optional<Compte> findCompte(Long id) {
        Optional<Compte> compte = compteRepository.findById(id);
        if (!compte.isPresent())
            return null;
        return compte;
    }

    @Override
    public Compte save(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void delete(Long code) {
        compteRepository.deleteById(code);
    }

    @Override
    public void virement(Long codeSource, Long codeDestination, double montant) {
        Compte c1 = compteRepository.getById(codeSource);
        
        Compte c2 = compteRepository.getById(codeDestination);
        if(c1.getSolde() > montant){
            c1.setSolde(c1.getSolde()-montant);
            c2.setSolde(c2.getSolde()+montant);
            compteRepository.save(c1);
            compteRepository.save(c2);
        }

    }

}
