package com.ecse428.billmaker.dao;

import java.util.List;

import com.ecse428.billmaker.model.myUser;
import org.springframework.dao.DataAccessException;


public interface UserDao {
    public List<myUser> selectMany() throws DataAccessException;
    public boolean deleteAccount(String username, String password);
}
