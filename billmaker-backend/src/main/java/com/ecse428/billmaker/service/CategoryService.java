package com.ecse428.billmaker.service;


import com.ecse428.billmaker.dao.CategoryRepository;
import com.ecse428.billmaker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category editCategory(String oldName, String name) {
        if (oldName == name) {
            return categoryRepository.findCategoryByName(name);
        }

        if (categoryRepository.findCategoryByName(name) == null){
            throw new IllegalArgumentException("Category does not exist!");
        }

        if (categoryRepository.findCategoryByName(name) != null){
            throw new IllegalArgumentException("Category name already exists!");
        }

        if (name == "") {
            throw new IllegalArgumentException("Name cannot be empty!");
        }

        Category c = categoryRepository.findCategoryByName(oldName);
        c.setName(name);
        categoryRepository.save(c);
        return c;
    }
}