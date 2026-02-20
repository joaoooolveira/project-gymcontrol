package com.joaooliveira.gymcontrol.service;

import com.joaooliveira.gymcontrol.repository.AdminRepository;

public class AdminService {
    private AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }
    
    public void registerAdmin(String username, String password){
        if(username == null || username.isBlank())
    }
}
