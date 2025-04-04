package com.ndhuy.product.item.domain.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.product.item.domain.model.ItemImage;
import com.ndhuy.product.item.domain.model.key.ItemImageKey;

public interface ItemImageRepository extends JpaRepository<ItemImage,ItemImageKey>{
    
}
