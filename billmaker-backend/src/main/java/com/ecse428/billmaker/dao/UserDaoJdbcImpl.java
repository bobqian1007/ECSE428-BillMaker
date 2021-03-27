package com.ecse428.billmaker.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ecse428.billmaker.model.myUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<myUser> selectMany() throws DataAccessException {

        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM bill_user");
        List<myUser> userList = new ArrayList<>();

        for (Map<String, Object> map : getList) {

            myUser user = new myUser();
            user.setUsername((String) map.get("username"));
            user.setEmail((String) map.get("email"));
            user.setPassword((String) map.get("password"));

            userList.add(user);
        }
        // ListをServiceに返す。
        return userList;
    }

    @Override
    public boolean deleteAccount(String username, String password) throws DataAccessException {
        jdbc.queryForList("DELETE FROM bill_user WHERE username='"+username+"'");
        return true;
    }


}
