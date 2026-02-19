package com.joaooliveira.gymcontrol.repository;

import com.joaooliveira.gymcontrol.model.Member;
import com.joaooliveira.gymcontrol.model.Member.StatusMember;
import com.joaooliveira.gymcontrol.util.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberRepository {
    
    public void addMember(Member member){
        String sql = "insert into member (nameMember, cpfMember, status) values (?, ?, ?)";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, member.getNameMember());
            stmt.setString(2, member.getCpfMember());
            stmt.setString(3, member.getStatus().name());
            
            stmt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean updateMember(Member member, int id){
        String sql = "update member set nameMember = ?, cpfMember = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, member.getNameMember());
            stmt.setString(2, member.getCpfMember());
            stmt.setInt(3, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean deactivateMember(int id){
        String sql = "update member set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Member.StatusMember.INACTIVE.name());
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean activateMember(int id){
        String sql = "update member set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Member.StatusMember.ACTIVE.name());
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch(Exception e){
            throw new RuntimeException(e);
        }        
    }
    
    public ArrayList<Member> listAllMembers(){
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
    
    public ArrayList<Member> listMembersByStatus(StatusMember status){
        ArrayList<Member> members = new ArrayList<>();
        String sql = "select * from member where status = ?";
        
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                
                StatusMember statusBD = 
                        StatusMember.valueOf(rs.getString("status"));
        
                
                Member member = new Member(
                        rs.getInt("id"),
                        rs.getString("nameMember"),
                        rs.getString("cpfMember"),
                        statusBD
                );
                members.add(member);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return members;
    }
    
    public boolean existsByCpf(String cpf){
        String sql = "select 1 from member where cpfMember = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try(ResultSet rs = stmt.executeQuery()){
                return rs.next();
            }
            
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean existsByCpfAndNotId(String cpf, int id){
        String sql = "select 1 from member where cpfMember = ? and id <> ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, cpf);
            stmt.setInt(2, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                return rs.next();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

