package com.joaooliveira.gymcontrol.test;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.model.Member.StatusMember;
import com.joaooliveira.gymcontrol.repository.MemberRepository;
import java.util.ArrayList;

public class MemberRepositoryTest {
    public static void main(String[] args){
        MemberRepository repository = new MemberRepository();
        
        Member member = new Member(
                    "test1",
                    "12345678930",
                    StatusMember.ACTIVE);
        
        repository.addMember(member);
        repository.listMembers();
        
        ArrayList<Member> members = repository.listMembers();
        
        for (Member m : members){
            System.out.println(
                m.getNameMember() + " | CPF: " + m.getCpfMember());
        }
    }
}
