package com.andersen.shop.model;

import com.andersen.shop.annotations.ExpiryDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductWithExpiryDate extends Product {
    @ExpiryDate
    @Setter
    @Getter
    private LocalDate expiryDate;

    public ProductWithExpiryDate(int productId, String name, ProductGroup productGroup, double price, Currency currency) {
        super(productId, name, productGroup, price, currency);
    }
}
