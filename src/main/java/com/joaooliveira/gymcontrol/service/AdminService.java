package com.joaooliveira.gymcontrol.service;

import com.joaooliveira.gymcontrol.model.Admin;
import com.joaooliveira.gymcontrol.model.Admin.StatusAdmin;
import com.joaooliveira.gymcontrol.repository.AdminRepository;
import com.joaooliveira.gymcontrol.util.PasswordHasher;

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
        
        String hashedPassword = PasswordHasher.hash(password);
        Admin admin = new Admin(username, hashedPassword, status);
        
        repository.addAdmin(admin);
    }
    
    public Admin login(String username, String password){
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is invalid");
        }
        
        if(password == null || password.isBlank() || password.length() < 8){
            throw new IllegalArgumentException("Password is invalid");
        }
        
        Admin admin = repository.findByUsername(username);
        
        if(admin == null){
            throw new IllegalArgumentException("User not found");
        }
        
        if(admin.getStatus() == Admin.StatusAdmin.INACTIVE){
            throw new IllegalArgumentException("Inactive user");
        }
        
        if(!PasswordHasher.matches(password, admin.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        
        return admin;
    }
}
