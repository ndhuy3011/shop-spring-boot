package com.ndhuy.product.item.domain.model;

import com.ndhuy.app.EntityBase;
import com.ndhuy.product.item.domain.model.key.CategoryKey;
import com.ndhuy.product.item.domain.valueobject.category.CategoryName;
import com.ndhuy.product.item.domain.valueobject.category.CategoryPath;
import com.ndhuy.product.item.domain.valueobject.category.CategoryRoot;

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
@Table(name = "p_cantegory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends EntityBase {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "category_no"))
    private CategoryKey id;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", length = CategoryName.MAX_LENGTH, unique = true, nullable = false))
    private CategoryName name;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "path", length = CategoryPath.MAX_LENGTH, unique = true, nullable = false))
    private CategoryPath path;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "root", nullable = true))
    private CategoryRoot root;
}
