package com.ecse428.billmaker.dao;
import org.springframework.data.repository.CrudRepository;
import com.ecse428.billmaker.model.*;

import java.util.Optional;


public interface ExpenseRepository extends CrudRepository<Expense, Integer> {


}

