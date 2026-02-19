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
        
        if(existsByCpf(member.getCpfMember())){
            System.out.println("Esse CPF já existe no sistema");
            return;
        }
        
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
    
    public void deactivateMember(int id){
        String sql = "update member set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Member.StatusMember.INACTIVE.name());
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            
            if(rowsAffected > 0){
                System.out.println("Aluno deletado com sucesso.");
            } else {
                System.out.println("Nenhum aluno encontrado com esse id.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void activateMember(int id){
        String sql = "update member set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Member.StatusMember.ACTIVE.name());
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }        
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
                        status
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
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

