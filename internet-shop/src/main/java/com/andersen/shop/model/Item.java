package com.andersen.shop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private long product_id;
    private String name;
    private double price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;
}
