package com.ecse428.billmaker.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;

public interface SupervisionRequestRepository extends CrudRepository<SupervisionRequest, Integer> {
	void deleteById(Integer id);
}
