package com.crud.compte.springbootcrudcompte.controller.interfaces;

import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import com.crud.compte.springbootcrudcompte.model.dto.VirementRequestDTO;
import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping( "api/v1/compte")
public interface CompteController {
    @GetMapping("/all")
    List<Compte> compteList();

    @GetMapping("/{type}")
    List<Compte> compteByTypeCompteList(@PathVariable TypeCompte type);

    @GetMapping("/{code}")
    Optional<Compte> findCompte(@PathVariable Long code);

    @PostMapping("/new")
    Compte save(@RequestBody Compte compte);

    @PutMapping("/{code}")
    Compte update(@PathVariable Long code, @RequestBody Compte compte);

    @DeleteMapping("/{code}")
    void delete(@PathVariable Long code);

    @PutMapping("/virement")
    void virement(@RequestBody VirementRequestDTO request);

}
