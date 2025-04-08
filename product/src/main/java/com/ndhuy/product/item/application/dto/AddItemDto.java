package com.ndhuy.product.item.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AddItemDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class AddItemIn{
        private String name;
        private String description;
        private String imageUrl;
        private String categoryId;
        private String brandId;
        private String unitId;
        private String unitPrice;
        private String quantity;
        private String status;


    }

    @Getter
    @Setter
    @Builder
    
    public static class AddItemOut{
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String categoryId;
        private String brandId;
        private String unitId;
        private String unitPrice;
        private String quantity;
        private String status;


    }
}
