package com.joaooliveira.gymcontrol.model;

public class Member {
    private Long id;
    private String nameMember;
    private String cpfMember;
    private StatusMember status;
    
     public Member(Long id, String nameMember, String cpfMember, StatusMember status){
         this.id = id;
         this.nameMember = nameMember;
         this.cpfMember = cpfMember;
         this.status = status;
     }
    
    public enum StatusMember {
        Active,
        Expired,
        Blocked
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
    
    
}
