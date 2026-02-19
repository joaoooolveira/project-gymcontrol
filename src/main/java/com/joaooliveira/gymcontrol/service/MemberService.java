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
        if(id <= 0){
            throw new IllegalArgumentException("Invalid member ID");
        }
        
        repository.activateMember(id);
    }
    
    public void deactivateMember(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Invalid member ID");
        }
        
        repository.deactivateMember(id);
    }
}
