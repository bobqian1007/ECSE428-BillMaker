package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
