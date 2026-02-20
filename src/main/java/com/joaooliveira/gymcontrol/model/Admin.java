package com.joaooliveira.gymcontrol.model;

public class Admin {
    private int id;
    private String username;
    private String password;
    private StatusAdmin status;

    public Admin(String username, String password, StatusAdmin status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Admin(int id, String username, String password, StatusAdmin status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }
    
    public enum StatusAdmin {
        ACTIVE,
        INACTIVE
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusAdmin getStatus() {
        return status;
    }

    public void setStatus(StatusAdmin status) {
        this.status = status;
    }
}
