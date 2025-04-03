package com.ndhuy.product.item.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.product.item.domain.Item;
import com.ndhuy.product.item.domain.key.ItemKey;

public interface ItemReponsitory extends JpaRepository<Item,ItemKey>{
    
}
