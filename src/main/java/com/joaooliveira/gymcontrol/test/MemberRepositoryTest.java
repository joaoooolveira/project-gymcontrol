package com.joaooliveira.gymcontrol.test;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.model.Member.StatusMember;
import com.joaooliveira.gymcontrol.repository.MemberRepository;

public class MemberRepositoryTest {
    public static void main(String[] args){
        MemberRepository repository = new MemberRepository();
        
        Member member = new Member(
                    "Joao Pedro",
                    "12345678910",
                    StatusMember.ACTIVE);
        
        repository.addMember(member);
    }
}
