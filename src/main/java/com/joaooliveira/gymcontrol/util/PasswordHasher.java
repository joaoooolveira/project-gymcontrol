package com.joaooliveira.gymcontrol.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private static final int workload = 12;
    
    private PasswordHasher(){
        
    }
    
    public static String hash(String originalPassword){
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(workload));
    }
    
    public static boolean matches(String originalPassword, String hashedPassword){
        return BCrypt.checkpw(originalPassword, hashedPassword);
    }
}
