package com.ndhuy.product.item.domain.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.product.item.domain.model.Category;
import com.ndhuy.product.item.domain.model.key.CategoryKey;

public interface CategoryRepository extends JpaRepository<Category,CategoryKey>{
    
}
