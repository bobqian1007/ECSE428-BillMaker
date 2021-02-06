package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.SupervisorUser;
import org.springframework.data.repository.CrudRepository;

public interface SupervisorUserRepository extends CrudRepository<SupervisorUser, String> {
    SupervisorUser findByUsername(String username);
    void deleteByUsername(String username);
}
