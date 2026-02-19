package com.joaooliveira.gymcontrol.service;

import com.joaooliveira.gymcontrol.repository.MemberRepository;

public class MemberService {
    private MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
    
    
    
}
