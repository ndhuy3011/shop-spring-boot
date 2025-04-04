package com.ndhuy.product.item.domain.model.key;

import com.ndhuy.product.item.domain.valueobject.item.ItemSeria;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;

public record ItemImageKey( @AttributeOverride(name = "value", column = @Column(name = "item_no")) ItemKey idItem,
        @AttributeOverride(name = "value", column = @Column(name = "item_seria"))  ItemSeria seria){
    
}
