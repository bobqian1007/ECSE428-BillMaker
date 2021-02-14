package com.ecse428.billmaker.dao;
import javax.persistence.EntityManager;

import com.ecse428.billmaker.model.LoginUser;
import com.ecse428.billmaker.model.myUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserDao {
    @Autowired
    EntityManager em;

    /**
     * Search for the user and return null if not found
     * @param email
     * @return UserEntity or Null
     */
    public LoginUser findUser(String email) {

        String query = "";
        query += "SELECT * ";
        query += "FROM bill_user ";
        query += "WHERE email = :email ";

        return (LoginUser)em.createNativeQuery(query, LoginUser.class).setParameter("email", email).getSingleResult();
    }
}
