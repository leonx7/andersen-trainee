package com.andersen.shop.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "basket")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    private Set<Item> items;
}
