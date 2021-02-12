package com.ecse428.billmaker.service;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.IndividualUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillMakerService {
    @Autowired
    private SupervisorUserRepository supervisorUserRepository;

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Transactional
    public IndividualUser createIndividualUser(String username, String password, String email){
        if (individualUserRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        IndividualUser individualUser = new IndividualUser();
        individualUser.setUsername(username);
        individualUser.setPassword(password);
        individualUser.setEmail(email);
        individualUserRepository.save(individualUser);
        return individualUser;
    }

    @Transactional
    public IndividualUser getIndividualUser(String username) {
        return individualUserRepository.findByUsername(username);
    }

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
