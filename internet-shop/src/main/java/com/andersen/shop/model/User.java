package com.andersen.shop.model;

import lombok.Getter;

import java.util.UUID;

public class User {
    @Getter
    UUID uuid = UUID.randomUUID();
}
