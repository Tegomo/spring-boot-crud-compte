package com.crud.compte.springbootcrudcompte;

import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import com.crud.compte.springbootcrudcompte.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringBootCrudCompteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudCompteApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository){
        return args -> {
            compteRepository.save(new Compte(null, 20000,new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 25000,new Date(), TypeCompte.EPARGNE));
        };
    }
}
