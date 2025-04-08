package com.ndhuy.product.item.application.service;

import com.ndhuy.product.item.application.dto.AddItemDto.AddItemIn;
import com.ndhuy.product.item.application.dto.AddItemDto.AddItemOut;

public interface AddItemService {
    AddItemOut doMain(AddItemIn cpln);
}
