package com.joaooliveira.gymcontrol.test;

import com.joaooliveira.gymcontrol.repository.AdminRepository;
import com.joaooliveira.gymcontrol.service.AdminService;

public class AdminRepositoryTest {
    public static void main(String[] args){
        AdminRepository repository = new AdminRepository();
        AdminService service = new AdminService(repository);
        
        service.registerAdmin("test", "test1234");
    }
}
