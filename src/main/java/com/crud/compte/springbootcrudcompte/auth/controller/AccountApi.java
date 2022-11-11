package com.crud.compte.springbootcrudcompte.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crud.compte.springbootcrudcompte.auth.config.JWTUtil;
import com.crud.compte.springbootcrudcompte.auth.model.dto.AddRoleToUserDTO;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken=request.getHeader(JWTUtil.AUTH_HEADER);
        if (authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
            try {
                String jwt=authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username=decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            }
            catch (Exception e){
               throw e;

            }
        }else {
            throw  new RuntimeException("Refresh token required!");
        }
    }

    @Override
    public AppUser profile(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }

}
