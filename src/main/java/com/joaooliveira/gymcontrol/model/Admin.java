package com.joaooliveira.gymcontrol.model;

public class Admin {
    private Long id;
    private String login;
    private String password;
    private ProfileUser profile;
    
    public enum ProfileUser {
        Admin,
        Secretary
    }
}
