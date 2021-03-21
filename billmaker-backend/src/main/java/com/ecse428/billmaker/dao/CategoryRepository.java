package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Category findCategoryByName(String name);
}
