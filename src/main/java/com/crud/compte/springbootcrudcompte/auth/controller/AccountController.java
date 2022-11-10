package com.crud.compte.springbootcrudcompte.auth.controller;

import com.crud.compte.springbootcrudcompte.auth.model.dto.AddRoleToUserDTO;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "api/v1/auth")
public interface AccountController {

    @GetMapping("/users")
    List<AppUser> appUsers();

    @PostMapping("/add/user")
    AppUser saveUser(@RequestBody AppUser appUser);

    @PostMapping("/add/role")
    AppRole saveRole(@RequestBody AppRole appRole);

    @PostMapping("/add/roletouser")
    void addRoleToUser(@RequestBody AddRoleToUserDTO request);


}
