package com.ndhuy.product.item.domain.dao.impl;

import org.springframework.stereotype.Component;

import com.ndhuy.product.item.domain.dao.CategoryDao;
import com.ndhuy.product.item.domain.model.Category;
import com.ndhuy.product.item.domain.model.key.CategoryKey;
import com.ndhuy.product.item.domain.reponsitory.CategoryRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Transactional
@Slf4j
@Component
public class CategoryDaoImpl implements CategoryDao{

    @Resource
    CategoryRepository categoryRepository;
    @Override
    public Category insert(Category input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public Category update(CategoryKey id, Category input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Category delete(Category input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Category findById(CategoryKey id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
