package com.ndhuy.product.item.domain.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.product.item.domain.model.Item;
import com.ndhuy.product.item.domain.model.key.ItemKey;

public interface ItemReponsitory extends JpaRepository<Item,ItemKey>{
    
}
