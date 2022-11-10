package com.crud.compte.springbootcrudcompte.auth.service;

import com.crud.compte.springbootcrudcompte.auth.model.entities.AppRole;
import com.crud.compte.springbootcrudcompte.auth.model.entities.AppUser;
import com.crud.compte.springbootcrudcompte.auth.repository.AppRoleRepository;
import com.crud.compte.springbootcrudcompte.auth.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    private PasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = appUserRepository.findByUserName(username);
        AppRole appRole = appRoleRepository.findByRoleName(rolename);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUserName(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
