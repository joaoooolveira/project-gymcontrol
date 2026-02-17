package com.joaooliveira.gymcontrol.repository;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.util.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberRepository {
    
    public void addMember(Member member){
        String sql = "insert into member (nameMember, cpfMember, status) values (?, ?, ?)";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, member.getNameMember());
            stmt.setString(2, member.getCpfMember());
            stmt.setString(3, member.getStatus().name());
            
            stmt.executeUpdate();
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Member> listMembers(){
        ArrayList<Member> members = new ArrayList<>();
        String sql = "select * from member";
        
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()){
                
                String statusBD = rs.getString("status");
                Member.StatusMember status = 
                        Member.StatusMember.valueOf(statusBD);
                
                Member member = new Member(
                        rs.getInt("id"),
                        rs.getString("nameMember"),
                        rs.getString("cpfMember"),
                        status
                );
                
                members.add(member);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return members;
    }
}
