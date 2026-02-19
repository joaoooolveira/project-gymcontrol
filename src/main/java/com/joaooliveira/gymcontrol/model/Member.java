package com.joaooliveira.gymcontrol.model;

public class Member {
    private int id;
    private String nameMember;
    private String cpfMember;
    private StatusMember status;

    public Member(String nameMember, String cpfMember, StatusMember status){
        validatingData(nameMember, cpfMember, status);
        this.nameMember = nameMember;
        this.cpfMember = cpfMember;
        this.status = status;
    }

    public Member(int id, String nameMember, String cpfMember, StatusMember status){
         this.id = id;
         this.nameMember = nameMember;
         this.cpfMember = cpfMember;
         this.status = status;
     }
    
    public enum StatusMember {
        ACTIVE,
        EXPIRED,
        BLOCKED,
        INACTIVE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    public String getCpfMember() {
        return cpfMember;
    }

    public void setCpfMember(String cpfMember) {
        this.cpfMember = cpfMember;
    }

    public StatusMember getStatus() {
        return status;
    }

    public void setStatus(StatusMember status) {
        this.status = status;
    }
    
    private void validatingData(String name, String cpf, StatusMember status){
        if(name.isBlank()){
            throw new IllegalArgumentException("Invalid name");
        } if(cpf.isBlank()){
            throw new IllegalArgumentException("Invalid CPF");
        } if(status == null){
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
