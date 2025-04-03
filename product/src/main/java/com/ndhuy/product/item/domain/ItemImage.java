package com.ndhuy.product.item.domain;

import com.ndhuy.product.item.domain.key.ItemImageKey;
import com.ndhuy.product.item.valueobject.itemImage.ItemImagePath;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "p_item_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemImage {
    @EmbeddedId
    private ItemImageKey itemImageKey;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "path", nullable = true))
    private ItemImagePath itemImagePath;

}
