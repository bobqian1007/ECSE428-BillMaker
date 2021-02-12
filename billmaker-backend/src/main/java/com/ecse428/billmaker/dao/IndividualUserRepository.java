package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.IndividualUser;
import org.springframework.data.repository.CrudRepository;

public interface IndividualUserRepository extends CrudRepository<IndividualUser, String> {
        IndividualUser findByUserName(String username);
}
