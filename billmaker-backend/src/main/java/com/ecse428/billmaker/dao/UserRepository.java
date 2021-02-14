package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Integer> {

}
