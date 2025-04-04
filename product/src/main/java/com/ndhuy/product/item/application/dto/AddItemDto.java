package com.ndhuy.product.item.application.dto;

public class AddItemDto {
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

        public AddItemIn(String name, String description, String imageUrl, String categoryId, String brandId, String unitId, String unitPrice, String quantity, String status) {
            this.name = name;
            this.description = description;
            this.imageUrl = imageUrl;
            this.categoryId = categoryId;
            this.brandId = brandId;
            this.unitId = unitId;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.status = status;
        }
    }
}
