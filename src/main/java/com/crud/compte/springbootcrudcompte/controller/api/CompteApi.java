package com.crud.compte.springbootcrudcompte.controller.api;


import com.crud.compte.springbootcrudcompte.controller.interfaces.CompteController;
import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import com.crud.compte.springbootcrudcompte.model.dto.VirementRequestDTO;
import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import com.crud.compte.springbootcrudcompte.service.interfaces.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CompteApi implements CompteController {

    private CompteService compteService;

    @Autowired
    public CompteApi(CompteService compteService) {
        this.compteService = compteService;
    }

    @Override
    public List<Compte> compteList() {
        return compteService.compteList();
    }

    @Override
    public List<Compte> compteByTypeCompteList(TypeCompte type) {
        return compteService.findCompteByTypeCompteList(type);
    }

    @Override
    public Optional<Compte> findCompte(Long code) {
        return compteService.findCompte(code);
    }

    @Override
    public Compte save(Compte compte) {
        return compteService.save(compte);
    }

    @Override
    public Compte update(Long code, Compte compte) {
        compte.setCode(code);
        return compteService.save(compte);
    }

    @Override
    public void delete(Long code) {
        compteService.delete(code);
    }

    @Override
    public void virement(VirementRequestDTO request) {
         compteService.virement(request.getCodeSource(), request.getCodeDestination(), request.getMontant());
    }


}
