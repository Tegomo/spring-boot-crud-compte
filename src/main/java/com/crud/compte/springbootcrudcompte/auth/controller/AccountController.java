package com.crud.compte.springbootcrudcompte.auth.controller;

import com.crud.compte.springbootcrudcompte.auth.model.dto.AddRoleToUserDTO;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.service.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RequestMapping( "api/v1/auth")
public interface AccountController {

    @GetMapping("/users")
    @PostAuthorize("hasAuthority('USER')")
    List<AppUser> appUsers();

    @PostMapping("/add/user")
    @PostAuthorize("hasAuthority('ADMIN')")
    AppUser saveUser(@RequestBody AppUser appUser);

    @PostMapping("/add/role")
    @PostAuthorize("hasAuthority('ADMIN')")
    AppRole saveRole(@RequestBody AppRole appRole);

    @PostMapping("/add/roletouser")
    @PostAuthorize("hasAuthority('ADMIN')")
    void addRoleToUser(@RequestBody AddRoleToUserDTO request);


    @GetMapping("/refreshToken")
    @PostAuthorize("hasAuthority('USER')")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception;

    @GetMapping("/profile")
    AppUser profile(@RequestBody Principal principal);
}
