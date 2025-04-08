package com.ndhuy.product.item.domain.model.key;

public record ItemKey(String value) {
    public ItemKey{

    }
    public static ItemKey of(String value){
        return new ItemKey(value);
    }
    public static ItemKey generateId(){
        return new ItemKey(java.util.UUID.randomUUID().toString());
    }
}
