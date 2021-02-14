package com.ecse428.billmaker.service;
import java.util.ArrayList;
import java.util.List;

import com.ecse428.billmaker.model.LoginUser;
import com.ecse428.billmaker.dao.LoginUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    //DBからユーザ情報を検索するメソッドを実装したクラス
    @Autowired
    private LoginUserDao loginUserDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        LoginUser user = loginUserDao.findUser(userName);

        if (user == null) {
            System.out.println("not found!!");
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserDetails userDetails = (UserDetails)new User(user.getEmail(), user.getPassword(),grantList);

        //Debug
        System.out.println(
            "Login Attempt: " + userDetails.getUsername() + ", " + userDetails.getPassword() + ", "
                + userDetails.getAuthorities());

        return userDetails;
    }

}
