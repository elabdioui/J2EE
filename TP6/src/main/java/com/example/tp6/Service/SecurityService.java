package com.example.tp6.Service;

import com.example.tp6.Entitie.AppUser;
import com.example.tp6.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
