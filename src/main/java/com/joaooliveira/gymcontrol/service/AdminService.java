package com.joaooliveira.gymcontrol.service;

import com.joaooliveira.gymcontrol.model.Admin;
import com.joaooliveira.gymcontrol.model.Admin.StatusAdmin;
import com.joaooliveira.gymcontrol.repository.AdminRepository;

public class AdminService {
    private AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }
    
    public void registerAdmin(String username, String password){
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is invalid");
        }
        
        if(repository.existsByUsername(username)){
            throw new IllegalArgumentException("Username already exists");
        }
        
        if(password == null || password.isBlank() || password.length() < 8){
            throw new IllegalArgumentException("Password is invalid");
        }
        
        StatusAdmin status = StatusAdmin.ACTIVE;
        
        Admin admin = new Admin(username, password, status);
        
        repository.addAdmin(admin);
    }
}
