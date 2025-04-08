package com.ndhuy.product.item.domain.dao.impl;

import org.springframework.stereotype.Component;

import com.ndhuy.product.item.domain.dao.ItemImageDao;
import com.ndhuy.product.item.domain.model.ItemImage;
import com.ndhuy.product.item.domain.model.key.ItemImageKey;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Component
public class ItemImageDaoImpl implements ItemImageDao {

    @Override
    public ItemImage insert(ItemImage input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public ItemImage update(ItemImageKey id, ItemImage input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ItemImage delete(ItemImage input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ItemImage findById(ItemImageKey id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
