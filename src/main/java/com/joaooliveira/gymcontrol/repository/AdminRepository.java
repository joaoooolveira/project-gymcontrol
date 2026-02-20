package com.joaooliveira.gymcontrol.repository;

import com.joaooliveira.gymcontrol.model.Admin;
import com.joaooliveira.gymcontrol.util.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {
    
    public void addAdmin(Admin admin){
        String sql = "insert into admin (usernameAdmin, password, status) values (?, ?, ?)";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getStatus().name());
            
            stmt.executeUpdate();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean activateAdmin(int id){
        String sql = "update admin set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Admin.StatusAdmin.ACTIVE.name());
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch(Exception e){
            throw new RuntimeException(e);
        }  
    }
    
    public boolean deactivateAdmin(int id){
        String sql = "update admin set status = ? where id = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, Admin.StatusAdmin.INACTIVE.name());
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Admin findByUsername(String username){
        String sql = "select * from admin where usernameAdmin = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                
            stmt.setString(1, username);
            
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return new Admin(
                        rs.getInt("id"),
                        rs.getString("usernameAdmin"),
                        rs.getString("password"),
                        Admin.StatusAdmin.valueOf(rs.getString("status"))
                    );
                }
                
                return null;
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean existsByUsername(String username){
        String sql = "select 1 from admin where usernameAdmin = ?";
        
        try(Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try(ResultSet rs = stmt.executeQuery()){
                return rs.next();
            }
            
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
