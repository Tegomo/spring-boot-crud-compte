package com.crud.compte.springbootcrudcompte.service.implementations;


import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
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

            Optional<Compte> c1 = compteRepository.findById(codeSource);
            Optional<Compte> c2 = compteRepository.findById(codeDestination);
            if (c1.isPresent()){
                if(c1.get().getSolde() > montant){
                    c1.get().setSolde(c1.get().getSolde()-montant);
                    c2.get().setSolde(c2.get().getSolde()+montant);
                    compteRepository.save(c1.get());
                    compteRepository.save(c2.get());
                }
            }
    }

    @Override
    public List<Compte> findCompteByTypeCompteList(TypeCompte typeCompte) {
        return compteRepository.findByType(typeCompte);
    }

}
