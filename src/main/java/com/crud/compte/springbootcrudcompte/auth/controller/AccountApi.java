package com.crud.compte.springbootcrudcompte.auth.controller;

import com.crud.compte.springbootcrudcompte.auth.model.dto.AddRoleToUserDTO;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountApi implements AccountController{
    private AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<AppUser> appUsers() {
        return accountService.listUsers();
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @Override
    public void addRoleToUser(AddRoleToUserDTO request) {
        accountService.addRoleToUser(request.getUserName(), request.getRoleName());
    }
}
