package com.ecse428.billmaker.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecse428.billmaker.dao.UserDao;
import com.ecse428.billmaker.model.myUser;

@Transactional
@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    public List<myUser> selectMany() {
        return dao.selectMany();

    }

}
