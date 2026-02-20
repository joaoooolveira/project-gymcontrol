package com.joaooliveira.gymcontrol.service;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.model.Member.StatusMember;
import com.joaooliveira.gymcontrol.repository.MemberRepository;
import java.util.List;

public class MemberService {
    private MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
    
    public void registerMember(String name, String cpf) {
        cpf = cpf.trim();
        
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name is invalid");
        }
        
        if(cpf == null || cpf.isBlank() || cpf.length() != 11){
            throw new IllegalArgumentException("CPF is invalid");
        }
        
        if(repository.existsByCpf(cpf)){
            throw new IllegalArgumentException("CPF already registered");
        }
        
        StatusMember status = StatusMember.ACTIVE;
        
        Member member = new Member(name, cpf, status);
        
        repository.addMember(member);
    }
    
    public void updateMember(Member member, int id) {
        if(member.getNameMember() == null || member.getNameMember().isBlank()){
            throw new IllegalArgumentException("Name is invalid");
        }
        
        if(member.getCpfMember() == null || member.getCpfMember().isBlank() || member.getCpfMember().length() != 11){
            throw new IllegalArgumentException("CPF is invalid");
        }
        
        if(repository.existsByCpfAndNotId(member.getCpfMember(), id)){
            throw new IllegalArgumentException("CPF already registered");
        }
        
        boolean updated = repository.updateMember(member, id);
        
        if(!updated){
            throw new IllegalArgumentException("Member not found");
        }
    }
    
    public List<Member> listAllMembers(){
        return repository.listAllMembers();
    }
    
    public List<Member> listMembersByStatus(StatusMember status){
        if(status == null){
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        return repository.listMembersByStatus(status);
    }
    
    public void activateMember(int id){
        boolean success = repository.activateMember(id);
        
        if(!success){
            throw new IllegalArgumentException("Member not found");
        }
    }
    
    public void deactivateMember(int id){
        boolean success = repository.deactivateMember(id);
        
        if(!success){
            throw new IllegalArgumentException("Member not found");
        }
    }
}
