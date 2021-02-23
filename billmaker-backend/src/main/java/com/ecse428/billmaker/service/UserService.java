package com.ecse428.billmaker.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.dao.UserDao;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.myUser;
import com.ecse428.billmaker.model.myUser;


@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;
    @Autowired
    private IndividualUserRepository idr;
    @Transactional
    public List<myUser> selectMany() {
        return dao.selectMany();

    }
    @Transactional
    public IndividualUser removeMonthLimit(String name) {
    	IndividualUser user = idr.findByUsername(name);
    	user.setMonthlyLimit(Double.POSITIVE_INFINITY);
    	idr.save(user);
    	return user;
    }
    @Transactional
    public void editMonthLimit(String name, double limit) {
    	//if (limit != 0) {
    		IndividualUser user = idr.findByUsername(name);
    		user.setMonthlyLimit(limit);
    		idr.save(user); 
    	//}else {
    	//	removeMonthLimit(name);
    	//} 	
    }
    @Transactional
    public double getMonthLimit(String name) {
    	IndividualUser user = idr.findByUsername(name);
    	return user.getMonthlyLimit(); 	
    }
    

}
