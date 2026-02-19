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
        
        Member member = new Member(
                    "test2",
                    "12345678920",
                    StatusMember.ACTIVE);
        
        repository.addMember(member);
        repository.activateMember(2);
        
        ArrayList<Member> members = repository.listMembersByStatus(
                        Member.StatusMember.ACTIVE);
        
        for (Member m : members){
            System.out.println(
                m.getNameMember() + " | CPF: " + m.getCpfMember());
        }
    }
}
