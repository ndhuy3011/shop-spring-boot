package com.ndhuy.product.item.domain.model.key;

public record ItemKey(String value) {
    public ItemKey{

    }
    public static ItemKey of(String value){
        return new ItemKey(value);
    }
}
