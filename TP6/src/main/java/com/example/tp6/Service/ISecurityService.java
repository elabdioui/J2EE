package com.example.tp6.Service;

import com.example.tp6.Entitie.AppUser;

public interface ISecurityService {
    AppUser loadUserByUserName(String username);
}
