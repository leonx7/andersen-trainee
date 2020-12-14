package com.andersen.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Currency {
    private String name;
    @Getter
    private String code;
    private Double course;
}
