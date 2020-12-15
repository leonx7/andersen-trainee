package com.andersen.shop.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID uuid = UUID.randomUUID();
    private String username;
    private String password;
}
