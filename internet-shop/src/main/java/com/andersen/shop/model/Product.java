package com.andersen.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductGroup productGroup;
    @Enumerated(EnumType.STRING)
    private Country country;
    private double price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-10s %-6s %-3s", id, name, price, "rub");
    }
}
