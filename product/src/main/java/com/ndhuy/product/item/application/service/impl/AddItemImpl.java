package com.ndhuy.product.item.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.product.item.application.dto.AddItemDto.AddItemIn;
import com.ndhuy.product.item.application.dto.AddItemDto.AddItemOut;
import com.ndhuy.product.item.application.service.AddItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddItemImpl implements AddItemService {

    @Override
    public AddItemOut doMain(AddItemIn cpln) {
        checkMain(cpln);

        return AddItemOut.builder().build();

    }

    private void checkMain(AddItemIn cpln) {
        checkCategory(cpln.getCategoryId());
    }

    private void checkCategory(String categoryId) {

    }

}
