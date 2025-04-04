package com.ndhuy.product.item.domain.model;

import com.ndhuy.app.EntityBase;
import com.ndhuy.product.item.domain.model.key.ItemKey;
import com.ndhuy.product.item.domain.valueobject.item.ItemAvatar;
import com.ndhuy.product.item.domain.valueobject.item.ItemDesc;
import com.ndhuy.product.item.domain.valueobject.item.ItemDescShort;
import com.ndhuy.product.item.domain.valueobject.item.ItemName;
import com.ndhuy.product.item.domain.valueobject.item.ItemPrice;
import com.ndhuy.product.item.domain.valueobject.item.ItemQuantity;

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
@Table(name = "p_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item extends EntityBase {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "item_no"))
    private ItemKey id;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", length = ItemName.MAX_LENGTH, unique = true, nullable = false))
    private ItemName name;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "desc_short", length = ItemDescShort.MAX_LENGTH, nullable = true))
    private ItemDescShort descShort;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "desc", length = ItemDesc.MAX_LENGTH, nullable = true))
    private ItemDesc desc;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = true))
    private ItemQuantity quantity;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price", nullable = true))
    private ItemPrice price;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "avatar", nullable = true))
    private ItemAvatar avatar;
}
