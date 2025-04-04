package com.ndhuy.product.item.domain.dao.impl;

import org.springframework.stereotype.Component;

import com.ndhuy.app.exception.application.runtime.NotFoundRuntimeException;
import com.ndhuy.product.item.domain.dao.IItemDao;
import com.ndhuy.product.item.domain.model.Item;
import com.ndhuy.product.item.domain.model.key.ItemKey;
import com.ndhuy.product.item.domain.reponsitory.ItemRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Component
public class ItemDao implements IItemDao {

    @Resource
    ItemRepository itemRepository;

    @Override
    public Item insert(Item input) {
        itemRepository.save(input);
        return input;
    }

    @Override
    public Item update(ItemKey id, Item input) {
        var item = findById(id);
        if (item == null) {
            throw new NotFoundRuntimeException("item.not_found");
        }
        item.setName(input.getName());
        item.setDesc(input.getDesc());
        item.setDescShort(input.getDescShort());
        item.setAvatar(input.getAvatar());
        itemRepository.save(item);
        return item;

    }

    @Override
    public Item delete(Item input) {
        input.delete();
        itemRepository.save(input);
        return input;
    }

    @Override
    public Item findById(ItemKey id) {
        return itemRepository.findById(id).orElse(null);
    }

}
