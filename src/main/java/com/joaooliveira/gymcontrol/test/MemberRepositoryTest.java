package com.joaooliveira.gymcontrol.test;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.model.Member.StatusMember;
import com.joaooliveira.gymcontrol.repository.MemberRepository;
import com.joaooliveira.gymcontrol.service.MemberService;
import java.util.ArrayList;

public class MemberRepositoryTest {

    public static void main(String[] args){
        MemberRepository repository = new MemberRepository();
        MemberService service = new MemberService(repository);
        
        Member member = new Member("test4", "12345678970", StatusMember.ACTIVE);
        
        //service.registerMember(member.getNameMember(), member.getCpfMember());
        service.updateMember(member, 7);
        
        service.listMembersByStatus(StatusMember.ACTIVE)
                .forEach(m ->
                    System.out.println(m.getNameMember() + " | CPF: " + m.getCpfMember() + " | Status: " + m.getStatus().name()));
    }
}
