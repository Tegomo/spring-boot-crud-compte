package com.crud.compte.springbootcrudcompte;

import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.repository.AppRoleRepository;
import com.crud.compte.springbootcrudcompte.auth.repository.AppUserRepository;
import com.crud.compte.springbootcrudcompte.auth.service.AccountService;
import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import com.crud.compte.springbootcrudcompte.model.entities.Compte;
import com.crud.compte.springbootcrudcompte.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class SpringBootCrudCompteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudCompteApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(CompteRepository compteRepository, AccountService accountService){
        return args -> {
            compteRepository.save(new Compte(null, 20000,new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 25000,new Date(), TypeCompte.EPARGNE));

            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"CUSTUMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null,"BILLS_MANAGER"));

            accountService.addNewUser(new AppUser(null,"User1","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"Admin","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"User2","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"User4","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"User3","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"User5","1234",new ArrayList<>()));

            accountService.addRoleToUser("User1", "USER");
            accountService.addRoleToUser("User2", "USER");
            accountService.addRoleToUser("User3", "USER");
            accountService.addRoleToUser("User4", "USER");
            accountService.addRoleToUser("User5", "USER");
            accountService.addRoleToUser("Admin", "USER");
            accountService.addRoleToUser("User2", "CUSTUMER_MANAGER");
            accountService.addRoleToUser("User3", "PRODUCT_MANAGER");
            accountService.addRoleToUser("User4", "BILLS_MANAGER");
            accountService.addRoleToUser("Admin", "ADMIN");
        };
    }
}
