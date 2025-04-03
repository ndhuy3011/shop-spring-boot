package com.ndhuy.product.item.domain.key;

public record ItemKey(String value) {
    public ItemKey{

    }
    public static ItemKey of(String value){
        return new ItemKey(value);
    }
}
