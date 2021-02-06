package com.ecse428.billmaker.service;

import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillMakerService {
    @Autowired
    private SupervisorUserRepository supervisorUserRepository;

    @Transactional
    public SupervisorUser createSupervisorUser(String name, String password, String email) {
        if (supervisorUserRepository.findByUsername(name) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        SupervisorUser supervisorUser = new SupervisorUser();
        supervisorUser.setUsername(name);
        supervisorUser.setPassword(password);
        supervisorUser.setEmail(email);
        supervisorUserRepository.save(supervisorUser);
        return supervisorUser;
    }

    @Transactional
    public SupervisorUser getSupervisorUser(String name) {
        return supervisorUserRepository.findByUsername(name);
    }
}
