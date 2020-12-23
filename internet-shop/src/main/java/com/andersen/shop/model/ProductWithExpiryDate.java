package com.andersen.shop.model;

import com.andersen.shop.annotations.ExpiryDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductWithExpiryDate extends Product {
    @Setter
    @Getter
    @ExpiryDate
    private LocalDate expiryDate;
}
