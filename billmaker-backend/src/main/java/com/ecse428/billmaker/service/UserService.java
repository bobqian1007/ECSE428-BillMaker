package com.ecse428.billmaker.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.dao.SupervisionRequestRepository;
import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.dao.UserDao;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.myUser;
import com.ecse428.billmaker.model.myUser;
import java.security.InvalidParameterException;

@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;
    @Autowired
    private IndividualUserRepository idr;
    @Autowired
    private SupervisorUserRepository svr;
    @Autowired
    private SupervisionRequestRepository srr;
    @Transactional
    public List<myUser> selectMany() {
        return dao.selectMany();
    }
    @Transactional
    public boolean deleteAccount(String username, String password) throws DataAccessException {
        return dao.deleteAccount(username,password);
    }

    @Transactional
    public IndividualUser removeMonthLimit(String name) {
    	IndividualUser user = idr.findByUsername(name);
    	user.setMonthlyLimit(Double.POSITIVE_INFINITY);
    	idr.save(user);
    	return user;
    }
    @Transactional
    public IndividualUser SupervisorRemoveLimit(String supervisorName,String name) {
    	SupervisorUser suser = svr.findByUsername(supervisorName);
    	IndividualUser user = idr.findByUsername(name);
    	for (SupervisionRequest sr:suser.getSupervisionRequests()) {
    		if (sr.getIndividualUser() == user) {
    			return removeMonthLimit(name);
    		}
    	}
    	throw new InvalidParameterException("this is not your supervisee");
    }
    @Transactional
    public void createSupervisionRequest(String sn, String name) {
    	SupervisionRequest sr = new SupervisionRequest();
    	sr.setStatus(true);
    	sr.setSupervisorUser(svr.findByUsername(sn));
    	sr.setIndividualUser(idr.findByUsername(name));
    	SupervisorUser suser = svr.findByUsername(sn);
    	IndividualUser user = idr.findByUsername(name);
    	Set<SupervisionRequest> newRequests = new HashSet();
        newRequests.add(sr);
        suser.setSupervisionRequests(newRequests);
        user.setSupervisionRequests(newRequests);
    	
    	srr.save(sr);
    }
    	
    @Transactional
    public void editMonthLimit(String name, double limit) {
    	//if (limit != 0) {
    		IndividualUser user = idr.findByUsername(name);
    		user.setMonthlyLimit(limit);
    		idr.save(user); 
    }
    @Transactional
    public double getMonthLimit(String name) {
    	IndividualUser user = idr.findByUsername(name);
    	return user.getMonthlyLimit(); 	
    }

    @Transactional
    public IndividualUser changePwd(String username, String password) {
        IndividualUser user = idr.findByUsername(username);
        user.setPassword(password);
        return user;
    }

    @Transactional
    public IndividualUser deleteUser(String username, String password) {
        IndividualUser user = idr.findByUsername(username);
        user.setUsername("deleted");
        return user;
    }

}
